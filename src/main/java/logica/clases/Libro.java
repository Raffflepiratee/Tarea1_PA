package com.pap.logica.clases;
import java.util.Date;

public class Libro extends Material{
    private String titulo;
    private int cantPag;

    public Libro(int idMaterial, Date fechaRegistro, String titulo, int cantPag) {
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantPag(int cantPag) {
        this.cantPag = cantPag;
    }

}
