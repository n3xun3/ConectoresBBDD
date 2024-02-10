package org.conectores.test.pasajero;

import org.conectores.entidad.Pasajero;
import org.conectores.negocio.DaoPasajero;
import org.conectores.persistencia.DaoPasajeroMySql;

public class TestAltapasajero {
    public static void main(String[] args) {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(2);
        pasajero.setNombre("Jose");
        pasajero.setEdad(24);
        pasajero.setPeso(50.0);

        DaoPasajero ps = new DaoPasajeroMySql();
        boolean alta = ps.altaPasajero(pasajero);
        if(alta){
            System.out.println("El pasajero se ha dado de alta");
        } else {
            System.out.println("El pasajero NO se ha dado de alta");
        }
    }
}
