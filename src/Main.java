import controllers.ControlCliente;
import controllers.ControlFactura;
import controllers.ControlProducto;
import models.Factura.Factura;
import models.Factura.ItemFactura;
import models.clientes.Cliente;
import models.product.CategoriaProducto;
import models.product.Marca;
import models.product.Producto;
import models.product.Proveedor;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Inicio del Sistema de Facturación y Stock (Prototipo Ampliado) ---");

        // 1. Instanciar los controladores
        ControlProducto controlProducto = new ControlProducto();
        ControlCliente controlCliente = new ControlCliente();
        ControlFactura controlFactura = new ControlFactura(controlProducto); // ControlFactura necesita ControlProducto

        // === Parte de Productos ===
        System.out.println("\n--- Módulo de Productos ---");
        Marca lactea = new Marca("La Serenisima");
        CategoriaProducto lacteos = new CategoriaProducto("Lácteos");
        Proveedor proveedorLacteos = new Proveedor("Distribuidora Láctea S.A.", "Av. Siempre Viva 742", "351-4567890");

        Producto leche = new Producto("L001", "Leche Entera", "Leche Larga Vida 1L",
                        150.0, 5, 10, lacteos, lactea, proveedorLacteos);
        controlProducto.registrarProducto(leche);

        Producto pan = new Producto("P002", "Pan Integral", "Pan de molde 500g",
                        200.0, 3, 2, new CategoriaProducto("Panificados"),
                new Marca("Fargo"), new Proveedor("Panadería El Buen Pan", "Calle del Pan 100", "351-9876543"));
        controlProducto.registrarProducto(pan);

        Producto galletas = new Producto("G003", "Galletas Dulces", "Paquete de 150g",
                        90.0, 10, 20, new CategoriaProducto("Snacks"),
                new Marca("Arcor"), new Proveedor("Golosinas SA", "Ruta 20 Km 5", "351-5554433"));
        controlProducto.registrarProducto(galletas);

        controlProducto.listarProductos();
        System.out.println("Stock inicial de L001: " + controlProducto.consultarStock("L001"));


        // === Parte de Clientes ===
        System.out.println("\n--- Módulo de Clientes ---");
        Cliente cliente1 = new Cliente("22333444", "Juan", "Perez", "Av. San Martín 123", "351-1111111");
        controlCliente.registrarCliente(cliente1);

        Cliente cliente2 = new Cliente("33444555", "Maria", "Gomez", "Calle Falsa 456", "351-2222222");
        controlCliente.registrarCliente(cliente2);

        controlCliente.listarClientes();

        // Probar buscar un cliente
        Cliente clienteBuscado = controlCliente.consultarCliente("22333444");
        if (clienteBuscado != null) {
            System.out.println("Cliente encontrado: " + clienteBuscado);
        }

        // === Parte de Facturación ===
        System.out.println("\n--- Módulo de Facturación ---");

        // Caso 1: Factura exitosa con stock suficiente
        System.out.println("\n--- Emitiendo Factura 1 (exitosa) ---");
        Factura factura1 = controlFactura.crearFactura(cliente1);
        if (factura1 != null) {
            // Se asume que el precio unitario del ItemFactura es el precio actual del Producto
            factura1.agregarItem(new ItemFactura(leche, 2, leche.getPrecio())); // 2 leches
            factura1.agregarItem(new ItemFactura(galletas, 5, galletas.getPrecio())); // 5 galletas

            controlFactura.emitirFactura(factura1);
        }
        System.out.println("Stock de L001 después de Factura 1: " + controlProducto.consultarStock("L001"));
        System.out.println("Stock de G003 después de Factura 1: " + controlProducto.consultarStock("G003"));


        // Caso 2: Factura con stock insuficiente
        System.out.println("\n--- Emitiendo Factura 2 (stock insuficiente) ---");
        Factura factura2 = controlFactura.crearFactura(cliente2);
        if (factura2 != null) {
            factura2.agregarItem(new ItemFactura(pan, 10, pan.getPrecio())); // Intentar vender 10 panes, pero solo hay 2
            controlFactura.emitirFactura(factura2); // Debería fallar
        }
        System.out.println("Stock de P002 después de Factura 2 (fallida): " + controlProducto.consultarStock("P002"));


        // Caso 3: Factura para anular
        System.out.println("\n--- Emitiendo Factura 3 para anular ---");
        Factura factura3 = controlFactura.crearFactura(cliente1);
        if (factura3 != null) {
            factura3.agregarItem(new ItemFactura(leche, 1, leche.getPrecio()));
            controlFactura.emitirFactura(factura3);
        }
        System.out.println("Stock de L001 después de Factura 3: " + controlProducto.consultarStock("L001"));


        // 4. Listar todas las facturas
        controlFactura.listarFacturas();

        // 5. Anular una factura
        System.out.println("\n--- Anulando Factura 3 ---");
        controlFactura.anularFactura(factura3.getNumero());
        controlFactura.listarFacturas(); // Ver que la factura 3 ahora está anulada
        System.out.println("Stock de L001 después de anular (no se repone stock en este prototipo): " + controlProducto.consultarStock("L001"));


        // 6. Imprimir una factura
        System.out.println("\n--- Imprimiendo Factura 1 ---");
        controlFactura.imprimirFactura(factura1.getNumero());

        System.out.println("\n--- Fin del Prototipo Ampliado ---");
    }
}