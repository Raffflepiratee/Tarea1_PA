package datatypes;

public class DtBibliotecario extends DtUsuario {

    private int IdEmp;

    public DtBibliotecario(String nombre, String email, int IdEmp) {
        super(nombre, email);
        this.IdEmp = IdEmp;
    }

    public int getIdEmp() {
        return IdEmp;
    }

    @Override
    public String toString() {
        return super.toString() + "\nID EMPLEADO = " + IdEmp;
    }
    
}
