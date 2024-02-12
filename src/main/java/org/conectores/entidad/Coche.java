package org.conectores.entidad;

/**
 * Esta clase representa a un coche.
 */
public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private int año;

    /**
     * Obtiene el ID del coche.
     *
     * @return El ID del coche.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del coche.
     *
     * @param id El ID del coche.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la marca del coche.
     *
     * @return La marca del coche.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del coche.
     *
     * @param marca La marca del coche.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del coche.
     *
     * @return El modelo del coche.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del coche.
     *
     * @param modelo El modelo del coche.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el año de fabricación del coche.
     *
     * @return El año de fabricación del coche.
     */
    public int getAño() {
        return año;
    }

    /**
     * Establece el año de fabricación del coche.
     *
     * @param año El año de fabricación del coche.
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Coche.
     *
     * @return Una cadena que representa el objeto Coche.
     */
    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                '}';
    }
}
