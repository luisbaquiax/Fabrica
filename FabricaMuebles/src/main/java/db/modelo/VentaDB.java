/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Usuario;
import entidad.Venta;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class VentaDB {

    private static final String INSERT = "INSERT INTO venta(fecha, costo, estado, nombre_mueble, nit_cliente) VALUES(?,?,?,?,?)";
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
            statement.setString(3, venta.getEstado());
            statement.setString(4, venta.getNombreMueble());
            statement.setString(5, venta.getNitCliente());

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

}
