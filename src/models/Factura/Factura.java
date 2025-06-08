package models.Factura;

import models.clientes.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numero;
    private Cliente cliente; // Referencia al cliente asociado a la factura
    private LocalDate fecha;
    private double total; // El total se calculará, pero se guarda para consistencia
    private String estado; // Ej: "Emitida", "Anulada"
    private List<ItemFactura> items; // Una factura tiene muchos ítems

    // Constructor
    public Factura(int numero, Cliente cliente, LocalDate fecha) {
        if (cliente == null) {
            throw new IllegalArgumentException("La factura debe estar asociada a un cliente.");
        }
        this.numero = numero;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = 0.0; // Se inicializa en 0 y se calcula al agregar ítems
        this.estado = "Emitida"; // Estado inicial por defecto
        this.items = new ArrayList<>();
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    // Setters (el setter para 'total' no es público, se calcula)
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Agrega un ítem a la factura y actualiza el total.
     * @param item El ItemFactura a agregar.
     */
    public void agregarItem(ItemFactura item) {
        if (item != null) {
            this.items.add(item);
            calcularTotal(); // Recalcular el total cada vez que se agrega un ítem
        }
    }

    /**
     * Calcula el total de la factura sumando los subtotales de todos sus ítems.
     */
    private void calcularTotal() {
        double nuevoTotal = 0;
        for (ItemFactura item : items) {
            nuevoTotal += item.calcularSubtotal();
        }
        // RFS24: "La emisión de facturas debe contemplar requisitos fiscales nacionales y locales, por ejemplo: IVA"
        // Aquí podrías aplicar el IVA u otros impuestos. Por simplicidad, asumiremos un IVA del 21%.
        double iva = 0.21; // 21% de IVA
        this.total = nuevoTotal * (1 + iva); // Sumar el IVA al total
    }

    /**
     * Cambia el estado de la factura a "Anulada".
     * @return true si la factura fue anulada, false si ya estaba anulada o el estado no lo permite.
     */
    public boolean anularFactura() {
        if (!this.estado.equals("Anulada")) {
            this.estado = "Anulada";
            System.out.println("Factura " + this.numero + " ha sido anulada.");
            return true;
        } else {
            System.out.println("La factura " + this.numero + " ya estaba anulada.");
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura N°: ").append(numero).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append(" (DNI: ").append(cliente.getDni()).append(")\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("--- Detalle de Items ---\n");
        for (ItemFactura item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("------------------------\n");
        sb.append("TOTAL (IVA incluido): $").append(String.format("%.2f", total)).append("\n");
        return sb.toString();
    }
}
