package org.example;

public class Baristas {
    boolean hayBaristas;
    int cantidadBaristas;

    public Baristas(int cantidadBaristasInicial){
        this.cantidadBaristas = Math.max(cantidadBaristasInicial, 0);
        this.hayBaristas = cantidadBaristas != 0;
    }

    public int agregarBarista(){
        this.cantidadBaristas++;
        this.hayBaristas = true;
        notify();
        return cantidadBaristas;
    }

    public int eliminarBarista(){
        this.cantidadBaristas = Math.max(this.cantidadBaristas - 1, 0);
        this.hayBaristas = cantidadBaristas != 0;
        return cantidadBaristas;
    }

    public synchronized void ocuparBarista() throws InterruptedException {
        while (!hayBaristas){
            wait();
        }
    }

    public synchronized void liberarBarista(){
        hayBaristas = true;
        notify();
    }
}
