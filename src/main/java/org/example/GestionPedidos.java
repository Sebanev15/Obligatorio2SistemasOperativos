package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class GestionPedidos {
    Queue<Pedido> pedidos;
    LinkedList<Pedido> pedidosCompletados;
    Baristas baristas;
    Cafetera cafetera;
    CajaRegistradora cajaRegistradora;

    public GestionPedidos(Baristas baristas, Cafetera cafetera, CajaRegistradora cajaRegistradora) {
        this.baristas = baristas;
        this.cafetera = cafetera;
        this.cajaRegistradora = cajaRegistradora;
        this.pedidos = new PriorityQueue<>();
        this.pedidosCompletados = new LinkedList<>();
    }
    public synchronized void procesarPedidos() throws InterruptedException {
        while(pedidos.isEmpty()){
            wait();
        }
        Pedido pedidoP = pedidos.poll();
        Thread pedido = pedidoP;
        System.out.println(baristas.cantidadDisponibles+ " mozos disponibles");
        pedido.run();
        pedidosCompletados.add(pedidoP);
        pedidoP.getCliente().setFidelidad(pedidoP.getCliente().getFidelidad() + 2);
        for(Pedido p : pedidos){
            p.aumentarTiempoEspera(1);
            p.calcularPrioridad();
        }
    }

    public synchronized void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        notify();
    }

    public void cerrarCaja(){
        double profit =0.0;
        int tiempoTotal=0;
        Map <Producto,Integer> ventas= new HashMap<>();
        for (Pedido p:pedidosCompletados){
            profit+=p.getProducto().getPrecio();
            System.out.println(p.getTiempoProcesarTotal());
            tiempoTotal+=p.getProducto().getTiempoProcesar();
            if (!ventas.containsKey(p.getProducto())){
                ventas.put(p.getProducto(),1);
            }else{
                ventas.put(p.getProducto(), ventas.get(p.getProducto())+1);
            }
            
        System.out.println("===== CAJA HOY =====");
        }
        for (Producto p:ventas.keySet()){
            System.out.println(p+" "+ventas.get(p)+" cantidades");
        }
        System.out.println("Profit del dia: $"+profit);

        
        pedidosCompletados.clear();

        System.out.println("===== METRICAS =====");
        System.out.println("Porcentaje de uso de la cafetera");
        System.out.println(ventas.get(Producto.CAFE));
        System.out.println((ventas.get(Producto.CAFE)/tiempoTotal)*100 + "%");
    }

    
}
