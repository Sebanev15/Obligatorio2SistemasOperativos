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
        pedido.setProcesando(true);
        System.out.println("Procesando " + pedido.getProducto() + " de " + pedido.getCliente().getRol() + " " + pedido.getCliente().getNombre() + " desde el totem");
        if(pedido.getProducto().equals(Producto.CAFE)){
            cafetera.procesarCafe(pedido);
        }
        baristas.liberarBarista();
        pedido.setCompletado(true);
        System.out.println("Pedido de " + pedido.getCliente().getRol() + " " + pedido.getCliente().getNombre() + " completado");
    }
}
