package com.pap.logica.manejadores;

public class PrestamoHandler {
    private static PrestamoHandler instancia = null;

    private PrestamoHandler(){}

    public static PrestamoHandler getInstancia() {
        if (instancia == null)
            instancia = new PrestamoHandler();
        return instancia;
    }
    
}
