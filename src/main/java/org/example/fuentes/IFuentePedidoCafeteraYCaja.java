package org.example.fuentes;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.CajaRegistradora;
import org.example.Pedido;

public interface IFuentePedidoCafeteraYCaja extends IFuentePedido {
    public void procesarPedido(Pedido pedido, Cafetera cafetera, CajaRegistradora cajaRegistradora, Baristas baristas)throws InterruptedException;
}
