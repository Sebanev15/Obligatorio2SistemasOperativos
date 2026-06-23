package org.example;

import org.example.fuentes.IFuentePedido;

public class Pedido extends Thread implements Comparable<Pedido> {
    private Producto producto;
    private int tiempoProcesarTotal;
    private Cliente cliente;
    private int prioridad;
    private boolean completado;
    private final IFuentePedido fuentePedido;
    private int tiempoEspera;
    private int ponderadoTiempoEspera;
    private boolean procesando;

    public Pedido(Producto producto, Cliente cliente, IFuentePedido fuentePedido) {
        this.producto = producto;
        this.tiempoProcesarTotal = this.producto.getTiempoProcesar();
        this.cliente = cliente;
        this.completado = false;
        this.fuentePedido = fuentePedido;
        this.tiempoEspera = 0;
        this.ponderadoTiempoEspera = 0;
        this.procesando = false;
        calcularPrioridad();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getTiempoProcesarTotal() {
        return tiempoProcesarTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletado() {
        return completado;
    }

    public IFuentePedido getFuentePedido() {
        return fuentePedido;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public boolean isProcesando() {
        return procesando;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public void setTiempoProcesarTotal(int tiempoProcesarTotal) {
        this.tiempoProcesarTotal = tiempoProcesarTotal;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public void setProcesando(boolean procesando) {
        this.procesando = procesando;
    }

    public void aumentarTiempoEspera(int tiempoEspera) {
        this.tiempoEspera += Math.max(tiempoEspera, 0);
        if(this.tiempoEspera < 30){
            this.ponderadoTiempoEspera = 1;
        }else if(this.tiempoEspera < 60){
            this.ponderadoTiempoEspera = 2;
        } else if (this.tiempoEspera < 90) {
            this.ponderadoTiempoEspera = 3;
        } else {
            this.ponderadoTiempoEspera = 4;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.getProducto().getTiempoProcesar()*1000);
            fuentePedido.procesarPedido(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void calcularPrioridad(){
        this.prioridad = (this.tiempoEspera * this.ponderadoTiempoEspera) + this.cliente.getFidelidad() + this.producto.getPrioridad() + this.cliente.getPrioridad();
    }

    @Override
    public int compareTo(Pedido o) {
        return Integer.compare(o.prioridad, this.prioridad);
    }
}
