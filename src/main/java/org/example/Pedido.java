package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Producto producto;
    private int tiempoProcesarTotal;
    private Cliente cliente;
    private int prioridad;
    private boolean completado;

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
