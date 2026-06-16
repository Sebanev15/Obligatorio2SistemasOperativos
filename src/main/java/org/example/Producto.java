package org.example;

public enum Producto {
    CAFE(1),
    AGUA(2),
    SANDWICH(3);

    int tiempoProcesar;
    Producto(int tiempoProcesar) {
        this.tiempoProcesar = tiempoProcesar;
    }

    public int getTiempoProcesar() {
        return tiempoProcesar;
    }
}
