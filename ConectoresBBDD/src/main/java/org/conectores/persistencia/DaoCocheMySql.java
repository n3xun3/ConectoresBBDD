package org.conectores.persistencia;

import org.conectores.entidad.Coche;
import org.conectores.interfaces.DaoCoche;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Implementación de {@link DaoCoche} para el acceso a datos de coches utilizando MySQL.
 */
public class DaoCocheMySql implements DaoCoche {

    private Connection conexion;

    /**
     * Intenta establecer una conexión con la base de datos.
     *
     * @return true si la conexión se establece correctamente, false en caso contrario.
     */
    public boolean abrirConexion(){
        String url = "jdbc:mysql://localhost:3306/GestionCoche";
        String usuario = "root";
        String password = "";
        try {
            conexion = DriverManager.getConnection(url,usuario,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Intenta cerrar la conexión con la base de datos.
     *
     * @return true si la conexión se cierra correctamente, false en caso contrario.
     */
    public boolean cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean altaCoche(Coche coche){
        if(!abrirConexion()){
            return false;
        }

        boolean alta = false;

        String query = "insert into coches (MARCA,COLOR,AÑO) " + " values(?,?,?)";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getColor());
            ps.setInt(3, coche.getAño());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                alta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir el coche: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return alta;
    }
}
