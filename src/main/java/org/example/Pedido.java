package org.example;

import org.example.fuentes.IFuentePedido;

public class Pedido extends Thread{
    private Producto producto;
    private int tiempoProcesarTotal;
    private Cliente cliente;
    private int prioridad;
    private boolean completado;
    private final IFuentePedido fuentePedido;

    public Pedido(Producto producto, Cliente cliente, IFuentePedido fuentePedido) {
        this.producto = producto;
        this.tiempoProcesarTotal = this.producto.getTiempoProcesar();
        this.cliente = cliente;
        this.completado = false;
        this.fuentePedido = fuentePedido;
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

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public void setTiempoProcesarTotal(int tiempoProcesarTotal) {
        this.tiempoProcesarTotal = tiempoProcesarTotal;
    }

}
