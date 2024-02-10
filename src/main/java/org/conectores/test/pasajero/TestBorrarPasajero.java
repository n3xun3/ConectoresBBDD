package org.conectores.test.pasajero;

import org.conectores.negocio.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

public class TestBorrarPasajero {
    public static void main(String[] args){

        DaoPasajero ps = new DaoPasajeroMySql();
        int idPasajero = 1;
        boolean borrado = ps.borrarPasajeroId(idPasajero);
        if(borrado){
            System.out.println("El pasajero se ha eliminado.");
        } else {
            System.out.println("El pasajero NO se ha eliminado.");
        }
    }
}
