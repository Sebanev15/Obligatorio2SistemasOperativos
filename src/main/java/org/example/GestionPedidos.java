package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GestionPedidos {
    Queue<Pedido> pedidos;
    LinkedList<Pedido> pedidosCompletados;
    Baristas baristas;
    Cafetera cafetera;
    CajaRegistradora cajaRegistradora;

    public GestionPedidos(Baristas baristas, Cafetera cafetera, CajaRegistradora cajaRegistradora) {
        this.baristas = baristas;
        this.cafetera = cafetera;
        this.cajaRegistradora = cajaRegistradora;
        this.pedidos = new PriorityQueue<>();
        this.pedidosCompletados = new LinkedList<>();
    }
    public synchronized void procesarPedidos() throws InterruptedException {
        while(pedidos.isEmpty()){
            wait();
        }
        Pedido pedido = pedidos.poll();
        pedido.start();
        pedido.join();
        pedidosCompletados.add(pedido);
        pedido.getCliente().setFidelidad(pedido.getCliente().getFidelidad() + 2);
        for(Pedido p : pedidos){
            p.aumentarTiempoEspera(1);
            p.calcularPrioridad();
        }
    }

    public synchronized void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        notify();
    }
}
