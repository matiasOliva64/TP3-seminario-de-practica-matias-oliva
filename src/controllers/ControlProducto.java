package controllers;

import models.product.Producto;

import java.util.ArrayList;
import java.util.List;

public class ControlProducto {
    private List<Producto> listaProductos;

    public ControlProducto(){
        this.listaProductos = new ArrayList<>();
        System.out.println("ControlProducto inicializado.");
    }

    //Metodos que simulan el CRUD con una base de datos
    public boolean registrarProducto(Producto producto){
        for(Producto p : listaProductos){
            if(p.getCodigo().equals(producto.getCodigo())){
                System.out.println("Error: Ya existe un producto con el codigo: " + producto.getCodigo());
                return false;
            }
        }
        listaProductos.add(producto);
        System.out.println("Producto " + producto.getNombre() + " Registrado exitosamente.");
        return true;
    }

    public Producto consultarProducto(String codigo){
        for(Producto p : listaProductos){
            if(p.getCodigo().equals(codigo)){
                return p;
            }
        }
        System.out.println("Producto con codigo " + codigo + " no encontrado.");
        return null;
    }

    public int consultarStock(String codigo){
        Producto producto = consultarProducto(codigo);
        if(producto != null){
            return producto.getStock().getCantidadDisponible();
        }
        return -1;
    }

    public void listarProductos(){
        if(listaProductos.isEmpty()){
            System.out.println("No hay productos registrados en el sistema.");
            return;
        }
        System.out.println("\n--- Listado de Productos ---");
        for(Producto p : listaProductos){
            System.out.println(p);
        }
        System.out.println("-----------------------------");
    }

    public boolean modificarProducto(Producto productoModificado){
        for(int i = 0;i < listaProductos.size();i++){
            if(listaProductos.get(i).getCodigo().equals(productoModificado.getCodigo())){
                listaProductos.set(i,productoModificado);
                System.out.println("Producto " + productoModificado.getNombre() + " modificado exitosamente.");
                return true;
            }
        }
        System.out.println("Error: Producto con codigo " + productoModificado.getCodigo() + " no encontrado para modificar.");
        return false;
    }

    public boolean eliminarProducto(String codigo){
        Producto productoDelete = null;
        for(Producto p : listaProductos){
            if(p.getCodigo().equals(codigo)){
                productoDelete = p;
                break;
            }
        }
        if(productoDelete != null){
            listaProductos.remove(productoDelete);
            System.out.println("Producto con codigo " + codigo + " Eliminado exitosamente.");
            return true;
        }else{
            System.out.println("Error: Producto con codigo " + codigo + " no encontrado para eliminar.");
            return false;
        }
    }

    public boolean agregarStockToProducto(String codigo, int cantidad){
        Producto producto = consultarProducto(codigo);
        if(producto != null){
            producto.getStock().agregar(cantidad);
            return true;
        }
        return false;
    }

    public boolean retirarStockDeProducto(String codigo, int cantidad){
        Producto producto = consultarProducto(codigo);
        if(producto != null){
            return producto.getStock().retirar(cantidad);
        }
        return false;
    }


}
