package org.conectores.negocio;

import org.conectores.entidad.Coche;

public interface DaoCoche {
    boolean altaCoche(Coche coche);
    boolean borrarCocheId(int idCoche);

    Coche consultarCocheId(int idCoche);

    boolean mostrarListadoCoches();

    boolean modificarCochePorId(int id, Coche coche);
}
