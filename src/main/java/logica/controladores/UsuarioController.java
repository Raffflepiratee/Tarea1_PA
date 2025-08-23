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

    public UsuarioController(){
        super();
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void agregarUsuario(String nombre, String correo) {
        UsuarioHandler uh = UsuarioHandler.getInstancia();
        Usuario u = uh.buscarUsuarioPorCorreo(correo);
        if (u == null) {
            int idEmp = 1;
            u = new Bibliotecario(nombre, correo, idEmp);
        }
        uh.agregarUsuarioH(u);
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
