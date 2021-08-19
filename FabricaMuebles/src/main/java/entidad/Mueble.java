/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;

/**
 *
 * @author luis
 */
public class Mueble implements Serializable {

    private String nombre;
    private double precio;
    private int cantidadExistente;
    /**
     * true : eliminado, false: no eliminado/
     */
    private boolean estado;

    /**
     * For the DB
     *
     * @param nombre
     * @param precio
     */
    public Mueble(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     *
     * @param nombre
     * @param precio
     * @param cantidadExistente
     */
    public Mueble(String nombre, double precio, int cantidadExistente) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadExistente = cantidadExistente;
    }

    /**
     *
     * @param nombre
     * @param precio
     * @param cantidadExistente
     * @param estado
     */
    public Mueble(String nombre, double precio, int cantidadExistente, boolean estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadExistente = cantidadExistente;
        this.estado = estado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidadExistente
     */
    public int getCantidadExistente() {
        return cantidadExistente;
    }

    /**
     * this.cantidadExistente += cantidadExistente;
     *
     * @param cantidadExistente the cantidadExistente to set
     */
    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente += cantidadExistente;
    }

    /**
     * cantidadExistente -= cantidadExistente
     *
     * @param cantidadExistente
     */
    public void quitarExistentes(int cantidadExistente) {
        this.cantidadExistente -= cantidadExistente;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mueble{" + "nombre=" + nombre + ", precio=" + precio + ", cantidadExistente=" + cantidadExistente + '}';
    }

}
