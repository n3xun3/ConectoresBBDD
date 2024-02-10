package org.conectores.negocio;

import org.conectores.entidad.Pasajero;

import java.util.List;

public interface DaoPasajero {
    boolean altaPasajero(Pasajero pasajero);

    Pasajero consultarPasajeroId(int idPasajero);

    boolean borrarPasajeroId(int idPasajero);

    boolean modificarPasajeroPorId(int id, Pasajero pasajero);

    boolean mostrarListadoPasajeros();

    // Método para obtener los pasajeros asociados a un coche
    List<Pasajero> obtenerPasajerosPorCoche(int idCoche);
    boolean relacionarPasajeroACoche(int idPasajero, int idCoche);

    boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche);
}
