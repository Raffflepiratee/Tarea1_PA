package interfaces;

public interface IUsuarioController {
    void agregarUsuario(String nombre, String correo);
    void eliminarUsuario(logica.clases.Usuario usuario);
    java.util.List<logica.clases.Usuario> obtenerUsuarios();
    logica.clases.Usuario buscarUsuarioPorCorreo(String correo);
}