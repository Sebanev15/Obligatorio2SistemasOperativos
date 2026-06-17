package org.example;

public enum RolCliente {
    ESTUDIANTE(0),
    DOCENTE(20);

    private final int prioridad;
    RolCliente(int prioridad){
        this.prioridad = prioridad;
    }
    public int getPrioridad(){
        return this.prioridad;
    }
}
