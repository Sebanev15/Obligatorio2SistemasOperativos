package org.example;

import org.example.fuentes.IFuentePedido;
import org.example.tiposPedidos.PedidoApp;
import org.example.tiposPedidos.PedidoMostrador;
import org.example.tiposPedidos.PedidoTotem;

public class Pedido extends Thread{
    private Producto producto;
    private int tiempoProcesarTotal;
    private Cliente cliente;
    private int prioridad;
    private boolean completado;
    private IFuentePedido fuentePedido;

    public Pedido(Producto producto, Cliente cliente){
        this.producto = producto;
        this.tiempoProcesarTotal = this.producto.getTiempoProcesar();
        this.cliente = cliente;
        this.completado = false;
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

    public void procesarPedido(GestionPedidos gestionPedidos, Baristas baristas) throws InterruptedException {
        if(fuentePedido instanceof PedidoApp pedidoApp){
            pedidoApp.procesarPedido(this, gestionPedidos.cafetera, baristas);
        }
        if(fuentePedido instanceof PedidoMostrador pedidoMostrador){
            pedidoMostrador.procesarPedido(this, gestionPedidos.cafetera, gestionPedidos.cajaRegistradora, baristas);
        }
        if(fuentePedido instanceof PedidoTotem pedidoTotem){
            pedidoTotem.procesarPedido(this, gestionPedidos.cafetera, baristas);
        }
    }
}
