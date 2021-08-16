/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Devolucion;
import java.sql.*;

/**
 *
 * @author luis
 */
public class DevolucionDB {

    private static final String INSERT = "INSERT INTO devolucion(perdida, fecha, nombre_cliente, nombre_mueble) VALUES(?,?,?,?)";

    public void insertarDevolucion(Devolucion devolucion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registro = 0;
        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setDouble(1, devolucion.getPerdida());
        statement.setString(2, devolucion.getFecha());
        statement.setString(3, devolucion.getNombreCliente());
        statement.setString(4, devolucion.getNombreMueble());
        registro = statement.executeUpdate();

    }

}
