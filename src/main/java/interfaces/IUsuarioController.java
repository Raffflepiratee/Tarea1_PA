package interfaces;

import java.util.List;
import logica.clases.Usuario;

public interface IUsuarioController {
    void agregarUsuario(String nombre, String correo);

    void eliminarUsuario(Usuario usuario);

    List<Usuario> obtenerUsuarios();

    Usuario buscarUsuarioPorCorreo(String correo);
}