/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.DetalleEnsamblaje;
import java.sql.*;

/**
 *
 * @author luis
 */
public class DetalleEnsamblajeDB {

    private static final String INSERT = "INSERT INTO detalle_ensamblaje(id_ensamblaje, costo_pieza, tipo_pieza) VALUES(?,?,?)";

    /**
     *
     * Inserta algún detalle de una venta a la base de datos en base el
     * id_ensamblaje
     *
     * @param detalle
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void insertarDetalleDelEnsamblaje(DetalleEnsamblaje detalle) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(INSERT);

        statement.setInt(1, detalle.getIdEnsamblaje());
        statement.setDouble(2, detalle.getCostoPieza());
        statement.setString(3, detalle.getTipoPieza());

        registros = statement.executeUpdate();
    }
}
