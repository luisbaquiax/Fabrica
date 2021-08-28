/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import com.mysql.cj.xdevapi.PreparableStatement;
import db.coneccion.Coneccion;
import entidad.Pieza;
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
public class PiezaDB {

    private static final String INSERT = "INSERT INTO pieza(tipo) VALUES(?)";
    private static final String UPDATE_CANTIDADES = "UPDATE pieza SET cantidad = ? WHERE tipo = ?";
    private static final String UPDATE_PIEZA = "UPDATE pieza SET tipo = ? WHERE tipo = ?";
    private static final String LIST_PIEZAS = "SELECT * FROM pieza";
    private static final String PIEZA_BY_TIPO = "SELECT * FROM pieza WHERE tipo = ? LIMIT 1";
    private static final String DELETE = "DELETE FROM pieza WHERE tipo = ?";
    private static final String CAMBIAR_ESTADO_PIEZA = "UPDATE pieza SET estado = ? WEHRE tipo = ?";

    /**
     * Inserta una nueva pieza a la tabla pieza
     *
     * @param pieza
     * @throws java.sql.SQLException
     */
    public void insertarPieza(Pieza pieza) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, pieza.getTipo());

            registros = statement.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
            statement.setBoolean(1, p.getEstado());
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
     * Actuliza una pieza de la tabla pieza
     * <br><br>
     * query: UPDATE_PIEZA = "UPDATE pieza SET tipo = ? WHERE tipo = ?"
     *
     *
     * @param pieza
     * @param nuevoNombre
     */
    public void actualizarPieza(Pieza pieza, String nuevoNombre) {
        int registros = 0;
        try {

            Connection conn = Coneccion.getConnection();
            PreparedStatement statement = conn.prepareStatement(UPDATE_PIEZA);

            statement.setString(1, nuevoNombre);
            statement.setString(2, pieza.getTipo());

            registros = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error al actualizar");
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                pieza = new Pieza(result.getString("tipo"));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pieza;
    }

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
                pieza = new Pieza(result.getString("tipo"));
                piezas.add(pieza);
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

}
