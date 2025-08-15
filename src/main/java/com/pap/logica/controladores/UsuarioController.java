package com.pap.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import com.pap.logica.clases.Usuario;


public class UsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }

}
