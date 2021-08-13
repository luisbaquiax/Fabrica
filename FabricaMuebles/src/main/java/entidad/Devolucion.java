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
public class Devolucion {

    private int id;
    private double perdida;
    private String fecha;
    private String nombreCliente;
    private String nombreMueble;

    /**
     * Recupear una devolución
     *
     * @param id
     * @param perdida
     * @param fecha
     * @param nombreCliente
     * @param nombreMueble
     */
    public Devolucion(int id, double perdida, String fecha, String nombreCliente, String nombreMueble) {
        this.id = id;
        this.perdida = perdida;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.nombreMueble = nombreMueble;
    }

    /**
     * Crear una devolución de parte del cliente
     *
     * @param perdida
     * @param fecha
     * @param nombreCliente
     * @param nombreMueble
     */
    public Devolucion(double perdida, String fecha, String nombreCliente, String nombreMueble) {
        this.perdida = perdida;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.nombreMueble = nombreMueble;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the perdida
     */
    public double getPerdida() {
        return perdida;
    }

    /**
     * @param perdida the perdida to set
     */
    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the nombreMueble
     */
    public String getNombreMueble() {
        return nombreMueble;
    }

    /**
     * @param nombreMueble the nombreMueble to set
     */
    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

}
