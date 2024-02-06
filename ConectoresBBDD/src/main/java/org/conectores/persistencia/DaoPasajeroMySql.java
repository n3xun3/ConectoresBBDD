package org.conectores.persistencia;

import org.conectores.entidad.Pasajero;
import org.conectores.interfaces.DaoPasajero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPasajeroMySql implements DaoPasajero {

    private Connection conexion;
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
    public boolean altaPasajero(Pasajero pasajero) {
        if (!abrirConexion()) {
            return false;
        }

        boolean exito = false;

        String query = "INSERT INTO pasajeros (nombre, edad, peso) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, pasajero.getNombre());
            ps.setInt(2, pasajero.getEdad());
            ps.setDouble(3, pasajero.getPeso());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir el pasajero: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return exito;
    }

}
