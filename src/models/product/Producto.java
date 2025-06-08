package models.product;

public class Producto {

    /*Atributos de la clase producto - Solo se acceden al crear constructor y setters y getters*/
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stockMinimo;
    private Stock stock;
    private CategoriaProducto categoria;
    private Marca marca;
    private Proveedor proveedor;

    /*Constructor*/
    public Producto(String codigo, String nombre, String descripcion, double precio, int stockMinimo,
                    int cantInicialStock, CategoriaProducto categoria, Marca marca,
                    Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.stock = new Stock(cantInicialStock);
        this.categoria = categoria;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    /*Getters*/
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public Stock getStock() {
        return stock;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    /*Setters*/

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setStockMinimo(int stockMinimo) {
        if(stockMinimo >= 0){
            this.stockMinimo = stockMinimo;
        }else{
            System.out.println("El stock minimo no puede ser negativo");
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }else{
            System.out.println("El precio no puede ser negativo");
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*Metodos Relacionados con Stock (Delagando comportamiento a la clase stock)*/


    @Override
    public String toString() {
        String marcaNombre = (marca != null) ? marca.getNombre() : "N/A";
        String categoriaNombre = (categoria != null) ? categoria.getNombre() : "N/A";
        String proveedorNombre = (proveedor != null) ? proveedor.getNombre() : "N/A";
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stockActual=" + stock.getCantidadDisponible() +
                ", stockMinimo=" + stockMinimo +
                ", marca=" + marcaNombre +
                ", categoria=" + categoriaNombre +
                ", proveedorPrincipal=" + proveedorNombre +
                '}';
    }


}
