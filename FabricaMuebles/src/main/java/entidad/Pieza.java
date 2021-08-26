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
     * true : eliminado, false: no eliminado/
     */
    private boolean estado;

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
     * For to insert in the table precio_piezas
     *
     * @param tipo
     * @param costo
     * @param cantidadExistente
     * @param estado
     */
    public Pieza(String tipo, double costo, int cantidadExistente, boolean estado) {
        this.tipo = tipo;
        this.costo = costo;
        this.cantidadExistente = cantidadExistente;
        this.estado = estado;
    }

    /**
     * Para insertar en la tabla pieza
     *
     * @param tipo
     */
    public Pieza(String tipo) {
        this.tipo = tipo;
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
     * @param cantidad this.cantidadExistente += cantidad;
     */
    public void setCantidadExistente(int cantidad) {
        this.cantidadExistente += cantidad;
    }

    /**
     *
     * @param cantidad
     */
    public void quitarCantidad(int cantidad) {
        this.cantidadExistente -= cantidad;
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

    @Override
    public String toString() {
        return "Pieza{" + "tipo=" + tipo + ", costo=" + costo + ", cantidadExistente=" + cantidadExistente + ", estado=" + estado + '}';
    }

}
