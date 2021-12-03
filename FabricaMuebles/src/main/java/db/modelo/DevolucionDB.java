/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Devolucion;
import entidad.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class DevolucionDB {

    private static final String INSERT = "INSERT INTO devolucion(perdida, fecha, nombre_cliente, id_producto) VALUES(?,?,?,?)";
    private static final String SELECT = "SELECT * FROM devolucion WHERE nombre_cliente = ?";
    private static final String SELECT_DEVOLUCION_FECHAS = "SELECT * FROM devolucion WHERE nombre_cliente = ? AND fecha BETWEEN ? AND ?";
    private static final String LISTADO_DEVOLUCIONES = "SELECT * FROM devolucion";
    private static final String LISTADO_DEVOLUCIONES_ENTRE_FECHAS = "SELECT * FROM devolucion WHERE fecha BETWEEN ? AND ?";
    private static final String DETALLE_DEVOLUCION = "SELECT venta.id, venta.fecha, venta.costo, venta.nit_cliente, venta.nombre_usuario\n"
            + "FROM venta\n"
            + "RIGHT JOIN detalle_venta\n"
            + "ON venta.id = detalle_venta.id_venta\n"
            + "WHERE detalle_venta.id_producto = ?";

    /**
     *
     * @param devolucion
     * @throws SQLException
     */
    public void insertarDevolucion(Devolucion devolucion) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        //registros afectados
        int registro = 0;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setDouble(1, devolucion.getPerdida());
        statement.setString(2, devolucion.getFecha());
        statement.setString(3, devolucion.getNombreCliente());
        statement.setInt(4, devolucion.getIdProducto());
        registro = statement.executeUpdate();

    }

    /**
     * query: SELECT * FROM devolucion WHERE nombre_cliente = ?
     *
     * @return listado de devoluciones
     * @throws SQLException
     */
    public List<Devolucion> getDevoluciones(String nitCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Devolucion dev = null;
        List<Devolucion> lista = new ArrayList<>();

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(SELECT);
        statement.setString(1, nitCliente);

        result = statement.executeQuery();

        resulsetDev(result, dev, lista);
        return lista;
    }

    /**
     *
     * @param fecha1
     * @param fecha2
     * @return
     * @throws SQLException
     */
    public List<Devolucion> getDevolucionesEntreFechasMedianteNITcliente(String nitCliente, String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Devolucion dev = null;
        List<Devolucion> lista = new ArrayList<>();

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(SELECT_DEVOLUCION_FECHAS);
        statement.setString(1, nitCliente);
        statement.setString(2, fecha1);
        statement.setString(3, fecha2);

        result = statement.executeQuery();

        resulsetDev(result, dev, lista);

        return lista;
    }

    /**
     * Obtiene el listado de todas las devoluciones
     *
     * @return
     * @throws SQLException
     */
    public List<Devolucion> getDevoluciones() throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Devolucion dev = null;
        List<Devolucion> lista = new ArrayList<>();

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(LISTADO_DEVOLUCIONES);

        result = statement.executeQuery();

        resulsetDev(result, dev, lista);

        return lista;
    }

    /**
     * *
     * LISTADO DE DEVOLUCIONES POR FECHA
     *
     * @param fecha1
     * @param fecha2
     * @return
     * @throws SQLException
     */
    public List<Devolucion> getDevolucionesPorFecha(String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Devolucion dev = null;
        List<Devolucion> lista = new ArrayList<>();

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(LISTADO_DEVOLUCIONES_ENTRE_FECHAS);
        statement.setString(1, fecha1);
        statement.setString(2, fecha2);

        result = statement.executeQuery();

        resulsetDev(result, dev, lista);

        return lista;
    }

    public Venta getDetalleDevolucion(int idProduco) throws SQLException, NullPointerException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(DETALLE_DEVOLUCION);
        statement.setInt(1, idProduco);

        result = statement.executeQuery();
        while (result.next()) {
            venta = new Venta(result.getInt("id"),
                    result.getString("fecha"),
                    result.getDouble("costo"),
                    result.getString("nit_cliente"),
                    result.getString("nombre_usuario"));
        }

        return venta;

    }

    private void resulsetDev(ResultSet result, Devolucion dev, List lista) throws SQLException {

        while (result.next()) {
            dev = new Devolucion(
                    result.getInt("id"),
                    result.getDouble("perdida"),
                    String.valueOf(result.getDate("fecha")),
                    result.getString("nombre_cliente"),
                    result.getInt("id_producto"));
            lista.add(dev);
        }
    }

}
