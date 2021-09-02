/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.coneccion;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Coneccion {

    private static final String URL = "jdbc:mysql://localhost:3306/fabrica?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "luisbaquiax1234";//@luis.baquiax95 luisbaquiax1234

    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     *
     * Cerramos primero el objeto PreparedStatement después el objeto Connection
     *
     * @param statement
     * @param conn
     */
    public static void close(PreparedStatement statement, Connection conn) {
        try {
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cerramos el ResulSet, PreparedStatement, Connection
     *
     * @param result
     * @param statement
     * @param conn
     */
    public static void close(ResultSet result, PreparedStatement statement, Connection conn) {
        try {
            result.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
