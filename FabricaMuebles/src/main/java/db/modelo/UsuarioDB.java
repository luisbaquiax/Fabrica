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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class UsuarioDB {

    private static final String INSERT = "INSERT INTO usuario(nombre, pass, tipo, estado) VALUES(?, ?, ?, ?)";
    private static final String DELETE = "DELET FROM usuario WHERE nombre = ?, AND pass = ?";
    private static final String SEARCH_USER = "SELECT * FROM usuario WHERE nombre = ? AND pass = ? LIMIT 1";
    private static final String SELECT_USERS_AREA_VENTAS = "SELECT * FROM usuario WHERE tipo = 2 AND estado = 0";
    private static final String SELECT_USERS_AREA_VENTAS_FABRICA = "SELECT * FROM usuario WHERE tipo != 3 AND estado = 0";
    private static final String SEARCH_USER_BY_NAME = "SELECT * FROM usuario WHERE nombre = ?";
    private static final String SELECT_USERS = "SELECT * FROM usuario";

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
            statement.setBoolean(4, usuario.isEstado());

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

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(statement);
            Coneccion.close(conn);
        }

    }

    /**
     * Servirá para el login dle sistema
     *
     * @param nombre
     * @param pass
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Usuario buscarUsuario(String nombre, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(SEARCH_USER);
        statement.setString(1, nombre);
        statement.setString(2, pass);
        result = statement.executeQuery();

        while (result.next()) {
            String name = result.getString("nombre");
            String contra = result.getString("pass");
            String tipo = result.getString("tipo");
            usuario = new Usuario(name, contra, tipo);
        }

        return usuario;
    }

    /**
     * Selecciona usuario por nombre<br><br>
     * query: SEARCH_USER_BY_NAME = "SELECT * FROM usuario WHERE nombre = ?"
     *
     * @param nombre
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Usuario buscarUsuarioPorNombre(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(SEARCH_USER_BY_NAME);
        statement.setString(1, nombre);
        result = statement.executeQuery();

        while (result.next()) {
            String name = result.getString("nombre");
            String contra = result.getString("pass");
            String tipo = result.getString("tipo");
            usuario = new Usuario(name, contra, tipo);
        }

        return usuario;
    }

    /**
     * Selecciona los usuarios del area de venta<br><br>
     * SELECT_USERS_AREA_VENTAS = "SELECT * FROM usuario WHERE tipo = 2 AND
     * estado = 0"
     *
     * @return
     */
    public LinkedList<Usuario> getUsurariosAreaDeVenta() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;
        LinkedList<Usuario> usuarios = new LinkedList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_USERS_AREA_VENTAS);
            result = statement.executeQuery();

            while (result.next()) {
                usuario = new Usuario(result.getString("nombre"), result.getString("pass"), result.getString("tipo"), result.getBoolean("estado"));
                usuarios.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    /**
     * Selecciona usuarios de ventas y fabrica
     * <br><br>
     * SELECT_USERS_AREA_VENTAS_FABRICA = "SELECT * FROM usuario WHERE tipo = 2
     * AND tipo = 1"
     *
     * @return
     */
    public LinkedList<Usuario> getUsurariosVentaYFabrica() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;
        LinkedList<Usuario> usuarios = new LinkedList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_USERS_AREA_VENTAS_FABRICA);
            result = statement.executeQuery();

            while (result.next()) {
                usuario = new Usuario(
                        result.getString("nombre"),
                        result.getString("pass"),
                        result.getString("tipo"),
                        result.getBoolean("estado"));
                usuarios.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    /**
     * Servirá para ver si ya existe datos en la base datos, básicamente lista
     * los usuarios registrados.
     * <br><br>
     * query: SELECT_USERS = "SELECT * FROM usuario"
     *
     * @return
     */
    public LinkedList<Usuario> getTodosUsuarios() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;
        LinkedList<Usuario> usuarios = new LinkedList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_USERS);
            result = statement.executeQuery();

            while (result.next()) {
                usuario = new Usuario(
                        result.getString("nombre"),
                        result.getString("pass"),
                        result.getString("tipo"),
                        result.getBoolean("estado"));
                usuarios.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
}
