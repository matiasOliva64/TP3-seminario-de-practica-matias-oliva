package models.users;

public class Rol {

    private int id;
    private String rolName;

    public Rol(int id,String rolName){
        this.id = id;
        this.rolName = rolName;
    }

    public Rol(String RolName){
        this.rolName = RolName;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return rolName;
    }
}
