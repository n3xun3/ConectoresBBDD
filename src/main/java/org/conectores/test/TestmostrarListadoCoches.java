package org.conectores.test;

import org.conectores.negocio.DaoCoche;
import org.conectores.persistencia.DaoCocheMySql;

public class TestmostrarListadoCoches {
    public static void main(String[] args){

        DaoCoche ps = new DaoCocheMySql();
        boolean mostrado = ps.mostrarListadoCoches();
        if(!mostrado){
            System.out.println("No existen coche en la BBDD.");
        }
    }
}
