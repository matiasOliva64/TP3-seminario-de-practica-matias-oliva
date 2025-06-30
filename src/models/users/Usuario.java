package models.users;

public class Usuario {

    private int id;
    private String userName;
    private String password;
    private Rol rol;

    public Usuario(int id,String userName,String password,Rol rol){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String userName,String password,Rol rol){
        this.userName = userName;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String userName,Rol rol){
        this.userName = userName;
        this.rol = rol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                '}';
    }
}
