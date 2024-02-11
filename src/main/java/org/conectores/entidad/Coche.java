package org.conectores.entidad;

public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private int año;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String toString(){
        return "Coche [id=" + id + ", marca=" + marca + ", modelo" + modelo + ",año" + año + "]";
    }
}