import org.example.*;
import org.example.tiposPedidos.PedidoApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestionPedidosTest {
    private Baristas baristas = new Baristas(3);
    private Cafetera cafetera = new Cafetera();
    private CajaRegistradora cajaRegistradora = new CajaRegistradora();
    private GestionPedidos gestionPedidos = new GestionPedidos(baristas, cafetera, cajaRegistradora);
    Cliente estudiante1 = new Cliente("Pedro", RolCliente.ESTUDIANTE);
    Cliente docente1 = new Cliente("Jose", RolCliente.DOCENTE);
    PedidoApp pedidoApp = new PedidoApp(cafetera, baristas);
    @Test
    public void tryProductWithInitialLowPriority() throws InterruptedException {
        Pedido pedidoSinPrioridad = estudiante1.crearPedido(Producto.AGUA, pedidoApp);
        gestionPedidos.agregarPedido(pedidoSinPrioridad);
        for(int x=0; x<=100; x++){
            Pedido pedidoPrioridad = docente1.crearPedido(Producto.CAFE, pedidoApp);
            pedidoPrioridad.setPrioridad(100);
            gestionPedidos.agregarPedido(pedidoPrioridad);
            gestionPedidos.procesarPedidos();
        }
        while(!gestionPedidos.getPedidos().isEmpty()){
            gestionPedidos.procesarPedidos();
        }
        assertTrue(pedidoSinPrioridad.getTiempoEspera()<100);
    }
}
