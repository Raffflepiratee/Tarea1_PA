package logica.controladores;

import interfaces.IPrestamoController;

import java.util.ArrayList;
import java.util.List;
import logica.clases.*;

import datatypes.*;
import excepciones.PrestamoRepetidoException;
import logica.manejadores.PrestamoHandler;
import logica.manejadores.MaterialHandler;
import logica.manejadores.UsuarioHandler;

public class PrestamoController implements IPrestamoController {

    private List<Prestamo> prestamos;

    public PrestamoController() {
        super();
        this.prestamos = new ArrayList<>();
    }

    @Override
    public void agregarPrestamo(DtPrestamo prestamo, String correoLector, String correoBiblio, int idMaterial) throws PrestamoRepetidoException {
        PrestamoHandler pH = PrestamoHandler.getInstancia();

        MaterialHandler mH = MaterialHandler.getInstancia();
        Material m = mH.buscarMaterialPorId(idMaterial);

        UsuarioHandler uH = UsuarioHandler.getInstancia();
        Lector uLector = (Lector) uH.buscarUsuarioPorCorreo(correoLector);
        // Convertir de usario a lector

        UsuarioHandler uH2 = UsuarioHandler.getInstancia();
        Bibliotecario uBibliotecario = (Bibliotecario) uH2.buscarUsuarioPorCorreo(correoBiblio);

        if (existePrestamoActivo(idMaterial)) {
            throw new PrestamoRepetidoException(
                    "Ya existe un prestamo activo para este material");
        }else { // Si el prestamo no existe
            Prestamo nuevoPrestamo = new Prestamo(
                    prestamo.getFechaSoli(),
                    prestamo.getEstadoPres(),
                    prestamo.getFechaDev(),
                    uLector,
                    uBibliotecario,
                    m);
            pH.agregarPrestamoH(nuevoPrestamo);
        }
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
        PrestamoHandler pH = PrestamoHandler.getInstancia();
        return new ArrayList<>(pH.obtenerPrestamos());
    }

    @Override
    public Prestamo buscarPrestamoPorId(int id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdPrestamo() == id) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public void cambiarEstadoPrestamo(DtPrestamo Prestamo, EstadosP nuevoEstado) throws PrestamoRepetidoException {
        PrestamoHandler pH = PrestamoHandler.getInstancia();
        Prestamo prestamo = pH.buscarPrestamoPorId(Prestamo.getIdPrestamo());
        if (prestamo != null) {
            prestamo.setEstadoPres(nuevoEstado);
            actualizarPrestamo(prestamo);
        } else {
            throw new PrestamoRepetidoException(
                    "El prestamo no existe en el sistema");
        }
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        PrestamoHandler pH = PrestamoHandler.getInstancia();
        pH.actualizarPrestamoH(prestamo);
    }

    @Override
    public boolean existePrestamoActivo(int idMaterial){
        PrestamoHandler pH = PrestamoHandler.getInstancia();
        List<Prestamo> prestamos = pH.obtenerPrestamos();
        for(Prestamo p : prestamos){
            if(p.getMaterial().getIdMaterial() == idMaterial && (p.getEstadoPres() == EstadosP.PENDIENTE || p.getEstadoPres() == EstadosP.EN_CURSO)){
                return true;
            }
        }
        return false;
    }

}
