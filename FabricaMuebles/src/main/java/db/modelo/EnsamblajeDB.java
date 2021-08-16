/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Ensamblaje;
import entidad.RequerimientoEnsamblaje;
import java.sql.*;

/**
 *
 * @author luis
 */
public class EnsamblajeDB {

    private static final String INSERT = "INSERT INTO ensamblaje(fecha, costo, estado, nombre_mueble, nombre_usuario) VALUES(?, ?, ?, ?, ?)";

    /**
     *
     * @param ensamblaje
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void insertarMueblePieza(Ensamblaje ensamblaje) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setDate(1, Date.valueOf(ensamblaje.getFecha()));
        statement.setDouble(2, ensamblaje.getCosto());
        statement.setBoolean(3, Boolean.valueOf(ensamblaje.getEstado()));
        statement.setString(4, ensamblaje.getMueble());
        statement.setString(5, ensamblaje.getUsuario());

        registros = statement.executeUpdate(INSERT);
    }
}
