package org.example.tiposPedidos;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;
import org.example.Producto;
import org.example.fuentes.IFuentePedidoCafetera;

public class PedidoTotem implements IFuentePedidoCafetera {
    @Override
    public void procesarPedido(Pedido pedido, Cafetera cafetera, Baristas baristas) throws InterruptedException {
        baristas.ocuparBarista();
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        pedido.setCompletado(true);
    }
}
