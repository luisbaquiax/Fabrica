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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class RequerimientoEnsamblajeDB {

    private static final String INSERT = "INSERT INTO mueble_pieza(pieza, mueble, cantidad_piezas) VALUES(?,?,?)";

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
}
