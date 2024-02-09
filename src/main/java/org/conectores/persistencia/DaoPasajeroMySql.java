package org.conectores.persistencia;

import org.conectores.entidad.Pasajero;
import org.conectores.negocio.DaoPasajero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public  boolean borrarPasajeroId(int idPasajero){
        if(!abrirConexion()){
            return false;
        }

        boolean borrar = false;

        String query = "DELETE FROM pasajeros WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, idPasajero);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                borrar = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al borrar el pasajero: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return borrar;
    }

    @Override
    // Método para obtener los pasajeros asociados a un coche
    public List<Pasajero> obtenerPasajerosPorCoche(int idCoche) {
        List<Pasajero> pasajeros = new ArrayList<>();

        try {
            abrirConexion();

            String query = "SELECT p.id, p.nombre, p.edad, p.peso " +
                    "FROM Pasajeros p " +
                    "JOIN CochePasajero cp ON p.id = cp.id_pasajero " +
                    "WHERE cp.id_coche = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idCoche);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt("id"));
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setEdad(rs.getInt("edad"));
                pasajero.setPeso(rs.getDouble("peso"));

                pasajeros.add(pasajero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return pasajeros;
    }

    @Override
    public boolean relacionarPasajeroACoche(int idPasajero, int idCoche) {
        try {
            abrirConexion();

            String query = "INSERT INTO CochePasajero (id_coche, id_pasajero) VALUES (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                return true; // Se insertó correctamente
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return false; // No se pudo realizar la inserción
    }

}
