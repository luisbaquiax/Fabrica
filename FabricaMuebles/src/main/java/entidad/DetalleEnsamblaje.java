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
public class DetalleEnsamblaje {

    private int id;
    private int idEnsamblaje;
    private double costoPieza;
    private String tipoPieza;

    /**
     * For to insert a new detalle_ensamblaje in the database
     *
     * @param idEnsamblaje
     * @param costoPieza
     * @param tipoPieza
     */
    public DetalleEnsamblaje(int idEnsamblaje, double costoPieza, String tipoPieza) {
        this.idEnsamblaje = idEnsamblaje;
        this.costoPieza = costoPieza;
        this.tipoPieza = tipoPieza;
    }

    /**
     * Recuperar detalle desde la DB
     *
     * @param id
     * @param idEnsamblaje
     * @param costoPieza
     * @param tipoPieza
     */
    public DetalleEnsamblaje(int id, int idEnsamblaje, double costoPieza, String tipoPieza) {
        this.id = id;
        this.idEnsamblaje = idEnsamblaje;
        this.costoPieza = costoPieza;
        this.tipoPieza = tipoPieza;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the idEnsamblaje
     */
    public int getIdEnsamblaje() {
        return idEnsamblaje;
    }

    /**
     * @return the costoPieza
     */
    public double getCostoPieza() {
        return costoPieza;
    }

    /**
     * @param costoPieza the costoPieza to set
     */
    public void setCostoPieza(double costoPieza) {
        this.costoPieza = costoPieza;
    }

    /**
     * @return the tipoPieza
     */
    public String getTipoPieza() {
        return tipoPieza;
    }

    /**
     * @param tipoPieza the tipoPieza to set
     */
    public void setTipoPieza(String tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

}
