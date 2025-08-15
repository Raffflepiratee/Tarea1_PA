package com.pap.logica;

public class Bibliotecario extends Usuario{
    private int idEmp; 

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
