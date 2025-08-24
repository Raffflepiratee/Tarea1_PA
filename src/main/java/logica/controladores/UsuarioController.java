package logica.controladores;

import interfaces.IUsuarioController;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import logica.clases.Usuario;
import logica.clases.Bibliotecario;
import logica.clases.Lector;
import logica.manejadores.UsuarioHandler;
import excepciones.UsuarioRepetidoException;
import datatypes.DtUsuario;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import persistencia.*;

public class UsuarioController implements IUsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        super();
        this.usuarios = new ArrayList<>();
    }

    // ARREGLAR
    @Override
    public void agregarUsuario(DtUsuario usuario) throws UsuarioRepetidoException{
        UsuarioHandler uh = UsuarioHandler.getInstancia();
        Usuario nuevoUsuario = uh.buscarUsuarioPorCorreo(usuario.getCorreo());
        if (nuevoUsuario != null)
            throw new UsuarioRepetidoException(
                "El usuario con correo " + usuario.getCorreo() + " ya existe en el sistema");
        if (usuario instanceof DtLector) {
            nuevoUsuario = new Lector(usuario.getNombre(), usuario.getCorreo(),
                    ((DtLector) usuario).getFechaIngreso(), ((DtLector) usuario).getEstadoUsuario(),
                    ((DtLector) usuario).getZona(), ((DtLector) usuario).getDireccion());
        }
        else if (usuario instanceof DtBibliotecario) {
            nuevoUsuario = new Bibliotecario(usuario.getNombre(), usuario.getCorreo(),((DtBibliotecario) usuario).getIdEmp());
        }
        uh.agregarUsuarioH(nuevoUsuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }

}
