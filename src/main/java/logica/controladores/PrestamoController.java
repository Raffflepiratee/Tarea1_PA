package logica.controladores;

import interfaces.IPrestamoController;

import java.util.ArrayList;
import java.util.List;
import logica.clases.Prestamo;

public class PrestamoController implements IPrestamoController {

    private List<Prestamo> prestamos;

    public PrestamoController() {
        super();
        this.prestamos = new ArrayList<>();
    }

    @Override
    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
        return new ArrayList<>(prestamos);
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

}
