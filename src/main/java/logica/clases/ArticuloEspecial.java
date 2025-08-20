package logica.clases;
import java.util.Date;

public class ArticuloEspecial extends Material {
    private String descripcion; 
    private float peso;
    private float dimFisicas;
    
    public ArticuloEspecial(int idMaterial, Date fechaRegistro, String descripcion, float peso, float dimFisicas) {
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setDimFisicas(float dimFisicas) {
        this.dimFisicas = dimFisicas;
    }
    
    
}
