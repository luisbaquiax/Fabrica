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
public class Venta {

    private int id;
    private String fecha;
    private double costo;
    private String estado;
    private String nombreMueble;
    private String nitCliente;

    /**
     * Para recuperar una venta de la DB
     *
     * @param id
     * @param fecha
     * @param costo
     * @param estado
     * @param nombreMueble
     * @param nitCliente
     */
    public Venta(int id, String fecha, double costo, String estado, String nombreMueble, String nitCliente) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
        this.nombreMueble = nombreMueble;
        this.nitCliente = nitCliente;
    }

    /**
     * Agrega a la base de datos una nueva venta
     *
     * @param fecha
     * @param costo
     * @param estado
     * @param nombreMueble
     * @param nitCliente
     */
    public Venta(String fecha, double costo, String estado, String nombreMueble, String nitCliente) {
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
        this.nombreMueble = nombreMueble;
        this.nitCliente = nitCliente;
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
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

    /**
     * @return the nitCliente
     */
    public String getNitCliente() {
        return nitCliente;
    }

    /**
     * @param nitCliente the nitCliente to set
     */
    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

}
