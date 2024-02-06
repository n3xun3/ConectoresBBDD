package org.conectores.test;

import org.conectores.entidad.Coche;
import org.conectores.interfaces.DaoCoche;
import org.conectores.persistencia.DaoCocheMySql;

public class TestAlta {
    public static void main(String[] args) {
        Coche coche = new Coche();
        coche.setMarca("Renault");
        coche.setColor("Blanco");
        coche.setAño(2024);

        DaoCoche dc = new DaoCocheMySql();
        boolean alta = dc.altaCoche(coche);
        if(alta){
            System.out.println("El coche se ha dado de alta");
        } else {
            System.out.println("El coche NO se ha dado de alta");
        }
    }
}