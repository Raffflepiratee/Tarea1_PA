package logica.manejadores;

import logica.clases.Usuario;

import persistencia.Conexion;
import javax.persistence.EntityManager;

public class UsuarioHandler {
    private static UsuarioHandler instancia = null;

    private UsuarioHandler() {
    }

    public static UsuarioHandler getInstancia() {
        if (instancia == null)
            instancia = new UsuarioHandler();
        return instancia;
    }

    public void agregarUsuarioH(Usuario u) {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        return em.find(Usuario.class, correo);
    }

}
