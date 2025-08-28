package logica.controladores;

import interfaces.IMaterialController;

import java.util.List;

import datatypes.EstadosP;

import java.util.ArrayList;

import logica.clases.Material;
import logica.clases.Prestamo;
import logica.clases.Libro;
import logica.clases.ArticuloEspecial;
import logica.manejadores.MaterialHandler;
import logica.manejadores.PrestamoHandler;

import java.util.Date;

public class MaterialController implements IMaterialController {

    private List<Material> materiales;

    public MaterialController() {
        super();
        this.materiales = new ArrayList<>();
    }

    @Override
    public void agregarMaterial(int idMaterial, Date fechaRegistro) {
        /* MaterialHandler mh = MaterialHandler.getInstancia();
        Material existente = mh.buscarMaterialPorId(idMaterial);
        if (existente != null) {
            System.out.println("El material con ID " + idMaterial + " ya existe en el sistema");
            return;
        }
        if (existente instanceof Libro) {
            existente = new Libro(idMaterial, fechaRegistro, ((Libro) existente).getTitulo(),
                    ((Libro) existente).getAutor(), ((Libro) existente).getEditorial(), ((Libro) existente).getIsbn());
        } else if (existente instanceof ArticuloEspecial) {
            existente = new ArticuloEspecial(idMaterial, fechaRegistro, ((ArticuloEspecial) existente).getDescripcion(),
                    ((ArticuloEspecial) existente).getPeso(), ((ArticuloEspecial) existente).getDimFisicas());
        } else {
            System.out.println("Tipo de material no reconocido.");
            return;
        }
        mh.agregarMaterialH(nuevoMaterial); */
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
