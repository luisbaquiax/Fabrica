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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ClienteDB {

    private static final String INSERT = "INSERT INTO cliente(nit, nombre, direccion) VALUES(?, ?, ?)";

    /**
     * Insert a new Cliete in the DB
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
        }
    }
}
