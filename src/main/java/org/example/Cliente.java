package org.example;

import org.example.fuentes.IFuentePedido;

public class Cliente {
    private String nombre;
    private RolCliente rol;
    private int fidelidad;
    public Cliente(String nombre, RolCliente rol){
        this.nombre = nombre;
        this.rol = rol;
        this.fidelidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public RolCliente getRol() {
        return rol;
    }

    public int getFidelidad() {
        return fidelidad;
    }

    public void setFidelidad(int fidelidad) {
        this.fidelidad = fidelidad;
    }

    public Pedido crearPedido(Producto producto, IFuentePedido  fuente){
        return new Pedido(producto, this, fuente);
    }

    public int getPrioridad(){
        return this.rol.getPrioridad();
    }
}
