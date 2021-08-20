/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import com.mysql.cj.xdevapi.PreparableStatement;
import db.coneccion.Coneccion;
import entidad.Pieza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class PiezaDB {

    private static final String INSERT = "INSERT INTO pieza(tipo, costo, cantidad) VALUES(?,?,?)";
    private static final String UPDATE_CANTIDADES = "UPDATE pieza SET cantidad = ? WHERE tipo = ?";
    private static final String UPDATE_PIEZA = "UPDATE pieza SET cantidad = ?, costo = ? WHERE tipo = ?";
    private static final String LIST_PIEZAS = "SELECT * FROM pieza";
    private static final String LIST_PIEZAS_CANTIDAD_ASC = "SELECT * FROM pieza ORDER BY cantidad ASC";
    private static final String LIST_PIEZAS_CANTIDAD_DESC = "SELECT * FROM pieza ORDER BY cantidad DESC";
    private static final String PIEZA_BY_TIPO = "SELECT * FROM pieza WHERE tipo = ? LIMIT 1";
    private static final String DELETE = "DELETE FROM pieza WHERE tipo = ?";
    private static final String CAMBIAR_ESTADO_PIEZA = "UPDATE pieza SET estado = ? WEHRE tipo = ?";
    private static final String PIEZAS_AGOTADAS_O_CASI_AGOTADAS = "SELECT * FROM pieza WHERE cantidad < 5";

    /**
     *
     * @param pieza
     */
    public void insertarPieza(Pieza pieza) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, pieza.getTipo());
            statement.setDouble(2, pieza.getCosto());
            statement.setInt(3, pieza.getCantidadExistente());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * DELETE = "DELET * FROM pieza WHERE tipo = ?"
     *
     * @param tipo
     */
    public void eliminarPieza(String tipo) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(DELETE);
            statement.setString(1, tipo);
            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("error al eliminar");
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * query: CAMBIAR_ESTADO_PIEZA = "UPDATE pieza SET estado = ? WEHRE tipo =
     * ?"
     *
     * @param p
     */
    public void cambiarEstadoPieza(Pieza p) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(CAMBIAR_ESTADO_PIEZA);
            statement.setBoolean(1, p.isEstado());
            statement.setString(2, p.getTipo());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("error al eliminar");
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * UPDATE_CANTIDADES = "UPDATE pieza SET cantidad = ? WHERE tipo = ?"
     *
     * @param tipo
     * @param cantidad
     */
    public void actualizarCatidadPiezas(String tipo, int cantidad) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(UPDATE_CANTIDADES);

            statement.setInt(1, cantidad);
            statement.setString(2, tipo);

            registros = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error al actualizar");
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * UPDATE_PIEZA = "UPDATE pieza SET cantidad = ?, costo = ? WHERE tipo = ?
     * \n
     *
     * @param pieza
     */
    public void editarPieza(Pieza pieza) {
        int registros = 0;
        try {

            Connection conn = Coneccion.getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_PIEZA);

            statement.setInt(1, pieza.getCantidadExistente());
            statement.setDouble(2, pieza.getCosto());
            statement.setString(3, pieza.getTipo());

            registros = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error al actualizar");
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene lista de piezas
     *
     * @return
     */
    public List<Pieza> getPiezas() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS);
            result = statement.executeQuery();

            while (result.next()) {
                pieza = new Pieza(
                        result.getString("tipo"),
                        result.getDouble("costo"),
                        result.getInt("cantidad"));
                piezas.add(pieza);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Instruccion: PIEZAS_AGOTADAS_O_CASI_AGOTADAS = "SELECT * FROM pieza WHERE
     * cantidad < 5"
     *
     * @return
     */
    public List<Pieza> getPiezasAgotadas() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(PIEZAS_AGOTADAS_O_CASI_AGOTADAS);
            result = statement.executeQuery();

            while (result.next()) {
                pieza = new Pieza(
                        result.getString("tipo"),
                        result.getDouble("costo"),
                        result.getInt("cantidad"));
                piezas.add(pieza);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Obtiene lista de piezas ordenados según cantidad de existentes
     * ascendentemente
     *
     * @return
     */
    public List<Pieza> getPiezasAscedentemente() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS_CANTIDAD_ASC);
            result = statement.executeQuery();

            while (result.next()) {
                pieza = new Pieza(
                        result.getString("tipo"),
                        result.getDouble("costo"),
                        result.getInt("cantidad"));
                piezas.add(pieza);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Obtiene lista de piezas ordenados según cantidad de existentes
     * ascendentemente
     *
     * @return
     */
    public List<Pieza> getPiezasDescedentemente() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS_CANTIDAD_DESC);
            result = statement.executeQuery();

            while (result.next()) {
                pieza = new Pieza(
                        result.getString("tipo"),
                        result.getDouble("costo"),
                        result.getInt("cantidad"));
                piezas.add(pieza);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     *
     * @param tipo
     * @return
     */
    public Pieza getPiezaPorTipo(String tipo) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(PIEZA_BY_TIPO);
            statement.setString(1, tipo);
            result = statement.executeQuery();

            while (result.next()) {
                pieza = new Pieza(
                        result.getString("tipo"),
                        result.getDouble("costo"),
                        result.getInt("cantidad"));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }

}
