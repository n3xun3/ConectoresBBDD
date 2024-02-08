package org.conectores.test;

import org.conectores.entidad.Coche;
import org.conectores.interfaces.DaoCoche;
import org.conectores.interfaces.DaoPasajero;
import org.conectores.persistencia.DaoCocheMySql;
import org.conectores.persistencia.DaoPasajeroMySql;

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
