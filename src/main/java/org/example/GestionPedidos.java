package org.example;

import java.util.ArrayList;
import java.util.Queue;

public class GestionPedidos {
    Queue<Pedido> pedidos;
    Baristas baristas;
    Cafetera cafetera;
    CajaRegistradora cajaRegistradora;

    public void procesarPedidos() throws InterruptedException {
        while(!pedidos.isEmpty()){
            for(Pedido pedido: pedidos){
                pedido.getFuentePedido().procesarPedido(pedido, baristas);
            }
        }
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }

}
