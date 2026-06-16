package org.example;

import org.example.tiposPedidos.PedidoMostrador;

import java.util.concurrent.locks.ReentrantLock;

public class CajaRegistradora {
    static ReentrantLock llave = new ReentrantLock();

    public void procesarEnMostrador(Pedido pedido){
        if(!pedido.isCompletado()){
            if(pedido.getFuentePedido() instanceof PedidoMostrador){
                llave.lock();
                pedido.setTiempoProcesarTotal(0);
                llave.unlock();
            }
        }
    }
}
