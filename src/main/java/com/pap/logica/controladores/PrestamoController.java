package com.pap.logica.controladores;

import java.util.ArrayList;
import java.util.List;
import com.pap.logica.clases.Prestamo;

public class PrestamoController {

    private List<Prestamo> prestamos;

    public PrestamoController() {
        this.prestamos = new ArrayList<>();
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        prestamos.remove(prestamo);
    }

    public List<Prestamo> obtenerPrestamos() {
        return new ArrayList<>(prestamos);
    }

    public Prestamo buscarPrestamoPorId(int id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdPrestamo() == id) {
                return prestamo;
            }
        }
        return null;
    }

}
