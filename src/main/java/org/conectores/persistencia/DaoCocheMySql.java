package org.conectores.persistencia;

import org.conectores.entidad.Coche;
import org.conectores.negocio.DaoCoche;

import java.sql.*;

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

    public boolean validarMarcaModeloNoVacios(Coche coche) {
        return !coche.getMarca().isEmpty() && !coche.getModelo().isEmpty();
    }

    @Override
    public boolean altaCoche(Coche coche){
        if(!abrirConexion()){
            return false;
        }

        boolean alta = false;

        String query = "INSERT INTO coches (MARCA, MODELO, AÑO) VALUES (?, ?, ?)";

        if (validarMarcaModeloNoVacios(coche)) {

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, coche.getMarca());
                ps.setString(2, coche.getModelo());
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
        } else {
            System.out.println("Error: La marca y el modelo no pueden estar vacíos.");
        }
        return alta;
    }

    @Override
    public boolean borrarCocheId(int idCoche) {
        if (!abrirConexion()) {
            return false;
        }

        boolean borrar = false;

        String queryDeleteCochepasajero = "DELETE FROM cochepasajero WHERE id_coche = ?";
        String queryDeleteCoche = "DELETE FROM coches WHERE id = ?";

        try {
            // Eliminar registros relacionados en la tabla cochepasajero
            PreparedStatement psDeleteCochepasajero = conexion.prepareStatement(queryDeleteCochepasajero);
            psDeleteCochepasajero.setInt(1, idCoche);
            psDeleteCochepasajero.executeUpdate();

            // Eliminar la fila de la tabla coches
            PreparedStatement psDeleteCoche = conexion.prepareStatement(queryDeleteCoche);
            psDeleteCoche.setInt(1, idCoche);
            int filasAfectadas = psDeleteCoche.executeUpdate();
            if (filasAfectadas > 0) {
                borrar = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al borrar el coche: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return borrar;
    }


    @Override
    public Coche consultarCocheId(int idCoche) {
        Coche coche = null;

        if (!abrirConexion()) {
            return null;
        }

        String query = "SELECT * FROM Coches WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, idCoche);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                coche = new Coche();
                coche.setId(rs.getInt("id"));
                coche.setMarca(rs.getString("marca"));
                coche.setModelo(rs.getString("modelo"));
                coche.setAño(rs.getInt("año"));

                System.out.println("Información del coche:");
                System.out.println("ID: " + coche.getId());
                System.out.println("Marca: " + coche.getMarca());
                System.out.println("Modelo: " + coche.getModelo());
                System.out.println("Año: " + coche.getAño());
                // Ajusta los nombres de las columnas según la estructura de tu tabla Coches
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el coche: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return coche;
    }

    @Override
    public boolean mostrarListadoCoches() {
        if (!abrirConexion()) {
            return false;
        }

        boolean mostrar = false;

        String query = "SELECT * FROM Coches";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            mostrar = true;
            System.out.println("Listado de coches:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int año = rs.getInt("año");
                // Ajusta los nombres de las columnas según la estructura de tu tabla Coches

                System.out.println("ID: " + id + ", Marca: " + marca + ", Modelo: " + modelo + ", Año: " + año);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el listado de coches: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return mostrar;
    }

    @Override
    public boolean modificarCochePorId(int id, Coche coche) {
        if (!abrirConexion()) {
            return false;
        }

        boolean modificado = false;

        String query = "UPDATE coches SET MARCA = ?, MODELO = ?, AÑO = ? WHERE ID = ?";

        if (validarMarcaModeloNoVacios(coche)) {

            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setString(1, coche.getMarca());
                ps.setString(2, coche.getModelo());
                ps.setInt(3, coche.getAño());
                ps.setInt(4, id);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    modificado = true;
                }
            } catch (SQLException e) {
                System.out.println("Error al modificar el coche: " + e.getMessage());
                e.printStackTrace();
            } finally {
                cerrarConexion();
            }
        } else {
            System.out.println("Error: La marca y el modelo no pueden estar vacíos.");
        }

        return modificado;
    }

}
