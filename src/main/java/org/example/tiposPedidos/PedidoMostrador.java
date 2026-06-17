package org.example.tiposPedidos;

import org.example.*;
import org.example.fuentes.IFuentePedido;

public class PedidoMostrador implements IFuentePedido {
    private final CajaRegistradora cajaRegistradora;
    private final Cafetera cafetera;
    private final Baristas baristas;

    public PedidoMostrador(Cafetera cafetera, CajaRegistradora cajaRegistradora, Baristas baristas){
        this.cafetera = cafetera;
        this.cajaRegistradora = cajaRegistradora;
        this.baristas = baristas;
    }
    @Override
    public void procesarPedido(Pedido pedido) throws InterruptedException {
        baristas.ocuparBarista();
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        cajaRegistradora.procesarEnMostrador(pedido);
        pedido.setCompletado(true);
        baristas.liberarBarista();

    }
}
