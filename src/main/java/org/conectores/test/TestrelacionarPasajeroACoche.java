package org.conectores.test;

import org.conectores.interfaces.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

public class TestrelacionarPasajeroACoche {
    public static void main(String[] args){

        DaoPasajero ps = new DaoPasajeroMySql();
        int idPasajero = 2;
        int idCoche = 1;
        boolean borrado = ps.relacionarPasajeroACoche(idPasajero,idCoche);
        if(borrado){
            System.out.println("El pasajero se relacionado con coche.");
        } else {
            System.out.println("El pasajero NO se relacionado con coche.");
        }
    }
}
