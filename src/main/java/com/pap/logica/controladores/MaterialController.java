package com.pap.logica.controladores;

import java.util.List;
import java.util.ArrayList;

import com.pap.logica.clases.Material;
import com.pap.logica.clases.Libro;

public class MaterialController {

    private List<Material> materiales;

    public MaterialController() {
        this.materiales = new ArrayList<>();
    }

    public void agregarMaterial(Material material) {
        materiales.add(material);
    }

    public void eliminarMaterial(Material material) {
        materiales.remove(material);
    }

    public List<Material> obtenerMateriales() {
        return new ArrayList<>(materiales);
    }

    public Material buscarMaterialPorId(int id) {
        for (Material material : materiales) {
            if (material.getIdMaterial() == id) {
                return material;
            }
        }
        return null; 
    }

    //libros
    public List<Material> buscarLibros() {
        List<Material> libros = new ArrayList<>();
        for (Material material : materiales) {
            if (material instanceof Libro) {
                libros.add(material);
            }
        }
        return libros;
    }

    // articulos especiales
    public List<Material> buscarArticulosEspeciales() {
        List<Material> articulosEspeciales = new ArrayList<>();
        for (Material material : materiales) {
            if (!(material instanceof Libro)) {
                articulosEspeciales.add(material);
            }
        }
        return articulosEspeciales;
    }

    // Eventualmente se van a agregar / eliminar funciones
}
