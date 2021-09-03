/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.DetalleVenta;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class DetalleVentaDB {

    private static final String INSERT_DETALLE_VENTA = "INSERT INTO detalle_venta(id_venta,id_producto) VALUES(?,?) ";

    public void insertDetalleVenta(DetalleVenta detalle) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT_DETALLE_VENTA);
        statement.setInt(1, detalle.getIdVenta());
        statement.setInt(2, detalle.getIdProducto());
        statement.executeUpdate();

    }
}
