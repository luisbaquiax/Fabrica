/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class UsuarioDB {

    private static final String INSERT = "INSERT INTO usuario(nombre, pass, tipo) VALUES(?, ?, ?)";
    private static final String DELETE = "DELET FROM usuario WHERE nombre = ?, AND pass = ?, AND tipo = ?";

    /**
     * Insertar usuario al sistema
     *
     * @param usuario
     */
    public void insertarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        //en caso de usar registros actualizados
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getTipo());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(statement);
            Coneccion.close(conn);
        }
    }

    /**
     * Eliminar al usuario del sistema
     *
     * @param usuario
     */
    public void eliminarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(DELETE);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getTipo());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(statement);
            Coneccion.close(conn);
        }

    }

}
