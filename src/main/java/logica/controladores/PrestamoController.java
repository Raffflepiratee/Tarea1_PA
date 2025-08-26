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
    public void agregarPrestamo(Prestamo prestamo, String correoLector, String correoBiblio, int idMaterial) {
        PrestamoHandler pH = PrestamoHandler.getInstancia();
        Prestamo p = pH.buscarPrestamoPorId(prestamo.getIdPrestamo());
        //TENEMOS DUDAS CON EL ACCESO A LOS OTROS CONTROLADORES
        //SERA QUE ES UN UNICO CONTROLADOR CON 3 MANEJADORES?
        if (p == null) {
            prestamos.add(prestamo);
            pH.agregarPrestamoH(prestamo);

        }
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
