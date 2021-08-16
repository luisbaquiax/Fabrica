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
public class Pieza {

    private String tipo;
    private double costo;
    private int cantidadExistente;

    /**
     * For add a now PIECE in the DATA_BASE
     *
     * @param tipo
     * @param costo
     */
    public Pieza(String tipo, double costo) {
        this.tipo = tipo;
        this.costo = costo;
    }

    /**
     *
     * @param tipo
     * @param costo
     * @param cantidadExistente
     */
    public Pieza(String tipo, double costo, int cantidadExistente) {
        this.tipo = tipo;
        this.costo = costo;
        this.cantidadExistente = cantidadExistente;
    }

    /**
     *
     * @return
     */
    public int getCantidadExistente() {
        return cantidadExistente;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidadExistente(int cantidad) {
        this.cantidadExistente += cantidad;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}
