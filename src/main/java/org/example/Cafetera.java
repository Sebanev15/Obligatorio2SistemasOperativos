package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Cafetera {
    static ReentrantLock llave = new ReentrantLock();

    public void procesarCafe(Pedido pedido){
        if(!pedido.isCompletado()){
            if(pedido.getProducto().equals(Producto.CAFE)){
                llave.lock();
                pedido.setTiempoProcesarTotal(0);
                llave.unlock();
            }
        }
    }
}
