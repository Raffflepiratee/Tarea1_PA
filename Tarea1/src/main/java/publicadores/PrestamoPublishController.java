package publicadores;

import java.util.Date;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import interfaces.Fabrica;
import interfaces.IPrestamoController;
import datatypes.DtPrestamo;
import datatypes.EstadosP;
import datatypes.Zonas;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PrestamoPublishController {
    private final Fabrica fabrica;
    private final IPrestamoController prestamoController;
    private Endpoint endpoint;

    public PrestamoPublishController() {
        fabrica = Fabrica.getInstancia();
        prestamoController = fabrica.getIControladorPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8080/prestamos", this);
        System.out.println("http://localhost:8080/prestamos");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void agregarPrestamo(DtPrestamo prestamo) {
        try {
            prestamoController.agregarPrestamo(prestamo);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamos() {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamos();
        if (prestamos != null) {
            for (int i = 0; i < prestamos.size(); i++) {
                DtPrestamo p = prestamos.get(i);
                System.out.println("Prestamo[" + i + "] toString(): " + p);
                try {
                    System.out.println("Prestamo[" + i + "] material=" + p.getMaterial()
                            + " estado=" + p.getEstadoPres()
                            + " correoLector=" + p.getLector());
                } catch (Exception ex) {
                    // ignora si algún getter no existe
                }
            }
        } else {
            System.out.println("PrestamoPublishController.obtenerPrestamos: prestamos == null");
        }
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosPendientes() {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamosPendientes();
        int size = (prestamos == null) ? 0 : prestamos.size();
        System.out.println("---> linea 60 t1: prestamos.size() = " + size);

        if (prestamos != null) {
            for (int i = 0; i < prestamos.size(); i++) {
                DtPrestamo p = prestamos.get(i);
                System.out.println("Prestamo[" + i + "] toString(): " + p);
                try {
                    System.out.println("Prestamo[" + i + "] material=" + p.getMaterial()
                            + " estado=" + p.getEstadoPres()
                            + " correoLector=" + p.getLector());
                } catch (Exception ex) {
                    // ignora si algún getter no existe
                }
            }
        } else {
            System.out.println("PrestamoPublishController.obtenerPrestamosPendientes: prestamos == null");
        }
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosPorZona(Zonas zona) {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamosPorZona(zona);
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosPorBibliotecario(int idEmp) {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamoBibliotecario(idEmp);
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosPorLector(String correoLector) {
        if (correoLector == null || correoLector.trim().isEmpty()) {
            return new DtPrestamo[0];
        }
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamoLector(correoLector);
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosActivosLector(String correoLector) {
        List<DtPrestamo> prestamos = prestamoController.obtenerPrestamosActivosLector(correoLector);
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public boolean existePrestamoActivo(int idMaterial) {
        return prestamoController.existePrestamoActivo(idMaterial);
    }

    @WebMethod
    public void cambiarEstadoPrestamo(DtPrestamo prestamo, EstadosP nuevoEstado) {
        prestamoController.cambiarEstadoPrestamo(prestamo, nuevoEstado);
    }

    @WebMethod
    public void cambiarMaterialPrestamo(DtPrestamo prestamo, int nuevoMaterialID) {
        prestamoController.cambiarMaterialPrestamo(prestamo, nuevoMaterialID);
    }

    @WebMethod
    public void cambiarCorreoLectorPrestamo(DtPrestamo prestamo, String nuevoCorreo) {
        prestamoController.cambiarCorreoLectorPrestamo(prestamo, nuevoCorreo);
    }

    @WebMethod
    public void cambiarCorreoBibliotecarioPrestamo(DtPrestamo prestamo, String nuevoCorreo) {
        prestamoController.cambiarCorreoBibliotecarioPrestamo(prestamo, nuevoCorreo);
    }

    @WebMethod
    public void cambiarFechaDevolucionPrestamo(DtPrestamo prestamo, Date nuevaFecha) {
        prestamoController.cambiarFechaDevolucionPrestamo(prestamo, nuevaFecha);
    }

    @WebMethod
    public void cambiarFechaSolicitudPrestamo(DtPrestamo prestamo, Date nuevaFecha) {
        System.out.println("PrestamoPublishController.cambiarFechaSolicitudPrestamo: nuevaFecha = " + nuevaFecha);
        prestamoController.cambiarFechaSolicitudPrestamo(prestamo, nuevaFecha);
    }
}
