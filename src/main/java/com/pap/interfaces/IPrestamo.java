package com.pap.interfaces;
import java.util.Date;

import com.pap.datatypes.EstadosP;

public interface IPrestamo {
    int getIdPrestamo();
    void setIdPrestamo(int idPrestamo);

    Date getFechaSol();
    void setFechaSol(Date fechaSol);

    EstadosP getEstadosPrestamo();
    void setEstadosPres(EstadosP estadosPres);

    Date getFechaDev();
    void setFechaDev(Date fechaDev);
}
