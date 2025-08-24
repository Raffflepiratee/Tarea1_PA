package interfaces;

import java.util.List;
import logica.clases.Usuario;
import excepciones.UsuarioRepetidoException;
import datatypes.DtUsuario;

public interface IUsuarioController {
    void agregarUsuario(DtUsuario usuario) throws UsuarioRepetidoException;

    void eliminarUsuario(Usuario usuario);

    List<Usuario> obtenerUsuarios();

    Usuario buscarUsuarioPorCorreo(String correo);
}