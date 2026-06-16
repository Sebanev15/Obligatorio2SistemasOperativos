package org.example.tiposPedidos;

import org.example.*;
import org.example.fuentes.IFuentePedidoCafeteraYCaja;

public class PedidoMostrador implements IFuentePedidoCafeteraYCaja {
    @Override
    public void procesarPedido(Pedido pedido, Cafetera cafetera, CajaRegistradora cajaRegistradora, Baristas baristas) throws InterruptedException {
        baristas.ocuparBarista();
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        cajaRegistradora.procesarEnMostrador(pedido);
        pedido.setCompletado(true);

    }
}
