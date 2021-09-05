/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.consulatsTienda;

import db.coneccion.Coneccion;
import entidad.Mueble;
import entidad.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ConsultaTiendaDB {

    private static final String COMPRAS_CLIENTE = ""
            + "SELECT cliente.nit, cliente.nombre, venta.id, venta.nombre_usuario\n"
            + "FROM cliente\n"
            + "RIGHT JOIN venta\n"
            + "ON cliente.nit = venta.nit_cliente WHERE venta.nombre_usuario = ?";
    private static final String VENTAS_DEL_DIA = "SELECT * FROM fabrica.venta fecha WHERE nombre_usuario = ? AND fecha = ?";
    private static final String VER_COMPRAS_CLIENTE = "SELECT * FROM venta WHERE nit_cliente = ?";
    private static final String VER_COMPRAS_CLIENTE_ENTRE_FECHAS = "SELECT * FROM fabrica.venta WHERE nit_cliente = ? AND fecha BETWEEN ? AND ?";
    private static final String DETALLE_FACTURA = ""
            + "SELECT mueble.nombre, mueble.precio\n"
            + "FROM mueble\n"
            + "RIGHT JOIN ensamblaje\n"
            + "ON ensamblaje.nombre_mueble =  mueble.nombre\n"
            + "RIGHT JOIN detalle_venta\n"
            + "ON ensamblaje.id = detalle_venta.id_producto WHERE detalle_venta.id_venta = ?";

    /**
     * Lista los clientes que han comprado al usuario-vendedor
     * <br><br>
     * query: SELECT cliente.nit, cliente.nombre, venta.id, venta.nombre_usuario
     * FROM cliente RIGHT JOIN venta ON cliente.nit = venta.nit_cliente WHERE
     * venta.nombre_usuario = '?'
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    public ArrayList<String[]> getClientesUsuario(String usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<String[]> listaDatos = new ArrayList<>();
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(COMPRAS_CLIENTE);
        statement.setString(1, usuario);

        result = statement.executeQuery();

        while (result.next()) {
            String[] datos = new String[4];
            datos[0] = result.getString("nit");
            datos[1] = result.getString("nombre");
            datos[2] = result.getInt("id") + "";
            datos[3] = result.getString("nombre_usuario");
            listaDatos.add(datos);
        }
        return listaDatos;
    }

    /**
     * query: SELECT * FROM venta WHERE nit_cliente = ?
     *
     * @param nit
     * @return Retorna una lista de compras de algun cliente
     * @throws SQLException
     */
    public ArrayList<Venta> getVentasPorNitCliente(String nit) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> compras = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(VER_COMPRAS_CLIENTE);
        statement.setString(1, nit);

        result = statement.executeQuery();

        while (result.next()) {
            venta = new Venta(
                    result.getInt("id"),
                    String.valueOf(result.getDate("fecha")),
                    result.getDouble("costo"),
                    result.getString("nit_cliente"),
                    result.getString("nombre_usuario"));
            compras.add(venta);
        }
        return compras;
    }

    /**
     * query: SELECT * FROM fabrica.venta WHERE nit_cliente = ? AND fecha
     * BETWEEN ? AND ?
     *
     * @param nit
     * @param fecha1
     * @param fecha2
     * @return Retorna una lista de ventas de un cliente entre dos fechas.
     * @throws SQLException
     */
    public ArrayList<Venta> getVentasPorNitCliente(String nit, String fecha1, String fecha2) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> compras = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(VER_COMPRAS_CLIENTE_ENTRE_FECHAS);
        statement.setString(1, nit);
        statement.setDate(2, Date.valueOf(fecha1));
        statement.setDate(3, Date.valueOf(fecha2));

        result = statement.executeQuery();

        while (result.next()) {
            venta = new Venta(
                    result.getInt("id"),
                    String.valueOf(result.getDate("fecha")),
                    result.getDouble("costo"),
                    result.getString("nit_cliente"),
                    result.getString("nombre_usuario"));
            compras.add(venta);
        }
        return compras;
    }

    /**
     * Ventas del dia
     * <br><br>
     * query: SELECT * FROM fabrica.venta fecha WHERE nombre_usuario = "?" AND
     * fecha = "?"
     *
     * @param usuario
     * @param fecha
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Venta> getVentasDelDia(String usuario, String fecha) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Venta> lista = new ArrayList<>();
        Venta venta = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(VENTAS_DEL_DIA);
        statement.setString(1, usuario);
        statement.setString(2, fecha);

        result = statement.executeQuery();

        while (result.next()) {
            venta = new Venta(
                    result.getInt("id"),
                    String.valueOf(result.getDate("fecha")),
                    result.getDouble("costo"),
                    result.getString("nit_cliente"),
                    result.getString("nombre_usuario"));
            lista.add(venta);
        }
        return lista;
    }

    /**
     * Selecciona los productos de una compra para mostrar el detalle de la
     * factura de alguna compra
     * <br><br>
     * query: SELECT mueble.nombre, mueble.precio FROM mueble RIGHT JOIN
     * ensamblaje ON ensamblaje.nombre_mueble = mueble.nombre RIGHT JOIN
     * detalle_venta ON ensamblaje.id = detalle_venta.id_producto WHERE
     * detalle_venta.id_venta = ?
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public List<Mueble> getMueblesEnFactura(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Mueble> lista = new ArrayList<>();
        Mueble mueble = null;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(DETALLE_FACTURA);
        statement.setInt(1, id);

        result = statement.executeQuery();

        while (result.next()) {
            mueble = new Mueble(result.getString("nombre"), result.getDouble("precio"));
            lista.add(mueble);
        }
        return lista;
    }
}
