/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class DetalleVentaDB {

    private static final String INSERT_DETALLE_VENTA = "INSERT INTO detalle_venta(id_venta,id_producto) VALUES(?,?) ";
    private static final String SELECT_BY_ID_VENTA = "SELECT * FROM detalle_venta WHERE id_venta = ?";
    private static final String SELECT_DETALL_BY_ID = "SELECT * FROM detalle_venta WHERE id = ?";
    private static final String DELETE_DETALLE_BY_ID = "DELETE FROM detalle_venta WHERE id = ?";

    public void insertDetalleVenta(DetalleVenta detalle) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT_DETALLE_VENTA);
        statement.setInt(1, detalle.getIdVenta());
        statement.setInt(2, detalle.getIdProducto());
        statement.executeUpdate();

    }

    public List<DetalleVenta> getDetallePorIDVenta(int idVenta) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DetalleVenta detalleVenta = null;
        List<DetalleVenta> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_BY_ID_VENTA);
            statement.setInt(1, idVenta);

            result = statement.executeQuery();

            while (result.next()) {
                detalleVenta = new DetalleVenta(result.getInt("id"), result.getInt("id_venta"), result.getInt("id_producto"));
                lista.add(detalleVenta);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    /**
     * query: SELECT * FROM detalle_venta WHERE id = ?
     *
     * @param id
     * @return
     */
    public DetalleVenta getDetallePorID(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DetalleVenta detalleVenta = null;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_DETALL_BY_ID);
            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {
                detalleVenta = new DetalleVenta(result.getInt("id"), result.getInt("id_venta"), result.getInt("id_producto"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return detalleVenta;
    }

    /**
     * query: DELETE * FROM detalle_venta WHERE id = ?
     *
     * @param id
     */
    public void deleteDetall(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(DELETE_DETALLE_BY_ID);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
