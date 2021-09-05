/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.DetalleEnsamblaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class DetalleEnsamblajeDB {

    private static final String INSERT = "INSERT INTO detalle_ensamblaje(id_ensamblaje, costo_pieza, tipo_pieza) VALUES(?,?,?)";
    private static final String ACTUALIZAR_DETALLE_ENSAMBLAJE = "UPDATE detalle_ensamblaje SET costo_pieza = ?, tipo_pieza = ? WHERE costo_pieza = ? AND tipo_pieza = ? AND id = ?";
    private static final String SELECT = "SELECT * FROM detalle_ensamblaje";
    private static final String SELECT_BY_ID_ENSAMBLAJE = "SELECT * FROM detalle_ensamblaje WHERE id_ensamblaje = ?";
    private static final String COSTO_ENSAMBLAJE = ""
            + "SELECT SUM(costo_pieza) AS costo\n"
            + "FROM detalle_ensamblaje\n"
            + "WHERE id_ensamblaje = ?";

    /**
     *
     * Inserta algún detalle de una venta a la base de datos en base el
     * id_ensamblaje
     *
     * @param detalle
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void insertarDetalleDelEnsamblaje(DetalleEnsamblaje detalle) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setInt(1, detalle.getIdEnsamblaje());
        statement.setDouble(2, detalle.getCostoPieza());
        statement.setString(3, detalle.getTipoPieza());

        registros = statement.executeUpdate();
    }

    /**
     *
     * @param costoNuevo
     * @param tipo
     * @param costoOriginal
     * @param tipoOriginal
     * @throws SQLException
     */
    public void actualizarEnsablajePrecioYTipoPieza(double costoNuevo, String tipo, double costoOriginal, String tipoOriginal, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(ACTUALIZAR_DETALLE_ENSAMBLAJE);
        statement.setDouble(1, costoNuevo);
        statement.setString(2, tipo);
        statement.setDouble(3, costoOriginal);
        statement.setString(4, tipoOriginal);
        statement.setInt(5, id);
        statement.executeUpdate();

    }

    /**
     * Lista los detalles de un ensamblaje
     *
     * @return
     */
    public List<DetalleEnsamblaje> getTodosDetalles() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DetalleEnsamblaje detalle = null;
        List<DetalleEnsamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT);
            result = statement.executeQuery();

            getDetalle(result, detalle, (ArrayList) lista);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleEnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*finally {
            if (conn != null) {
                Coneccion.close(result, statement, conn);

            }
        }*/
        return lista;

    }

    /**
     * Lista el detalle de un ensamblaje mediante el id-ensamblaje
     * <br><br>
     * query: SELECT * FROM detalle_ensamblaje WHERE id_ensamblaje = ?
     *
     * @param id
     * @return
     */
    public List<DetalleEnsamblaje> getTodosDetallesById(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DetalleEnsamblaje detalle = null;
        List<DetalleEnsamblaje> lista = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_BY_ID_ENSAMBLAJE);
            statement.setInt(1, id);

            result = statement.executeQuery();

            getDetalle(result, detalle, (ArrayList) lista);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleEnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*finally {
            if (conn != null) {
                Coneccion.close(result, statement, conn);

            }
        }*/
        return lista;

    }

    /**
     * query: COSTO_ENSAMBLAJE = "" + "SELECT SUM(costo_pieza) AS costo\n" +
     * "FROM detalle_ensamblaje\n" + "WHERE id_ensamblaje = ?"
     *
     * @param id
     * @return El costo de ensamblaje según el id del ensamblaje
     */
    public double getCostoEnsamblaje(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        double costoEnsamblaje = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(COSTO_ENSAMBLAJE);
            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {
                costoEnsamblaje = result.getDouble("costo");

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetalleEnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*finally {
            if (conn != null) {
                Coneccion.close(result, statement, conn);

            }
        }*/
        return costoEnsamblaje;
    }

    /**
     * Metodo del resutlset
     *
     * @param result
     * @param detalle
     * @param lista
     * @throws SQLException
     */
    private void getDetalle(ResultSet result, DetalleEnsamblaje detalle, ArrayList lista) throws SQLException {
        while (result.next()) {
            detalle = new DetalleEnsamblaje(
                    result.getInt("id"), result.getInt("id_ensamblaje"),
                    result.getDouble("costo_pieza"),
                    result.getString("tipo_pieza"));
            lista.add(detalle);
        }
    }
}
