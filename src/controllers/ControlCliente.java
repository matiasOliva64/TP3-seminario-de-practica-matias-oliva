package controllers;

import models.clientes.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ControlCliente {
    private List<Cliente> listaClientes;

    public ControlCliente() {
        this.listaClientes = new ArrayList<>();
        System.out.println("ControlCliente inicializado.");
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * @param cliente El objeto Cliente a registrar.
     * @return true si el cliente se registró exitosamente, false si ya existe un cliente con el mismo DNI.
     */
    public boolean registrarCliente(Cliente cliente) {
        // Validación: verificar si ya existe un cliente con el mismo DNI
        for (Cliente c : listaClientes) {
            if (c.getDni().equals(cliente.getDni())) {
                System.out.println("Error: Ya existe un cliente con el DNI " + cliente.getDni());
                return false;
            }
        }
        listaClientes.add(cliente);
        System.out.println("Cliente '" + cliente.getNombre() + " " + cliente.getApellido() + "' registrado exitosamente.");
        return true;
    }

    /**
     * Consulta un cliente por su DNI.
     * @param dni El DNI del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o null si no existe.
     */
    public Cliente consultarCliente(String dni) {
        for (Cliente c : listaClientes) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }
        System.out.println("Cliente con DNI " + dni + " no encontrado.");
        return null;
    }

    /**
     * Modifica un cliente existente.
     * @param clienteModificado El objeto Cliente con los datos actualizados.
     * @return true si el cliente se modificó, false si no se encontró.
     */
    public boolean modificarCliente(Cliente clienteModificado) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getDni().equals(clienteModificado.getDni())) {
                listaClientes.set(i, clienteModificado);
                System.out.println("Cliente '" + clienteModificado.getNombre() + " " + clienteModificado.getApellido() + "' modificado exitosamente.");
                return true;
            }
        }
        System.out.println("Error: Cliente con DNI " + clienteModificado.getDni() + " no encontrado para modificar.");
        return false;
    }

    /**
     * Elimina un cliente del sistema por su DNI.
     * @param dni El DNI del cliente a eliminar.
     * @return true si el cliente fue eliminado, false si no se encontró.
     */
    public boolean eliminarCliente(String dni) {
        Cliente clienteAEliminar = null;
        for (Cliente c : listaClientes) {
            if (c.getDni().equals(dni)) {
                clienteAEliminar = c;
                break;
            }
        }
        if (clienteAEliminar != null) {
            listaClientes.remove(clienteAEliminar);
            System.out.println("Cliente con DNI " + dni + " eliminado exitosamente.");
            return true;
        } else {
            System.out.println("Error: Cliente con DNI " + dni + " no encontrado para eliminar.");
            return false;
        }
    }

    /**
     * Lista todos los clientes registrados en el sistema.
     */
    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
            return;
        }
        System.out.println("\n--- Listado de Clientes ---");
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
        System.out.println("--------------------------");
    }
}
