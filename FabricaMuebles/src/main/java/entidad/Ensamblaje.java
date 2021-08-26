/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class Ensamblaje {

    private int id;
    private String fecha;
    private double costo;
    /**
     * true = estado registrado a la sala de ventas, false = estado no
     * registrado a la sala de ventas
     */
    private boolean estado;
    private String mueble;
    private String usuario;

    /**
     * Piezas que se utilizó
     */
    private ArrayList<Pieza> piezas;

    /**
     * Para insertar en la tabla ensamblaje
     *
     * @param fecha
     * @param costo
     * @param estado
     * @param mueble
     * @param usuario
     */
    public Ensamblaje(String fecha, double costo, boolean estado, String mueble, String usuario) {
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
     * Obtener ensamblaje desde la base de datos
     *
     * @param id
     * @param fecha
     * @param costo
     * @param estado
     * @param mueble
     * @param usuario
     */
    public Ensamblaje(int id, String fecha, double costo, boolean estado, String mueble, String usuario) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
        this.mueble = mueble;
        this.usuario = usuario;
    }

    /**
     *
     * @param id
     * @param fecha
     * @param costo
     * @param estado
     * @param mueble
     * @param usuario
     * @param piezas
     */
    public Ensamblaje(int id, String fecha, double costo, boolean estado, String mueble, String usuario, ArrayList<Pieza> piezas) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.estado = estado;
        this.mueble = mueble;
        this.usuario = usuario;
        this.piezas = piezas;
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
    public boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
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

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }

    @Override
    public String toString() {
        return "Ensamblaje{" + "id=" + id + "fecha=" + fecha + ", costo=" + costo + ", estado=" + estado + ", mueble=" + mueble + ", usuario=" + usuario + '}';
    }
}
