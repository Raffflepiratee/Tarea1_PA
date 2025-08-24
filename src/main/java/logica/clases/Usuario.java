package logica.clases;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    private String correo;
    private String nombre;

    public Usuario() {
        super();
    }

    public Usuario(String nombre, String correo) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
