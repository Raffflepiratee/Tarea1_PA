package logica.controladores;

import interfaces.IMaterialController;

import java.util.List;
import java.util.ArrayList;

import logica.clases.Material;
import logica.clases.Libro;
import logica.clases.ArticuloEspecial;
import logica.manejadores.MaterialHandler;
import java.util.Date;

public class MaterialController implements IMaterialController {

    private List<Material> materiales;

    public MaterialController() {
        super();
        this.materiales = new ArrayList<>();
    }

    @Override
    public void agregarMaterial(int idMaterial, Date fechaRegistro) {
        MaterialHandler mh = MaterialHandler.getInstancia();
        Material m = mh.buscarMaterialPorId(idMaterial);
        if (m == null) {
            // m = new Libro(idMaterial, fechaRegistro);
        }
        mh.agregarMaterialH(m);
    }

    @Override
    public void eliminarMaterial(Material material) {
        materiales.remove(material);
    }

    @Override
    public List<Material> obtenerMateriales() {
        return new ArrayList<>(materiales);
    }

    @Override
    public Material buscarMaterialPorId(int id) {
        for (Material material : materiales) {
            if (material.getIdMaterial() == id) {
                return material;
            }
        }
        return null;
    }

    // libros
    /*
     * @Override
     * public List<Material> buscarLibros() {
     * List<Material> libros = new ArrayList<>();
     * for (Material material : materiales) {
     * if (material instanceof Libro) {
     * libros.add(material);
     * }
     * }
     * return libros;
     * }
     * 
     * // articulos especiales
     * 
     * @Override
     * public List<Material> buscarArticulosEspeciales() {
     * List<Material> articulosEspeciales = new ArrayList<>();
     * for (Material material : materiales) {
     * if (!(material instanceof Libro)) {
     * articulosEspeciales.add(material);
     * }
     * }
     * return articulosEspeciales;
     * }
     */

    // Eventualmente se van a agregar / eliminar funciones
}
