package utilities;

import enums.FormMode;

public class Utilities {

    //metodo para mostrar distintos titulos en la ventana registrar o modificar
    public static String getTitleForMode(FormMode mode,String entityName){
        return switch (mode){
            case REGISTER -> "Registro de " + entityName.toLowerCase();
            case MODIFY -> "Modificación de " + entityName.toLowerCase();
            case VIEW -> "Detalles de " + entityName.toLowerCase();
            default -> "Dialogo desconocido";
        };
    }

}
