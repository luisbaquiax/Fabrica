/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.RequerimientoEnsamblaje;
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
public class RequerimientoEnsamblajeDB {

    private static final String INSERT = "INSERT INTO mueble_pieza(pieza, mueble, cantidad_piezas) VALUES(?,?,?)";
    private static final String SELECT_BY_MUEBLE = "SELECT * FROM mueble_pieza WHERE  mueble = ?";

    /**
     *
     * @param requerimiento
     */
    public void insertarRequierimientoEnsamblaje(RequerimientoEnsamblaje requerimiento) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(INSERT);
            statement.setString(1, requerimiento.getPieza());
            statement.setString(2, requerimiento.getMueble());
            statement.setInt(3, requerimiento.getCantidadPiezas());

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RequerimientoEnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * SELECT * FROM mueble_pieza WHERE mueble = ?
     *
     * @param mueble
     * @return lista requerimientos para ensamblaje
     */
    public List<RequerimientoEnsamblaje> listarRequerimientos(String mueble) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        RequerimientoEnsamblaje reque = null;
        ArrayList<RequerimientoEnsamblaje> requerimientoEnsamblajes = new ArrayList<>();
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(SELECT_BY_MUEBLE);
            statement.setString(1, mueble);

            result = statement.executeQuery();

            while (result.next()) {
                reque = new RequerimientoEnsamblaje(
                        result.getString("pieza"),
                        result.getString("mueble"),
                        result.getInt("cantidad_piezas"));
                requerimientoEnsamblajes.add(reque);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RequerimientoEnsamblajeDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return requerimientoEnsamblajes;
    }
}
