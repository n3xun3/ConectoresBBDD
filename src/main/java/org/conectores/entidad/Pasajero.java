package org.conectores.entidad;

/**
 * Esta clase representa a un pasajero que puede ser transportado en un coche.
 */
public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private double peso;

    /**
     * Obtiene el ID del pasajero.
     *
     * @return El ID del pasajero.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del pasajero.
     *
     * @param id El ID del pasajero.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del pasajero.
     *
     * @return El nombre del pasajero.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del pasajero.
     *
     * @param nombre El nombre del pasajero.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del pasajero.
     *
     * @return La edad del pasajero.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del pasajero.
     *
     * @param edad La edad del pasajero.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el peso del pasajero.
     *
     * @return El peso del pasajero.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del pasajero.
     *
     * @param peso El peso del pasajero.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Pasajero.
     *
     * @return Una cadena que representa el objeto Pasajero.
     */
    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                '}';
    }
}
