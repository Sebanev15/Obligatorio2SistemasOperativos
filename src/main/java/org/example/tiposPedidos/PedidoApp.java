package org.example.tiposPedidos;

import org.example.Baristas;
import org.example.Cafetera;
import org.example.Pedido;
import org.example.Producto;
import org.example.fuentes.IFuentePedido;

public class PedidoApp implements IFuentePedido {
    private final Cafetera cafetera;
    private final Baristas baristas;

    public PedidoApp(Cafetera cafetera, Baristas baristas) {
        this.cafetera = cafetera;
        this.baristas = baristas;
    }
    @Override
    public void procesarPedido(Pedido pedido) throws InterruptedException {
        baristas.ocuparBarista();
        pedido.setProcesando(true);
        System.out.println("Procesando " + pedido.getProducto() + " de " + pedido.getCliente().getRol() + " " + pedido.getCliente().getNombre() + " desde la app");

        if(pedido.getProducto().equals(Producto.CAFE)){
            this.cafetera.procesarCafe(pedido);
        }

        baristas.liberarBarista();
        pedido.setCompletado(true);
        System.out.println("Pedido de " + pedido.getCliente().getRol() + " " + pedido.getCliente().getNombre() + " completado");
    }
}
