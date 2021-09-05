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

    private String nitCliente;
    /**
     * El usuario de tipo vendedor/área de fábrica
     */
    private String usuario;

    /**
     * Para recuperar una venta de la DB
     *
     * @param id
     * @param fecha
     * @param costo
     * @param nitCliente
     */
    public Venta(int id, String fecha, double costo, String nitCliente) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.nitCliente = nitCliente;
    }

    /**
     * Add a new Venta in the table venta
     *
     * @param fecha
     * @param costo
     * @param nitCliente
     * @param usuario
     */
    public Venta(String fecha, double costo, String nitCliente, String usuario) {
        this.fecha = fecha;
        this.costo = costo;
        this.nitCliente = nitCliente;
        this.usuario = usuario;
    }

    /**
     * Recupear información de la base de datos
     *
     * @param id
     * @param fecha
     * @param costo
     * @param nitCliente
     * @param usuario
     */
    public Venta(int id, String fecha, double costo, String nitCliente, String usuario) {
        this.id = id;
        this.fecha = fecha;
        this.costo = costo;
        this.nitCliente = nitCliente;
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

    /**
     *
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", costo=" + costo + ", nitCliente=" + nitCliente + ", usuario=" + usuario + '}';
    }

}
