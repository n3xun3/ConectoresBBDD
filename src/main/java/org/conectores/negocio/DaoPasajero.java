package org.conectores.negocio;

import org.conectores.entidad.Pasajero;

import java.util.List;

public interface DaoPasajero {
    boolean altaPasajero(Pasajero pasajero);
    boolean borrarPasajeroId(int idPasajero);
    // Método para obtener los pasajeros asociados a un coche
    List<Pasajero> obtenerPasajerosPorCoche(int idCoche);
    boolean relacionarPasajeroACoche(int idPasajero, int idCoche);
}
