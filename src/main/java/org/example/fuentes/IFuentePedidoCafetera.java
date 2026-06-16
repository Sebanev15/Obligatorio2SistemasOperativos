package org.example.fuentes;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;

public interface IFuentePedidoCafetera extends IFuentePedido {
    public void procesarPedido(Pedido pedido, Cafetera cafetera, Baristas baristas)throws InterruptedException;

}
