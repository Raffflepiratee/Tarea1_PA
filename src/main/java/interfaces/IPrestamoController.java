package interfaces;

import java.util.Date;
import java.util.List;
import datatypes.*;
import logica.clases.Prestamo;
import excepciones.PrestamoRepetidoException;

public interface IPrestamoController {
    void agregarPrestamo(DtPrestamo prestamo, String correoLector, String correoBiblio, int idMaterial) throws PrestamoRepetidoException;

    void eliminarPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamos();

    Prestamo buscarPrestamoPorId(int id);

    void cambiarEstadoPrestamo(DtPrestamo Prestamo, EstadosP nuevoEstado) throws PrestamoRepetidoException;

    void actualizarPrestamo(Prestamo prestamo);

    boolean existePrestamoActivo(int Material);
}
