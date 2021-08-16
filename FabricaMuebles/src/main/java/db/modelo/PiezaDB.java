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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class PiezaDB {

    private static final String INSERT = "INSERT INTO pieza(tipo, costo) VALUES(?,?)";
    private static final String UPDATE_CANTIDADES = "UPDATE pieza SET cantidad = ? WHERE tipo = ?";
    private static final String UPDATE_PIEZA = "UPDATE pieza SET cantidad = ?, costo = ? WHERE tipo = ?";

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

}
