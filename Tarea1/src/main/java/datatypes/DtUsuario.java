package datatypes;

public class DtUsuario {
    private String nombre;
    private String correo;

    public DtUsuario(String nombre, String correo) {
        super();
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "NOMBRE = " + nombre + "\nCORREO = " + correo;
    }
}
