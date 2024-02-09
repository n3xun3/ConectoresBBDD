package org.conectores.test;

import org.conectores.entidad.Coche;
import org.conectores.negocio.DaoCoche;
import org.conectores.persistencia.DaoCocheMySql;

public class TesrmodificarCochePorId {
    public static void main(String[] args) {
        Coche coche = new Coche();
        coche.setMarca("Ford");
        coche.setColor("Azul");
        coche.setAño(2024);

        DaoCoche dc = new DaoCocheMySql();
        boolean modificar = dc.modificarCochePorId(1,coche);
        if(modificar){
            System.out.println(coche);
        } else {
            System.out.println("No existe");
        }    }
}
