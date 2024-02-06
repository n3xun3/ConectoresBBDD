package org.conectores.test;

import org.conectores.entidad.Pasajero;
import org.conectores.interfaces.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

import java.util.List;

public class TestobtenerPasajerosPorCoche {
    public static void main(String[] args){

        DaoPasajero ps = new DaoPasajeroMySql();
        int idCoche = 1;
        List<Pasajero> pasajeros = ps.obtenerPasajerosPorCoche(idCoche);
        if(pasajeros.size() > 0){
            System.out.println("El pasajero se relacionado con coche.");
            System.out.println(pasajeros);
        } else {
            System.out.println("El coche NO tiene ningún pasajero asociado.");
        }
    }
}
