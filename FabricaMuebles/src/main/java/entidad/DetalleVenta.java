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
public class DetalleVenta {

    private int id;
    private int idVenta;
    private int idProducto;

    /**
     * Para seleccionar el detalle de una venta
     *
     * @param id
     * @param idVenta
     * @param idProducto
     */
    public DetalleVenta(int id, int idVenta, int idProducto) {
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    /**
     * Para agregar el detalle de una venta
     *
     * @param idVenta
     * @param idProducto
     */
    public DetalleVenta(int idVenta, int idProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }

}
