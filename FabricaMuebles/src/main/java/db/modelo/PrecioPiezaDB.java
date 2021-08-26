/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class PrecioPiezaDB {

    private static final String INSERT = "INSERT INTO precio_pieza(pieza, costo, cantidad, estado) VALUES(?,?,?,?)";
    private static final String LIST_PIEZAS = "SELECT * FROM precio_pieza";
    private static final String PIEZAS_AGOTADAS_O_CASI_AGOTADAS = "SELECT * FROM precio_pieza WHERE cantidad < 5";

    public void insertarPrecioPieza(Pieza pieza) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);

            statement.setString(1, pieza.getTipo());
            statement.setDouble(2, pieza.getCosto());
            statement.setInt(3, pieza.getCantidadExistente());
            statement.setBoolean(4, pieza.getEstado());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene lista de piezas
     *
     * @return
     */
    public List<Pieza> getPreciosPiezas() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS);
            result = statement.executeQuery();

            obtenerPiezas(result, piezas, pieza);

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

            obtenerPiezas(result, piezas, pieza);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Usa el objeto resultset para obtener los datos
     *
     * @param result
     * @param piezas
     * @param pieza
     * @throws SQLException
     */
    private void obtenerPiezas(ResultSet result, List<Pieza> piezas, Pieza pieza) throws SQLException {

        while (result.next()) {
            pieza = new Pieza(
                    result.getString("pieza"),
                    result.getDouble("costo"),
                    result.getInt("cantidad"),
                    result.getBoolean("estado")
            );
            piezas.add(pieza);
        }
    }
}
