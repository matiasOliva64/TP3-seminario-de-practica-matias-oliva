package models.product;

public class Stock {

    private int cantidadDisponible;

    /*Constructor*/
    public Stock(int cantidadInicial){
        if(cantidadInicial >= 0){
            this.cantidadDisponible = cantidadInicial;
        }else{
            System.out.println("La cantidad inicial no puede ser negativa");
            this.cantidadDisponible = 0;
        }
    }

    /*Getter*/
    public int getCantidadDisponible(){
        return cantidadDisponible;
    }

    /*Setter*/
    public void setCantidadDisponible(int cantidadDisponible){
        if(cantidadDisponible >= 0){
            this.cantidadDisponible = cantidadDisponible;
        }else{
            System.out.println("La cantidad disponible no puede ser negativa");
        }
    }

    /*Metodos para modificar stock*/
    public void agregar(int cantidad){
        if(cantidad > 0){
            this.cantidadDisponible += cantidad;
        }else{
            System.out.println("La cantidad a agregar debe ser positiva");
        }
    }

    public boolean retirar(int cantidad){
        if(cantidad > 0 && this.cantidadDisponible >= cantidad){
            this.cantidadDisponible -= cantidad;
            return true;
        } else if (cantidad <= 0) {
            System.out.println("La cantidad a retirar debe se positiva");
            return false;
        }else {
            System.out.println("No hay suficiente stock. Cantidad disponible: " + this.cantidadDisponible);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
