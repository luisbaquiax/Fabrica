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
     *
     * @param pieza
     */
    public void editarPieza(Pieza pieza) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(UPDATE_PIEZA);

            statement.setInt(1, pieza.getCantidadExistente());
            statement.setDouble(2, pieza.getCosto());
            statement.setString(3, pieza.getTipo());

            registros = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error al actualizar");
            Logger.getLogger(MuebleDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
