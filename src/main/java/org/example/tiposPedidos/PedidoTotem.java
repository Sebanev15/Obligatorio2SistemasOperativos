package org.example.tiposPedidos;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;
import org.example.Producto;
import org.example.fuentes.IFuentePedido;

public class PedidoTotem implements IFuentePedido {
    private final Cafetera cafetera;
    private final Baristas baristas;
    public PedidoTotem(Cafetera cafetera, Baristas baristas) {
        this.cafetera = cafetera;
        this.baristas = baristas;
    }

    @Override
    public void procesarPedido(Pedido pedido) throws InterruptedException {
        baristas.ocuparBarista();
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        pedido.setCompletado(true);
        baristas.liberarBarista();
    }
}
