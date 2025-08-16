package com.pap.interfaces;

import java.util.Date;
import com.pap.datatypes.EstadosU;
import com.pap.datatypes.Zonas;


public interface ILector {
    Date getFechaIngreso();
    void setFechaIngreso(Date fechaIngreso);

    EstadosU getEstadoUsuario();
    void setEstadoUsuario(EstadosU estadoUsuario);

    Zonas getZona();
    void setZona(Zonas zona);

    String getDireccion();
    void setDireccion(String direccion);
}
