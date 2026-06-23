package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
        pedidosCompletados.add(pedido);
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
        Set <Long> pedidosInaceptables = new HashSet<>();
        Map <Producto,Integer> ventas= new HashMap<>();
        Map <RolCliente,Integer> tiempoPorRol = new HashMap();
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
            if (p.getTiempoEspera()>100){
                pedidosInaceptables.add(p.getId());
            }
            if(p.getCliente().getRol().equals(RolCliente.ESTUDIANTE)){
                tiempoPorRol.put(RolCliente.ESTUDIANTE, tiempoPorRol.getOrDefault(RolCliente.ESTUDIANTE, 0) + p.getTiempoEspera());
            } else if(p.getCliente().getRol().equals(RolCliente.DOCENTE)){
                tiempoPorRol.put(RolCliente.DOCENTE, tiempoPorRol.getOrDefault(RolCliente.DOCENTE, 0) + p.getTiempoEspera());
            }
        }

        pedidosCompletados.clear();

        System.out.println("===== METRICAS =====");

        System.out.println("Profit del dia: $"+profit);
        System.out.println("Tiempo total de procesamiento: "+tiempoTotal);
        System.out.println("Tiempo promedio de espera: "+(double)sumaTiempoEspera/cantidadPedidos);
        double porcentajeUsoCafetera = ((double) ventas.get(Producto.CAFE) / cantidadPedidos) * 100;
        System.out.println("Porcentaje de uso de la cafetera: " + porcentajeUsoCafetera + "%");
        System.out.println("Tiempo de espera total de estudiantes: " + tiempoPorRol.get(RolCliente.ESTUDIANTE));
        System.out.println("Tiempo de espera total de docentes: " + tiempoPorRol.get(RolCliente.DOCENTE));
        if (pedidosInaceptables.isEmpty()){
            System.out.println("No hubo pedidos con exceso de espera");
        } else {
            System.out.println("Id de pedidos con exceso de espera: "+pedidosInaceptables.toString());
        }
        
    }

    public synchronized void completarPedido(Pedido pedido) {
        pedidosCompletados.add(pedido);
        pedido.getCliente().setFidelidad(pedido.getCliente().getFidelidad() + 2);
    }

    public Queue<Pedido> getPedidos() {
        return pedidos;
    }
}
