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
        Pedido pedido = pedidos.poll();

        for(Pedido p: pedidos){
            p.aumentarTiempoEspera(1);
            p.calcularPrioridad();
        }
        Queue<Pedido> pedidosAux = new PriorityQueue<>(pedidos);
        pedidos = pedidosAux;
        pedido.start();
    }

    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        notify();
    }

    public void cerrarCaja() throws InterruptedException {
        while(!pedidos.isEmpty()){
            wait();
        }
        double profit =0.0;
        int tiempoTotal=0;
        int cantidadPedidos=pedidosCompletados.size();
        int sumaTiempoEspera=0;
        Map <Producto,Integer> ventas= new HashMap<>();
        System.out.println("===== CAJA HOY =====");
        for (Pedido p:pedidosCompletados){
            profit+=p.getProducto().getPrecio();
            System.out.println(p.getProducto() + " de " + p.getCliente().getRol() + " " + p.getCliente().getNombre() + "; tiempo de espera "+ p.getTiempoEspera() + " y prioridad de " + p.getPrioridad() );
            sumaTiempoEspera+=p.getTiempoEspera();
            tiempoTotal+=p.getProducto().getTiempoProcesar();
            if (!ventas.containsKey(p.getProducto())){
                ventas.put(p.getProducto(),1);
            }else{
                ventas.put(p.getProducto(), ventas.get(p.getProducto())+1);
            }
        }

        pedidosCompletados.clear();

        System.out.println("===== METRICAS =====");

        System.out.println("Profit del dia: $"+profit);
        System.out.println("Tiempo total de procesamiento: "+tiempoTotal);
        System.out.println("Tiempo promedio de espera: "+(double)sumaTiempoEspera/cantidadPedidos);
        double porcentajeUsoCafetera = ((double) ventas.get(Producto.CAFE) / cantidadPedidos) * 100;
        System.out.println("Porcentaje de uso de la cafetera: " + porcentajeUsoCafetera + "%");
    }

    public synchronized void completarPedido(Pedido pedido) {
        pedidosCompletados.add(pedido);
        pedido.getCliente().setFidelidad(pedido.getCliente().getFidelidad() + 2);
    }
}
