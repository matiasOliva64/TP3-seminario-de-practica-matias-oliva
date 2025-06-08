package models.product;

public class CategoriaProducto {

    //Atributo de clase
    private String nombre;

    //Constructor de la clase
    public CategoriaProducto(String nombre){
        this.nombre = nombre;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodo to String
    @Override
    public String toString() {
        return "CategoriaProducto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
