/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.reportesAdministracion;

import db.coneccion.Coneccion;
import entidad.Usuario;
import entidad.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ReportesAdminDB {

    private static final String VENTAS_BY_FECHAS = "SELECT * FROM venta WHERE fecha BETWEEN ? AND ?";
    private static final String USUARIO_CON_MAS_VENTAS = ""
            + "SELECT COUNT(nombre_usuario) AS cantidad, nombre_usuario\n"
            + "FROM venta\n"
            + "GROUP BY nombre_usuario\n"
            + "ORDER BY COUNT(nombre_usuario) DESC LIMIT 1";
    private static final String USUARIO_CON_MAS_VENTAS_FECHAS = ""
            + "SELECT COUNT(nombre_usuario) AS cantidad, nombre_usuario\n"
            + "FROM venta WHERE fecha BETWEEN ? AND ?\n"
            + "GROUP BY nombre_usuario\n"
            + "ORDER BY COUNT(nombre_usuario) DESC LIMIT 1;";
    private static final String USER_VENTAS = "SELECT * FROM venta WHERE nombre_usuario = ?";
    private static final String USER_VENTAS_FECHAS = "SELECT * FROM venta WHERE nombre_usuario = ? AND fecha BETWEEN ? AND ?";

    /**
     * Listado de todas las ventas <br><br>
     * query: SELECT * FROM venta WHERE AND fecha BETWEEN ? AND ?
     *
     * @param fecha1
     * @param fecha2
     * @return
     * @throws SQLException
     */
    public ArrayList<Venta> getVentasEntreFechas(String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> compras = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(VENTAS_BY_FECHAS);
        statement.setDate(1, Date.valueOf(fecha1));
        statement.setDate(2, Date.valueOf(fecha2));

        result = statement.executeQuery();

        getVenta(result, venta, compras);

        return compras;
    }

    /**
     * <br><br>
     * query: SELECT COUNT(nombre_usuario) AS cantidad, nombre_usuario FROM
     * venta GROUP BY nombre_usuario ORDER BY COUNT(nombre_usuario) DESC LIMIT 1
     *
     * @return Obtiene el usuario que registra más ventas
     * @throws java.sql.SQLException
     */
    public Usuario getUsuarioMasVentas() throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(USUARIO_CON_MAS_VENTAS);

        result = statement.executeQuery();

        while (result.next()) {
            usuario = new Usuario(result.getString("nombre_usuario"), result.getInt("cantidad"));
        }
        return usuario;
    }

    /**
     * <br><br>
     * query: SELECT COUNT(nombre_usuario) AS cantidad, nombre_usuario FROM
     * venta WHERE fecha BETWEEN ? AND ? GROUP BY nombre_usuario ORDER BY
     * COUNT(nombre_usuario) DESC LIMIT 1;
     *
     * @param fecha1
     * @param fecha2
     * @return selecciona un usuario que ha vendido en un intervalo de fecha
     * @throws SQLException
     */
    public Usuario getUsuarioMasVentasEnFechas(String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Usuario usuario = null;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(USUARIO_CON_MAS_VENTAS_FECHAS);
        statement.setDate(1, Date.valueOf(fecha1));
        statement.setDate(2, Date.valueOf(fecha2));

        result = statement.executeQuery();

        while (result.next()) {
            usuario = new Usuario(result.getString("nombre_usuario"), result.getInt("cantidad"));
        }
        return usuario;
    }

    /**
     * query: SELECT * FROM venta WHERE nombre_usuario = ?
     *
     * @param usuario
     * @return retorna las ventas de un usuario basado en el nombre del usuario
     * de usuario
     * @throws SQLException
     */
    public List<Venta> getUserVentasByName(String usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> compras = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(USER_VENTAS);
        statement.setString(1, usuario);

        result = statement.executeQuery();

        getVenta(result, venta, compras);
        return compras;
    }

    /**
     * query: SELECT * FROM venta WHERE nombre_usuario = ?
     *
     * @param usuario
     * @return retorna las ventas de un usuario basado en el nombre del usuario
     * de usuario
     * @throws SQLException
     */
    public List<Venta> getUserVentasPorNombreYFechas(String usuario, String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> compras = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(USER_VENTAS_FECHAS);
        statement.setString(1, usuario);
        statement.setString(2, fecha1);
        statement.setString(3, fecha2);

        result = statement.executeQuery();

        getVenta(result, venta, compras);
        return compras;
    }

    /**
     * Obtiene registros del ResulSet
     *
     * @param result
     * @param venta
     * @param compras
     * @throws SQLException
     */
    private void getVenta(ResultSet result, Venta venta, ArrayList compras) throws SQLException {
        while (result.next()) {
            venta = new Venta(
                    result.getInt("id"),
                    String.valueOf(result.getDate("fecha")),
                    result.getDouble("costo"),
                    result.getString("nit_cliente"),
                    result.getString("nombre_usuario"));
            compras.add(venta);
        }
    }
}
