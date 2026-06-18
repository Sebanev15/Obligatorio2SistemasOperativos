package org.example;

public class Baristas {
    boolean hayBaristas;
    int cantidadBaristas;
    int cantidadDisponibles;

    public Baristas(int cantidadBaristasInicial){
        this.cantidadBaristas = Math.max(cantidadBaristasInicial, 0);
        this.cantidadDisponibles = this.cantidadBaristas;
    }

    public int getCantidadDisponibles() {
        return cantidadDisponibles;
    }

    public int agregarBarista(){
        this.cantidadBaristas++;
        this.cantidadDisponibles++;
        notify();
        return cantidadBaristas;
    }

    public int eliminarBarista(){
        this.cantidadBaristas = Math.max(this.cantidadBaristas - 1, 0);
        this.cantidadDisponibles = Math.max(this.cantidadDisponibles - 1, 0);
        return cantidadBaristas;
    }

    public synchronized void ocuparBarista() throws InterruptedException {
        while (!(cantidadDisponibles > 0)) {
            wait();
        }
        this.cantidadDisponibles--;
        Thread.sleep(1000);
    }

    public synchronized void liberarBarista(){
        cantidadDisponibles = Math.min(this.cantidadDisponibles + 1, this.cantidadBaristas);
        notify();
    }
}
