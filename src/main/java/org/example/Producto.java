package org.example;

public enum Producto {
    CAFE(1,20,80.0),
    AGUA(2,0,50.0),
    SANDWICH(3,0,120);

    private final int tiempoProcesar;
    private final int prioridad;
    private final double precio;

    Producto(int tiempoProcesar, int prioridad, double precio) {
        this.tiempoProcesar = tiempoProcesar;
        this.prioridad = prioridad;
        this.precio=precio;
    }

    public int getTiempoProcesar() {
        return tiempoProcesar;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public double getPrecio (){
        return this.precio;
    }
}
