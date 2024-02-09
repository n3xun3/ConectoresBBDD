package org.conectores.test;

import org.conectores.entidad.Coche;
import org.conectores.negocio.DaoCoche;
import org.conectores.persistencia.DaoCocheMySql;

public class TestconsultarCocheId {
    public static void main(String[] args){

        DaoCoche ps = new DaoCocheMySql();
        int idCoche = 1;
        Coche borrado = ps.consultarCocheId(idCoche);
        if(borrado != null){
            System.out.println("El pasajero se ha eliminado.");
        } else {
            System.out.println("El pasajero NO se ha eliminado.");
        }
    }
}
