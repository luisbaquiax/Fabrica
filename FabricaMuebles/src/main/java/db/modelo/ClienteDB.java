/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ClienteDB {

    private static final String INSERT = "INSERT INTO cliente(nit, nombre, direccion) VALUES(?, ?, ?)";
    private static final String SELECT_BY_NIT = "SELECT * FROM cliente WHERE nit = ?";

    /**
     * Insert a new Cliete in the DB, query: INSERT = "INSERT INTO cliente(nit,
     * nombre, direccion) VALUES(?, ?, ?)"
     *
     *
     * @param cliente
     */
    public void insertarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, cliente.getNit());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDireccion());

            registros = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                Coneccion.close(conn);

            }
        }
    }

    /**
     * queru: SELECT * FROM cliente WHERE nit = ?
     *
     * @param nit
     * @return
     */
    public Cliente getClientPorNit(String nit) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Cliente cliente = null;

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_BY_NIT);
            statement.setString(1, nit);
            result = statement.executeQuery();

            while (result.next()) {
                cliente = new Cliente(
                        result.getString("nit"),
                        result.getString("nombre"),
                        result.getString("direccion"));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                Coneccion.close(conn);

            }
        }
        return cliente;
    }
}
