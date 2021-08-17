/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Ensamblaje {

    private String fecha;
    private double costo;
    private String estado;
    private String mueble;
    private String usuario;

    /**
     *
     * @param fecha
     * @param costo
     * @param estado
     * @param mueble
     * @param usuario
     */
    public Ensamblaje(String fecha, double costo, String estado, String mueble, String usuario) {
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
        this.mueble = mueble;
        this.usuario = usuario;
    }

    /**
     * Para la entrada de datos
     *
     * @param fecha
     * @param mueble
     * @param usuario
     */
    public Ensamblaje(String fecha, String mueble, String usuario) {
        this.fecha = fecha;
        this.mueble = mueble;
        this.usuario = usuario;
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
     * @return the mueble
     */
    public String getMueble() {
        return mueble;
    }

    /**
     * @param mueble the mueble to set
     */
    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
