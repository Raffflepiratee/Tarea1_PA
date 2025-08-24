package interfaces;

import java.util.Date;
import java.util.List;
import datatypes.EstadosP;
import logica.clases.Prestamo;

public interface IPrestamoController {
    void agregarPrestamo(Prestamo prestamo);

    void eliminarPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamos();

    Prestamo buscarPrestamoPorId(int id);
}
