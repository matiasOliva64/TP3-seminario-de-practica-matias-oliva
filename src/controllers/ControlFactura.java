package controllers;

import models.Factura.Factura;
import models.Factura.ItemFactura;
import models.clientes.Cliente;
import models.product.Producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlFactura {

    private List<Factura> listaFacturas;
    private ControlProducto controlProducto; // Necesitamos el controlador de productos para gestionar el stock
    private int proximoNumeroFactura; // Para generar números de factura únicos

    public ControlFactura(ControlProducto controlProducto) {
        if (controlProducto == null) {
            throw new IllegalArgumentException("ControlProducto no puede ser nulo para ControlFactura.");
        }
        this.listaFacturas = new ArrayList<>();
        this.controlProducto = controlProducto;
        this.proximoNumeroFactura = 1; // Inicia el número de factura
        System.out.println("ControlFactura inicializado.");
    }

    /**
     * Crea una nueva factura. Este método se encarga de instanciar la factura y prepararla.
     * No se "emite" hasta que se llama al método emitirFactura.
     * @param cliente El cliente para quien se crea la factura.
     * @return El objeto Factura creado, o null si el cliente es nulo.
     */
    public Factura crearFactura(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Error: No se puede crear una factura sin un cliente válido.");
            return null;
        }
        Factura nuevaFactura = new Factura(proximoNumeroFactura++, cliente, LocalDate.now());
        // La factura se añade a la lista solo cuando se emite, o se podría añadir aquí y cambiar su estado
        // Por simplicidad en este prototipo, la agregamos aquí y su estado inicial es "Emitida"
        System.out.println("Factura N°" + nuevaFactura.getNumero() + " creada para " + cliente.getNombre() + ".");
        return nuevaFactura;
    }

    /**
     * Emite una factura, lo que implica verificar stock y retirarlo.
     * @param factura La factura a emitir (que ya debe contener sus ítems).
     * @return true si la factura se emitió exitosamente, false si hay problemas de stock o la factura no es válida.
     */
    public boolean emitirFactura(Factura factura) {
        if (factura == null) {
            System.out.println("Error: No se puede emitir una factura nula.");
            return false;
        }
        if (factura.getItems().isEmpty()) {
            System.out.println("Error: La factura " + factura.getNumero() + " no tiene ítems para emitir.");
            return false;
        }
        if (!factura.getEstado().equals("Emitida")) {
            System.out.println("Error: La factura " + factura.getNumero() + " no está en estado 'Emitida' para ser procesada.");
            return false;
        }
        // 1. Verificar disponibilidad de stock para todos los ítems antes de proceder
        for (ItemFactura item : factura.getItems()) {
            Producto producto = item.getProducto();
            int cantidadSolicitada = item.getCantidad();
            int stockActual = controlProducto.consultarStock(producto.getCodigo());

            if (stockActual == -1 || stockActual < cantidadSolicitada) {
                System.out.println("Error al emitir factura " + factura.getNumero() +
                        ": Stock insuficiente para el producto '" + producto.getNombre() +
                        "'. Stock actual: " + stockActual + ", solicitado: " + cantidadSolicitada);
                // Si el stock no es suficiente para algún producto, no emitimos la factura.
                return false;
            }
        }
        // 2. Retirar stock para cada ítem
        for (ItemFactura item : factura.getItems()) {
            Producto producto = item.getProducto();
            int cantidadSolicitada = item.getCantidad();
            boolean stockRetirado = controlProducto.retirarStockDeProducto(producto.getCodigo(), cantidadSolicitada);
            if (!stockRetirado) {
                // Esto no debería pasar si la verificación inicial fue exitosa, pero es una capa de seguridad.
                System.out.println("Error inesperado al retirar stock para producto '" + producto.getNombre() + "'. Abortando emisión.");
                // Aquí deberías considerar un mecanismo de "rollback" si ya se retiró stock de otros productos.
                return false;
            }
        }
        // 3. Agregar la factura a la lista de facturas emitidas
        this.listaFacturas.add(factura);
        factura.setEstado("Emitida"); // Asegurarse del estado final
        System.out.println("Factura N°" + factura.getNumero() + " emitida exitosamente para cliente " + factura.getCliente().getNombre() + ".");
        System.out.println(factura); // Imprimir la factura completa
        return true;
    }

    /**
     * Anula una factura existente.
     * --- RFS23: El sistema debe permitir anular una factura ---
     * @param numeroFactura El número de la factura a anular.
     * @return true si la factura fue anulada, false si no se encontró o ya estaba anulada.
     */
    public boolean anularFactura(int numeroFactura) {
        Factura factura = buscarFacturaPorNumero(numeroFactura);
        if (factura != null) {
            if (factura.anularFactura()) {
                // Por simplicidad, no repondremos stock en este prototipo.
                return true;
            }
        } else {
            System.out.println("Error: Factura N°" + numeroFactura + " no encontrada para anular.");
        }
        return false;
    }

    /**
     * Busca una factura por su número.
     * @param numero El número de la factura a buscar.
     * @return El objeto Factura si se encuentra, o null.
     */
    public Factura buscarFacturaPorNumero(int numero) {
        for (Factura f : listaFacturas) {
            if (f.getNumero() == numero) {
                return f;
            }
        }
        return null;
    }

    /**
     * Lista todas las facturas (emitidas y anuladas).
     * --- RFS22: El sistema debe permitir listar facturas emitidas y anuladas ---
     */
    public void listarFacturas() {
        if (listaFacturas.isEmpty()) {
            System.out.println("No hay facturas registradas en el sistema.");
            return;
        }
        System.out.println("\n--- Listado de Facturas ---");
        for (Factura f : listaFacturas) {
            System.out.println(f);
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Simula la impresión de una factura.
     * --- RFS21: El sistema debe permitir imprimir una factura ---
     * @param numeroFactura El número de la factura a imprimir.
     * @return true si la factura se encontró y se "imprimió", false si no.
     */
    public boolean imprimirFactura(int numeroFactura) {
        Factura factura = buscarFacturaPorNumero(numeroFactura);
        if (factura != null) {
            System.out.println("\n--- Simulación de Impresión de Factura N°" + numeroFactura + " ---");
            System.out.println(factura); // Reutilizamos el toString() para la "impresión"
            System.out.println("--- Impresión finalizada ---");
            return true;
        } else {
            System.out.println("Error: Factura N°" + numeroFactura + " no encontrada para imprimir.");
            return false;
        }
    }
}
