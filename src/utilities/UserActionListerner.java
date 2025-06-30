package utilities;

//interface para refrescar la tabla una vez hecha las operaciones de registro, modificacion y borrado
public interface UserActionListerner {
    void onUserAdded();
    void onUserUpdated();
    void onUserDeleted();
}
