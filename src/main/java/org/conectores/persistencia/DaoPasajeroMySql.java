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
    public Pasajero consultarPasajeroId(int idPasajero) {
        Pasajero pasajero = null;

        if (!abrirConexion()) {
            return null;
        }

        String query = "SELECT * FROM Pasajeros WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, idPasajero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setId(rs.getInt("id"));
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setEdad(rs.getInt("edad"));
                pasajero.setPeso(rs.getInt("peso"));

                System.out.println("Información del coche:");
                System.out.println("ID: " + pasajero.getId());
                System.out.println("Nombre: " + pasajero.getNombre());
                System.out.println("Edad: " + pasajero.getEdad());
                System.out.println("Peso: " + pasajero.getPeso());
                // Ajusta los nombres de las columnas según la estructura de tu tabla Coches
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el coche: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return pasajero;
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
    public boolean modificarPasajeroPorId(int id, Pasajero pasajero) {
        if (!abrirConexion()) {
            return false;
        }

        boolean modificado = false;

        String query = "UPDATE pasajeros SET NOMBRE = ?, EDAD = ?, PESO = ? WHERE ID = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, pasajero.getNombre());
            ps.setInt(2, pasajero.getEdad());
            ps.setDouble(3, pasajero.getPeso());
            ps.setInt(4, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                modificado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el pasajero: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return modificado;
    }

    @Override
    public boolean mostrarListadoPasajeros() {
        if (!abrirConexion()) {
            return false;
        }

        boolean mostrar = false;

        String query = "SELECT * FROM pasajeros";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            mostrar = true;
            System.out.println("Listado de Pasajeros:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int peso = rs.getInt("peso");
                // Ajusta los nombres de las columnas según la estructura de tu tabla Coches

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Pesp: " + peso);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el listado de Psajeros: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return mostrar;
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

            // Mostrar todos los coches disponibles
            System.out.println("Coches disponibles:");
            mostrarCochesDisponibles();

            String query = "INSERT INTO CochePasajero (id_coche, id_pasajero) VALUES (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Pasajero añadido correctamente al coche con ID: " + idCoche);
                return true; // Se insertó correctamente
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return false; // No se pudo realizar la inserción
    }

    private void mostrarCochesDisponibles() throws SQLException {
        String query = "SELECT id, marca, modelo, año FROM Coches";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idCoche = rs.getInt("id");
            String marcaCoche = rs.getString("marca");
            String modeloCoche = rs.getString("modelo");
            int añoCoche = rs.getInt("año");
            System.out.println("ID: " + idCoche + ", Marca: " + marcaCoche + ", Modelo: " + modeloCoche + ", Año: " + añoCoche);
        }
    }
    @Override
    public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche) {
        try {
            abrirConexion();

            // Mostrar todos los coches y sus pasajeros asociados
            System.out.println("Coches y sus pasajeros asociados antes de la eliminación:");
            mostrarCochesYPasajeros();

            String query = "DELETE FROM CochePasajero WHERE id_pasajero = ? AND id_coche = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idPasajero);
            ps.setInt(2, idCoche);

            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                return true; // Se eliminó correctamente la relación pasajero-coche
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return false; // No se pudo eliminar la relación pasajero-coche
    }

    // Método para mostrar todos los coches y sus pasajeros asociados
    private void mostrarCochesYPasajeros() throws SQLException {
        String query = "SELECT coches.id AS id, coches.marca, coches.modelo, coches.año, " +
                "pasajeros.id AS id, pasajeros.nombre AS nombre, pasajeros.edad, pasajeros.peso " +
                "FROM Coches coches " +
                "LEFT JOIN CochePasajero cp ON coches.id = cp.id_coche " +
                "LEFT JOIN Pasajeros pasajeros ON cp.id_pasajero = pasajeros.id";

        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idCoche = rs.getInt("id");
            String marcaCoche = rs.getString("marca");
            int idPasajero = rs.getInt("id");
            String nombrePasajero = rs.getString("nombre");

            System.out.println("Coche ID: " + idCoche + ", Marca: " + marcaCoche + ", Pasajero ID: " + idPasajero + ", Nombre: " + nombrePasajero);
        }
    }

}
