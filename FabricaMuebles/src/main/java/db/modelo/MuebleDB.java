/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Mueble;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class MuebleDB {

    private static final String INSERT = "INSERT INTO mueble(nombre, precio, cantidad) VALUES(?,?,?)";
    private static final String UPDATE_CANTIDADES = "UPDATE mueble SET cantidad = ? WHERE nombre = ?";
    private static final String LISTAR_MUEBLES = "SELECT * FROM mueble";

    /**
     * Insert mueble in the DB
     *
     * @param mueble
     */
    public void insertarMueble(Mueble mueble) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, mueble.getNombre());
            statement.setDouble(2, mueble.getPrecio());
            statement.setInt(3, mueble.getCantidadExistente());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param cantidad
     * @param nombre
     */
    public void actualizarCantidadMuebles(int cantidad, String nombre) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(UPDATE_CANTIDADES);

            statement.setInt(1, cantidad);
            statement.setString(2, nombre);

            registros = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error al actualizar");
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * LISTADO DE MUEBLES EXISTENTES
     *
     * @return
     */
    public List<Mueble> listarMuebles() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Mueble mueble = null;
        List<Mueble> muebles = new ArrayList<>();
        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LISTAR_MUEBLES);
            result = statement.executeQuery();

            while (result.next()) {
                String nombre = result.getString("nombre");
                double precio = result.getDouble("precio");
                int cantidad = result.getInt("cantidad");

                mueble = new Mueble(nombre, precio, cantidad);
                muebles.add(mueble);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muebles;
    }
}
