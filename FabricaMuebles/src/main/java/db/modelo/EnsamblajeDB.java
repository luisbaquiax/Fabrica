/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Ensamblaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class EnsamblajeDB {

    private static final String INSERT = "INSERT INTO ensamblaje(fecha, costo, estado, nombre_mueble, nombre_usuario) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_BY_ID = "UPDATE ensamblaje SET estado = ? WHERE id = ?";
    private static final String SELECT = "SELECT * FROM ensamblaje";
    private static final String SELECT_BY_USUARIO = "SELECT * FROM ensamblaje WHERE nombre_usuario = ?";
    private static final String ENSAMBLAJES_BY_ESTADO_USUARIO = "SELECT * FROM ensamblaje WHERE estado = ? AND nombre_usuario = ?";
    private static final String ENSAMBLAJE_BY_ID = "SELECT * FROM ensamblaje WHERE id = ?";
    private static final String ENSABLAJES_BY_FECHA_ASC = "SELECT * FROM ensamblaje WHERE nombre_usuario = ? ORDER BY fecha ASC";
    private static final String ENSABLAJES_BY_FECHA_DESC = "SELECT * FROM ensamblaje WHERE nombre_usuario = ? ORDER BY fecha DESC";

    /**
     *
     * @param ensamblaje
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void insertarEnsamblaje(Ensamblaje ensamblaje) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setDate(1, Date.valueOf(ensamblaje.getFecha()));
        statement.setDouble(2, ensamblaje.getCosto());
        statement.setBoolean(3, ensamblaje.getEstado());
        statement.setString(4, ensamblaje.getMueble());
        statement.setString(5, ensamblaje.getUsuario());

        registros = statement.executeUpdate();
    }

    /**
     * query: UPDATE_BY_ID = "UPDATE ensamblaje SET estado = ? WHERE id = ?"
     *
     * @param ensamblaje
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void actualizaEnsamblajePorId(Ensamblaje ensamblaje) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(UPDATE_BY_ID);
        statement.setBoolean(1, ensamblaje.getEstado());
        statement.setInt(2, ensamblaje.getId());

        registros = statement.executeUpdate();
    }

    /**
     * query: SELECT = "SELECT * FROM ensamblaje"
     *
     * @return
     */
    public List<Ensamblaje> getEnsamblajes() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        List<Ensamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT);
            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
                lista.add(ensamblaje);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * Seleciona los ensamblados por un usuario en particular<br><br>
     * query: SELECT_BY_USUARIO = "SELECT * FROM ensamblaje WHERE nombre_usuario
     * = ?"
     *
     * @param nombreUsuario
     * @return
     */
    public List<Ensamblaje> getEnsamblajesPorUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        List<Ensamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_BY_USUARIO);
            statement.setString(1, nombreUsuario);
            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
                lista.add(ensamblaje);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * query: ENSAMBLEJES_BY_ESTADO_USUARIO = "SELECT * FROM ensamblaje WHERE
     * estado = ? AND nombre_usuario = ?"
     *
     * @param estado
     * @param usuario
     * @return
     */
    public List<Ensamblaje> getEnsamblajesPorEstadoYUsuario(boolean estado, String usuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        List<Ensamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(ENSAMBLAJES_BY_ESTADO_USUARIO);
            statement.setBoolean(1, estado);
            statement.setString(2, usuario);

            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
                lista.add(ensamblaje);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * query: ENSABLAJES_BY_FECHA_ASC = "SELECT * FROM ensamblaje ORDER BY fecha
     * ASC"
     *
     * @return
     */
    public List<Ensamblaje> getEnsamblajesPorFechaASC(String usuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        List<Ensamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(ENSABLAJES_BY_FECHA_ASC);
            statement.setString(1, usuario);

            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
                lista.add(ensamblaje);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * query: ENSABLAJES_BY_FECHA_DESC = "SELECT * FROM ensamblaje ORDER BY
     * fecha DESC"
     *
     * @return
     */
    public List<Ensamblaje> getEnsamblajesPorFechaDESC(String usuario) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        List<Ensamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(ENSABLAJES_BY_FECHA_DESC);
            statement.setString(1, usuario);

            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
                lista.add(ensamblaje);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * query: SELECT * FROM ensamblaje where id = ?
     *
     * @param id
     * @return
     */
    public Ensamblaje getEnsamblajesPorID(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ensamblaje ensamblaje = null;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(ENSAMBLAJE_BY_ID);
            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {
                ensamblaje = new Ensamblaje(
                        result.getInt("id"),
                        result.getString("fecha"),
                        result.getDouble("costo"),
                        result.getBoolean("estado"),
                        result.getString("nombre_mueble"),
                        result.getString("nombre_usuario"));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(EnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblaje;
    }

}
