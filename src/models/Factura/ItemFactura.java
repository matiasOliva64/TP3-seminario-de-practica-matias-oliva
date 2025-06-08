package models.Factura;

import models.product.Producto;

public class ItemFactura {

        private Producto producto; // Referencia al producto que se está facturando
        private int cantidad;
        private double precioUnitario; // Precio del producto en el momento de la venta

        // Constructor
        public ItemFactura(Producto producto, int cantidad, double precioUnitario) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto en ItemFactura no puede ser nulo.");
            }
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad de ItemFactura debe ser positiva.");
            }
            if (precioUnitario < 0) {
                throw new IllegalArgumentException("El precio unitario de ItemFactura no puede ser negativo.");
            }
            this.producto = producto;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

        // Getters
        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        // Setters (considerar si son necesarios, ya que un item de factura es generalmente inmutable una vez creado)
        public void setCantidad(int cantidad) {
            if (cantidad > 0) {
                this.cantidad = cantidad;
            } else {
                System.out.println("La cantidad debe ser positiva.");
            }
        }

        public void setPrecioUnitario(double precioUnitario) {
            if (precioUnitario >= 0) {
                this.precioUnitario = precioUnitario;
            } else {
                System.out.println("El precio unitario no puede ser negativo.");
            }
        }

        /**
         * Calcula el subtotal de este item de factura.
         * @return El subtotal (cantidad * precioUnitario).
         */
        public double calcularSubtotal() {
            return this.cantidad * this.precioUnitario;
        }

        @Override
        public String toString() {
            return "ItemFactura{" +
                    "producto=" + producto.getNombre() + // Mostrar el nombre del producto
                    ", cantidad=" + cantidad +
                    ", precioUnitario=" + String.format("%.2f", precioUnitario) +
                    ", subtotal=" + String.format("%.2f", calcularSubtotal()) +
                    '}';
        }
}