/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Pieza;
import entidad.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ProductoDB {

    private static final String INSERT = "INSERT INTO producto(id_ensamblaje, estado) VALUES(?,?)";
    private static final String UPDATE_PRODUCTO = "UPDATE producto SET estado = ? WHERE id_ensamblaje = ?";
    private static final String PRODUCTOS = "SELECT ensamblaje.nombre_mueble, producto.id_ensamblaje, mueble.precio\n"
            + "FROM ensamblaje\n"
            + "RIGHT JOIN producto\n"
            + "ON ensamblaje.id=producto.id_ensamblaje\n"
            + "RIGHT JOIN mueble\n"
            + "ON mueble.nombre=ensamblaje.nombre_mueble "
            + "WHERE producto.estado = 0 "
            + "ORDER BY id_ensamblaje";

    /**
     * Inserta un producto existente a la base de datos
     *
     * @param idEnsamblaje
     * @param estado
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void insertarProducto(int idEnsamblaje, boolean estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setInt(1, idEnsamblaje);
        statement.setBoolean(2, estado);

        registros = statement.executeUpdate();

        /*if (conn != null) {
            Coneccion.close(statement, conn);

        }*/
    }

    /**
     * Actualiza un producto cambiando el estado a vendido
     * <br><br>
     * query: UPDATE producto SET estado = ? WHERE id_ensamblaje
     *
     * @param producto
     * @throws SQLException
     */
    public void actualizarProducto(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(UPDATE_PRODUCTO);

        statement.setBoolean(1, producto.isVendido());
        statement.setInt(2, producto.getIdentificador());

        registros = statement.executeUpdate();

        /*if (conn != null) {
            Coneccion.close(statement, conn);

        }*/
    }

    /**
     * Muestra los productos disponibles
     * <br><br>
     * query: SELECT ensamblaje.nombre_mueble, producto.id_ensamblaje,
     * mueble.nombre, mueble.precio<br>
     * FROM ensamblaje<br>
     * RIGHT JOIN producto<br>
     * ON ensamblaje.id=producto.id_ensamblaje<br>
     * RIGHT JOIN mueble<br>
     * ON mueble.nombre=ensamblaje.nombre_mueble <br>
     * WHERE producto.estado = '0'<br>
     * ORDER BY id_ensamblaje<br>
     *
     * @return
     */
    public List<Producto> getProducts() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(PRODUCTOS);
            result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto(
                        result.getString("nombre_mueble"),
                        result.getDouble("precio"),
                        result.getInt("id_ensamblaje"));
                productos.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*finally {
            if (conn != null) {
                Coneccion.close(conn);

            }
        }*/
        return productos;
    }
}
