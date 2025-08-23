package datatypes;

public class DtLibro {
    private String titulo;
    private int cantPag;

    public DtLibro(int idMaterial, Date fechaRegistro, String titulo, int cantPag) {
        super(idMaterial, fechaRegistro);
        this.titulo = titulo;
        this.cantPag = cantPag;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCantPag() {
        return cantPag;
    }

    @Override
    public String toString() {
        return "DtLibro [titulo=" + titulo + ", cantPag=" + cantPag + ", toString()=" + super.toString() + "]";
    }
}
