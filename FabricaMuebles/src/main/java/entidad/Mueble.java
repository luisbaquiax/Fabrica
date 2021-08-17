/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author luis
 */
public class Mueble {

    private String nombre;
    private double precio;
    private int cantidadExistente;

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
     * @param cantidadExistente the cantidadExistente to set
     */
    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente += cantidadExistente;
    }

    @Override
    public String toString() {
        return "Mueble{" + "nombre=" + nombre + ", precio=" + precio + ", cantidadExistente=" + cantidadExistente + '}';
    }

}
