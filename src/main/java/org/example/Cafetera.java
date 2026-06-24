package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Cafetera {
    private static ReentrantLock llave = new ReentrantLock();

    public void procesarCafe(Pedido pedido) throws InterruptedException {
        if(!pedido.isCompletado()){
            if(pedido.getProducto().equals(Producto.CAFE)){
                llave.lock();
                System.out.println("turno del pedido de " + pedido.getCliente().getRol() + " " + pedido.getCliente().getNombre() + " en cafetera");
                Thread.sleep(pedido.getProducto().getTiempoProcesar()*1000);
                llave.unlock();
                System.out.println("Cafetera libre");
            }
        }
    }
}
