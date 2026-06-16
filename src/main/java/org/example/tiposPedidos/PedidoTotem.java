package org.example.tiposPedidos;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;
import org.example.Producto;
import org.example.fuentes.IFuentePedido;

public class PedidoTotem implements IFuentePedido {
    private final Cafetera cafetera;
    public PedidoTotem(Cafetera cafetera){
        this.cafetera = cafetera;
    }

    @Override
    public void procesarPedido(Pedido pedido, Baristas baristas) throws InterruptedException {
        baristas.ocuparBarista();
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        pedido.setCompletado(true);
        baristas.liberarBarista();
    }
}
