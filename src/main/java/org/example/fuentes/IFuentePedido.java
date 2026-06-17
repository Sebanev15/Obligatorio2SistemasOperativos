package org.example.fuentes;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;

public interface IFuentePedido {
    public void procesarPedido(Pedido pedido)throws InterruptedException;
}
