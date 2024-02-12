package org.conectores.negocio;

import org.conectores.entidad.Pasajero;

import java.util.List;

/**
 * Esta interfaz define los métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de pasajeros.
 */
public interface DaoPasajero {

    /**
     * Registra un nuevo pasajero en la base de datos.
     *
     * @param pasajero El objeto Pasajero a ser registrado.
     * @return true si el pasajero se registra correctamente, false en caso contrario.
     */
    boolean altaPasajero(Pasajero pasajero);

    /**
     * Consulta un pasajero por su ID en la base de datos.
     *
     * @param idPasajero El ID del pasajero a ser consultado.
     * @return El objeto Pasajero encontrado, o null si no se encuentra ningún pasajero con el ID especificado.
     */
    Pasajero consultarPasajeroId(int idPasajero);

    /**
     * Elimina un pasajero de la base de datos por su ID.
     *
     * @param idPasajero El ID del pasajero a ser eliminado.
     * @return true si el pasajero se elimina correctamente, false en caso contrario.
     */
    boolean borrarPasajeroId(int idPasajero);

    /**
     * Modifica un pasajero existente en la base de datos por su ID.
     *
     * @param id El ID del pasajero a ser modificado.
     * @param pasajero El objeto Pasajero con los nuevos datos a ser actualizados.
     * @return true si el pasajero se modifica correctamente, false en caso contrario.
     */
    boolean modificarPasajeroPorId(int id, Pasajero pasajero);

    /**
     * Muestra un listado de todos los pasajeros registrados en la base de datos.
     *
     * @return true si se muestran los pasajeros correctamente, false en caso contrario.
     */
    boolean mostrarListadoPasajeros();

    /**
     * Obtiene una lista de pasajeros asociados a un coche específico en la base de datos.
     *
     * @param idCoche El ID del coche del cual se desean obtener los pasajeros asociados.
     * @return Una lista de objetos Pasajero asociados al coche especificado.
     */
    List<Pasajero> obtenerPasajerosPorCoche(int idCoche);

    /**
     * Asocia un pasajero a un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero a ser asociado.
     * @param idCoche El ID del coche al cual se desea asociar el pasajero.
     * @return true si se realiza la asociación correctamente, false en caso contrario.
     */
    boolean relacionarPasajeroACoche(int idPasajero, int idCoche);

    /**
     * Elimina la asociación de un pasajero a un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero del cual se desea eliminar la asociación.
     * @param idCoche El ID del coche del cual se desea eliminar la asociación.
     * @return true si se elimina la asociación correctamente, false en caso contrario.
     */
    boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche);
}
