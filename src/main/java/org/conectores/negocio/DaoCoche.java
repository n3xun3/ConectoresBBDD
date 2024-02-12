package org.conectores.negocio;

import org.conectores.entidad.Coche;

/**
 * Esta interfaz define los métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de coches.
 */
public interface DaoCoche {

    /**
     * Registra un nuevo coche en la base de datos.
     *
     * @param coche El objeto Coche a ser registrado.
     * @return true si el coche se registra correctamente, false en caso contrario.
     */
    boolean altaCoche(Coche coche);

    /**
     * Elimina un coche de la base de datos por su ID.
     *
     * @param idCoche El ID del coche a ser eliminado.
     * @return true si el coche se elimina correctamente, false en caso contrario.
     */
    boolean borrarCocheId(int idCoche);

    /**
     * Consulta un coche por su ID en la base de datos.
     *
     * @param idCoche El ID del coche a ser consultado.
     * @return El objeto Coche encontrado, o null si no se encuentra ningún coche con el ID especificado.
     */
    Coche consultarCocheId(int idCoche);

    /**
     * Muestra un listado de todos los coches registrados en la base de datos.
     *
     * @return true si se muestran los coches correctamente, false en caso contrario.
     */
    boolean mostrarListadoCoches();

    /**
     * Modifica un coche existente en la base de datos por su ID.
     *
     * @param id El ID del coche a ser modificado.
     * @param coche El objeto Coche con los nuevos datos a ser actualizados.
     * @return true si el coche se modifica correctamente, false en caso contrario.
     */
    boolean modificarCochePorId(int id, Coche coche);
}
