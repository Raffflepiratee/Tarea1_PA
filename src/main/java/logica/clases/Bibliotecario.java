package logica.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Bibliotecario extends Usuario {
    private int idEmp; 
    @OneToMany(mappedBy = "bibliotecario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos = new ArrayList<Prestamo>();

    public Bibliotecario(String nombre, String correo, int idEmp) {
        super(nombre, correo);
        this.idEmp = idEmp;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }
}
