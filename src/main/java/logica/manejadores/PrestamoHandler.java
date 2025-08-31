package logica.manejadores;

import logica.clases.Prestamo;
import datatypes.Zonas;

import persistencia.Conexion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class PrestamoHandler {
    private static PrestamoHandler instancia = null;

    private PrestamoHandler() {
    }

    public static PrestamoHandler getInstancia() {
        if (instancia == null)
            instancia = new PrestamoHandler();
        return instancia;
    }

    public void agregarPrestamoH(Prestamo p) {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public Prestamo buscarPrestamoPorId(int id) {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        return em.find(Prestamo.class, id);
    }

    public void actualizarPrestamoH(Prestamo p) {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    public List<Prestamo> obtenerPrestamos() {
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
        return new ArrayList<>(prestamos);
    }

    public List<Prestamo> obtenerPrestamosPorBibliotecario(int idEmp){
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        List<Prestamo> prestamosPorBibliotecatrio = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.bibliotecario.idEmp = :idEmp", Prestamo.class)
            .setParameter("idEmp", idEmp)
            .getResultList();
        return new ArrayList<>(prestamosPorBibliotecatrio);
    }

    public List<Prestamo> obtenerPrestamosPorZona(Zonas zona){
        Conexion c = Conexion.getInstancia();
        EntityManager em = c.getEntityManager();
        List<Prestamo> prestamosPorZona = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.lector.zona = :zona", Prestamo.class)
            .setParameter("zona", zona)
            .getResultList();
        return new ArrayList<>(prestamosPorZona);
    }

}