package logica.controladores;

import interfaces.IUsuarioController;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import logica.clases.Usuario;
import logica.clases.Bibliotecario;
import logica.clases.Lector;
import logica.manejadores.UsuarioHandler;
import datatypes.*;
import interfaces.*;
import persistencia.*;

public class UsuarioController implements IUsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        super();
        this.usuarios = new ArrayList<>();
    }

    // ARREGLAR
    @Override
    public void agregarUsuario(DtUsuario usuario) /* throws UsuarioRepetidoException */ {
        UsuarioHandler uh = UsuarioHandler.getInstancia();
        Usuario existente = uh.buscarUsuarioPorCorreo(usuario.getCorreo());
        if (existente != null)
            /*
             * throw new UsuarioRepetidoException(
             * "El usuario con correo " + usuario.getCorreo() + " ya existe en el sistema");
             */
            System.out.println("El usuario con correo " + usuario.getCorreo() + " ya existe en el sistema");
        Usuario nuevoUsuario = null;
        if (usuario instanceof DtLector) {
            DtLector dtLector = (DtLector) usuario;
            nuevoUsuario = new Lector(
                    dtLector.getNombre(),
                    dtLector.getCorreo(),
                    dtLector.getDireccion(),
                    dtLector.getZona(),
                    dtLector.getEstadoUsuario(),
                    dtLector.getFechaIngreso());
        } else if (usuario instanceof DtBibliotecario) {
            DtBibliotecario dtBiblio = (DtBibliotecario) usuario;
            nuevoUsuario = new Bibliotecario(
                    dtBiblio.getNombre(),
                    dtBiblio.getCorreo(),
                    dtBiblio.getIdEmp());
        }

        if (nuevoUsuario != null) {
            uh.agregarUsuarioH(nuevoUsuario);
        }
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
