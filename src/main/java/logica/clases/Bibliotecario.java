package logica.clases;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Usuario{
    private int idEmp; 
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
