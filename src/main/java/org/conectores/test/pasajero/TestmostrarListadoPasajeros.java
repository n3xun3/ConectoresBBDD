package org.conectores.test.pasajero;

import org.conectores.negocio.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

public class TestmostrarListadoPasajeros {
    public static void main(String[] args){

        DaoPasajero ps = new DaoPasajeroMySql();
        boolean mostrado = ps.mostrarListadoPasajeros();
        if(!mostrado){
            System.out.println("No existen coche en la BBDD.");
        }
    }
}
