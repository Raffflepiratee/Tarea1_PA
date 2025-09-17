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
import interfaces.IUsuarioController;
import interfaces.IMaterialController;
import interfaces.IPrestamoController;
import datatypes.*;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublishController {
    private Fabrica fabrica;
    private IUsuarioController usuarioController;
    private IMaterialController materialController;
    private IPrestamoController prestamoController;
    private Endpoint endpoint;

    public PublishController() {
        fabrica = Fabrica.getInstancia();
        usuarioController = fabrica.getIControladorUsuario();
        materialController = fabrica.getIControladorMaterial();
        prestamoController = fabrica.getIControladorPrestamo();
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:8080/controlador", this);
        System.out.println("http://localhost:8080/controlador");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    // Métodos para Usuarios
    @WebMethod
    public void agregarUsuario(DtUsuario usuario) {
        try {
            usuarioController.agregarUsuario(usuario);
        } catch (Exception e) {
            // Manejo de excepciones
        }
    }

    @WebMethod
    public DtUsuario[] obtenerUsuarios() {
        List<DtUsuario> usuarios = usuarioController.obtenerUsuarios();
        return usuarios.toArray(new DtUsuario[0]);
    }

    // Métodos para Materiales
    @WebMethod
    public void agregarMaterial(DtMaterial material) {
        try {
            materialController.agregarMaterial(material);
        } catch (Exception e) {
            // Manejo de excepciones
        }
    }

    @WebMethod
    public DtMaterial[] obtenerMateriales() {
        List<DtMaterial> materiales = materialController.obtenerMateriales();
        return materiales.toArray(new DtMaterial[0]);
    }

    @WebMethod
    public DtMaterial[] obtenerMaterialesPorRango(Date fechaInicio, Date fechaFin) {
        List<DtMaterial> materiales = materialController.obtenerMaterialesPorRango(fechaInicio, fechaFin);
        return materiales.toArray(new DtMaterial[0]);
    }

    // Métodos para Préstamos
    @WebMethod
    public void agregarPrestamo(Date fechaSoli, Date fechaDev, EstadosP estadoP,
                                String correoLector, String correoBiblio, int idMaterial) {
        try {
            prestamoController.agregarPrestamo(fechaSoli, fechaDev, estadoP, correoLector, correoBiblio, idMaterial);
        } catch (Exception e) {
            // Manejo de excepciones
        }
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamos() {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamos();
        return prestamos.toArray(new DtPrestamo[0]);
    }

    @WebMethod
    public DtPrestamo[] obtenerPrestamosPendientes() {
        List<DtPrestamo> prestamos = prestamoController.obtenerDtPrestamosPendientes();
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
    public DtPrestamo[] obtenerPrestamosActivosLector(String correoLector) {
        List<DtPrestamo> prestamos = prestamoController.obtenerPrestamosActivosLector(correoLector);
        return prestamos.toArray(new DtPrestamo[0]);
    }
}
