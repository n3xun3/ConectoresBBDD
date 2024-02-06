package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

       /*En este caso añadimos la dependencia al archivo pom*/

        String cadenaConexion = "jdbc:mysql://localhost:3306/bbdd";
        String user = "root";
        String pass = ""; // sustituye por la contrase�a que especificaste durante la instalaci�n de MySQL.

        Connection con = null;
        try {
            //DriverManager es una clase que gestiona las conexiones, y aqui le
            //pedimos una conexion
            con = DriverManager.getConnection(cadenaConexion, user, pass);
        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexión con la BD");
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Se ha establecido la conexión con la Base de datos");

        // Cerrar la conexión (Tambien podriamos poner la referencia con
        // en un try-catch autoclose para que se cierre automáticamente
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("No se ha podido cerrar la conexión con la BD");
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Se ha cerrado la base de datos");

    }
}