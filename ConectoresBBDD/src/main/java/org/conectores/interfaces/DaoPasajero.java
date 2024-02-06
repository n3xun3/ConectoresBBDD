package org.conectores.interfaces;

import org.conectores.entidad.Pasajero;

public interface DaoPasajero {
    boolean altaPasajero(Pasajero pasajero);
    boolean borrarPasajeroId(int idPasajero);
}
