package org.example;

public enum Producto {
    CAFE(1,5),
    AGUA(2,0),
    SANDWICH(3,0);

    private final int tiempoProcesar;
    private final int prioridad;

    Producto(int tiempoProcesar, int prioridad) {
        this.tiempoProcesar = tiempoProcesar;
        this.prioridad = prioridad;
    }

    public int getTiempoProcesar() {
        return tiempoProcesar;
    }

    public int getPrioridad() {
        return prioridad;
    }
}
