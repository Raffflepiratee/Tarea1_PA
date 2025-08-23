package interfaces;

import java.util.Date;
import java.util.List;
import logica.clases.Material;

public interface IMaterialController {
    void agregarMaterial(int idMaterial, Date fechaRegistro);

    void eliminarMaterial(Material material);

    List<Material> obtenerMateriales();

    Material buscarMaterialPorId(int id);
}
