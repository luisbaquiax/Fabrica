/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Usuario;
import entidad.Venta;
import entidad.manejoErrores.FabricaExcepcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class VentaDB {

    private static final String INSERT = "INSERT INTO venta(fecha, costo, nit_cliente, nombre_usuario) VALUES(?,?,?,?)";
    private static final String GET_VENTA_BY_ID = "SELECT * FROM venta WHERE id = ?";
    private static final String SELECT_VENTAS = "SELECT * FROM venta WERE";
    private static final String UPDATE_USER_AND_STATUS = "UPDATE venta SET  nombre_usuario = ?, estado = ? WHERE id = ?";

    /**
     *
     * @param venta
     */
    public void insertarVenta(Venta venta) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setDate(1, Date.valueOf(venta.getFecha()));
            statement.setDouble(2, venta.getCosto());
            statement.setString(3, venta.getNitCliente());
            statement.setString(4, venta.getUsuario());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VentaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param usuario
     * @param estado
     * @param id
     */
    public void registrarVenta(Usuario usuario, String estado, int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, usuario.getNombre());
            statement.setString(2, estado);
            statement.setInt(3, id);

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VentaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Todas las ventas
     *
     * @return
     */
    public List<Venta> getVentas() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_VENTAS);

            result = statement.executeQuery();
            while (result.next()) {
                venta = new Venta(result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getString("nit_cliente"));

                ventas.add(venta);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VentaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    /**
     * Seleccion una venta por el id
     * <p>
     * query: GET_VENTA_BY_ID = "SELECT * FROM venta WERE id = ?"</p>
     *
     * @param id
     * @return
     * @throws FabricaExcepcion
     */
    public Venta getVentasByID(int id) throws FabricaExcepcion {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Venta venta = null;

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(GET_VENTA_BY_ID);
            statement.setInt(1, id);

            result = statement.executeQuery();
            while (result.next()) {
                venta = new Venta(result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getString("nit_cliente"));

            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VentaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (venta == null) {
            throw new FabricaExcepcion("No se econtro la factura.");
        }
        return venta;
    }
}
