package utilities;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.Arrays;

public class PasswordHasherManager {

    private static final int COST_FACTOR = 12;

    //metodo para hashear la contraseña de usuario
    public static String hashPassword(String plainTextPassword){
        if(plainTextPassword == null || plainTextPassword.isEmpty()){
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía!");
        }
        char[] passwordChars = plainTextPassword.toCharArray();
        try {
            byte[] hashedPassword = BCrypt.withDefaults().hash(COST_FACTOR,passwordChars);
            return new String(hashedPassword);
        }finally {
            Arrays.fill(passwordChars,'\0');
        }
    }

    //metodo para verificar si una contraseña es correcta
    public static boolean verifyPassword(String plainTextPassword,String hashedPassword){
        if(plainTextPassword == null || hashedPassword == null || plainTextPassword.isEmpty() || hashedPassword.isEmpty()){
            return false;
        }
        char[] plainTextChars = plainTextPassword.toCharArray();
        char[] hashedChars = hashedPassword.toCharArray();
        try {
            BCrypt.Result resutl = BCrypt.verifyer().verify(plainTextChars,hashedChars);
            return resutl.verified;
        }finally {
            Arrays.fill(plainTextChars, '\0');
            Arrays.fill(hashedChars,'\0');
        }
    }
}
