package org.conectores.test.pasajero;

import org.conectores.entidad.Pasajero;
import org.conectores.negocio.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

public class TestconsultarPasajeroId {
    public static void main(String[] args){

        DaoPasajero ps = new DaoPasajeroMySql();
        int idPasajero = 1;
        Pasajero pasajero = ps.consultarPasajeroId(idPasajero);
        if(pasajero != null){
            System.out.println("El pasajero se muestra correctamente.");
        } else {
            System.out.println("El pasajero NO se muestra correctamente.");
        }
    }
}
