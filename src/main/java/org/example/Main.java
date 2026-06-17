package org.example;

import org.example.tiposPedidos.PedidoApp;
import org.example.tiposPedidos.PedidoMostrador;
import org.example.tiposPedidos.PedidoTotem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cafetera cafetera = new Cafetera();
        CajaRegistradora cajaRegistradora = new CajaRegistradora();
        Baristas baristas = new Baristas(1);
        GestionPedidos gestionPedidos = new GestionPedidos(baristas, cafetera, cajaRegistradora);

        Cliente estudiante1 = new Cliente("Juan", RolCliente.ESTUDIANTE);
        Cliente docente1 = new Cliente("Juan", RolCliente.DOCENTE);

        PedidoApp pedidoApp = new PedidoApp(cafetera, baristas);
        PedidoMostrador pedidoMostrador = new PedidoMostrador(cafetera, cajaRegistradora, baristas);
        PedidoTotem pedidoTotem = new PedidoTotem(cafetera, baristas);


        Pedido pedido1 = estudiante1.crearPedido(Producto.CAFE, pedidoApp);
        Pedido pedido3 = estudiante1.crearPedido(Producto.AGUA, pedidoApp);
        Pedido pedido4 = estudiante1.crearPedido(Producto.CAFE, pedidoApp);
        Pedido pedido5 = estudiante1.crearPedido(Producto.SANDWICH, pedidoApp);
        Pedido pedido6 = estudiante1.crearPedido(Producto.CAFE, pedidoApp);
        Pedido pedido7 = estudiante1.crearPedido(Producto.AGUA, pedidoApp);
        Pedido pedido8 = estudiante1.crearPedido(Producto.CAFE, pedidoApp);
        Pedido pedido9 = estudiante1.crearPedido(Producto.CAFE, pedidoApp);

        Pedido pedido2 = docente1.crearPedido(Producto.CAFE, pedidoMostrador);

        gestionPedidos.agregarPedido(pedido1);
        gestionPedidos.agregarPedido(pedido3);
        gestionPedidos.agregarPedido(pedido4);
        gestionPedidos.agregarPedido(pedido5);
        gestionPedidos.agregarPedido(pedido6);
        gestionPedidos.agregarPedido(pedido7);
        gestionPedidos.agregarPedido(pedido2);


        while(!gestionPedidos.pedidos.isEmpty()){
            gestionPedidos.procesarPedidos();
        }
        System.out.println("D");


    }
}