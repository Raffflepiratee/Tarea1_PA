package interfaces;

import java.util.Date;
import java.util.List;
import datatypes.*;
import logica.clases.Prestamo;
import excepciones.PrestamoRepetidoException;

public interface IPrestamoController {
    void agregarPrestamo(Date fechaSoli, Date fechaDev, EstadosP estadoP, String correoLector, String correoBiblio, int idMaterial) throws PrestamoRepetidoException;

    void eliminarPrestamo(Prestamo prestamo);

    List<DtPrestamo> obtenerDtPrestamos();

    Prestamo buscarPrestamoPorId(int id);

    void cambiarEstadoPrestamo(DtPrestamo Prestamo, EstadosP nuevoEstado);

    void actualizarPrestamo(Prestamo prestamo);

    boolean existePrestamoActivo(int Material);

    public List<DtPrestamo> obtenerDtPrestamoBibliotecario(int idEmp);
}
