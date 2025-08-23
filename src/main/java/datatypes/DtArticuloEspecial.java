package datatypes;

public class DtArticuloEspecial extends DtMaterial {
    
    private String descripcion;
    private float peso;
    private float dimFisicas;

    public DtArticuloEspecial(int idMaterial, Date fechaRegistro, String descripcion, float peso, float dimFisicas) {
        super(idMaterial, fechaRegistro);
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimFisicas = dimFisicas;
    
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public float getDimFisicas() {
        return dimFisicas;
    }

    @Override
    public String toString() {
        return "DtArticuloEspecial [descripcion=" + descripcion + ", peso=" + peso + ", dimFisicas=" + dimFisicas
                + ", toString()=" + super.toString() + "]";
    }
}
