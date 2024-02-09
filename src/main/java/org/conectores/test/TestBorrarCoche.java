package org.conectores.test;

import org.conectores.negocio.DaoCoche;
import org.conectores.persistencia.DaoCocheMySql;

public class TestBorrarCoche {
    public static void main(String[] args){

        DaoCoche dc = new DaoCocheMySql();
        int idCoche = 2;
        boolean borrado = dc.borrarCocheId(idCoche);
        if(borrado){
            System.out.println("El coche se ha eliminado.");
        } else {
            System.out.println("El coche NO se ha eliminado.");
        }
    }
}
