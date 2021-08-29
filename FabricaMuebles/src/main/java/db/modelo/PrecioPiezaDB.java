/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.modelo;

import db.coneccion.Coneccion;
import entidad.Pieza;
import entidad.manejoErrores.FabricaExcepcion;
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
    private static final String LIST_PIEZAS = "SELECT * FROM precio_pieza WHERE estado = ?";
    private static final String PIEZAS_AGOTADAS_O_CASI_AGOTADAS = "SELECT * FROM precio_pieza WHERE cantidad < 5";
    private static final String LIST_PIEZAS_CANTIDAD_ASC = "SELECT * FROM precio_pieza WHERE estado = 0 ORDER BY cantidad ASC";
    private static final String LIST_PIEZAS_CANTIDAD_DESC = "SELECT * FROM precio_pieza WHERE estado = 0 ORDER BY cantidad DESC";
    private static final String SELECT_BY_TIPO_COSTO = "SELECT * FROM precio_pieza WHERE pieza = ? AND costo = ?";
    private static final String UPDATE_PIEZA = "UPDATE precio_pieza SET cantidad = ?, costo = ?, estado = ? WHERE pieza = ? AND costo = ?";

    private static final String OBTENER_PIEZAS_POR_NOMBRE = "SELECT * FROM precio_pieza WHERE pieza = ? AND cantidad > 0 AND estado = 0";

    /**
     * Insert a new pieza-precio in the database specfic in the table
     * precio-pieza
     *
     * @param pieza
     * @throws java.sql.SQLException
     */
    public void insertarPrecioPieza(Pieza pieza) throws SQLException {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update a precio_pieza by pieza and costo
     * <br><br>
     * query: UPDATE_PIEZA = "UPDATE precio_pieza SET cantidad = ?, costo = ?,
     * estado = ? WHERE pieza = ? AND costo = ?"
     *
     * @param pieza
     * @param piezaBuscado
     * @param precioBuscado
     */
    public void actualizarPrecioPieza(Pieza pieza, String piezaBuscado, double precioBuscado) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {

            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(UPDATE_PIEZA);

            statement.setInt(1, pieza.getCantidadExistente());
            statement.setDouble(2, pieza.getCosto());
            statement.setBoolean(3, pieza.getEstado());
            statement.setString(4, piezaBuscado);
            statement.setDouble(5, precioBuscado);

            registros = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene lista de piezas dependiendo de su estado eliminado(obsoletas) no
     * eliminado(sigue su uso)
     * <br><br>query: LIST_PIEZAS = "SELECT * FROM precio_pieza WHERE estado =
     * ?"
     *
     * @param estadoEliminado
     * @return
     */
    public List<Pieza> getPreciosPiezas(boolean estadoEliminado) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS);
            statement.setBoolean(1, estadoEliminado);
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
     * Obtiene lista de piezas ordenados según cantidad de existentes
     * ascendentemente
     *
     * @return
     */
    public List<Pieza> getPiezasAscedentemente() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS_CANTIDAD_ASC);
            result = statement.executeQuery();

            obtenerPiezas(result, piezas, pieza);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Obtiene lista de piezas ordenados según cantidad de existentes
     * ascendentemente
     *
     * @return
     */
    public List<Pieza> getPiezasDescedentemente() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(LIST_PIEZAS_CANTIDAD_DESC);
            result = statement.executeQuery();

            obtenerPiezas(result, piezas, pieza);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Seleciona de la tabla precio_pieza una pieza en particular por su tipo y
     * precio <br><br>query: SELECT_BY_TIPO_COSTO = "SELECT * FROM precio_pieza
     * WHERE pieza = ? AND costo = ?"
     *
     * @param nombre
     * @param precio
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws FabricaExcepcion
     */
    public Pieza getPiezaPorPrecioYNombre(String nombre, double precio)
            throws SQLException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            FabricaExcepcion {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;

        conn = Coneccion.getConnection();
        statement = conn.prepareStatement(SELECT_BY_TIPO_COSTO);
        statement.setString(1, nombre);
        statement.setDouble(2, precio);

        result = statement.executeQuery();

        pieza = obtenerPieza(result, pieza);

        if (pieza == null) {
            throw new FabricaExcepcion("Pieza no encontrada. Comuníquese con el administrador de base de datos.");
        }
        return pieza;

    }

    /**
     * Obtiene una lista de piezas de la tabla precio-pieza en base a su nombre
     * y que su cantidad sea mayor que 0 y su estado sea '0' es decir que no
     * está,esto se usarán para realizar una ensamblaje obsoleto
     * <br><br>
     * query: SELECT * FROM precio_pieza WHERE pieza = ? AND cantidad > 0 AND
     * estado = 0
     *
     * @param tipo
     * @return
     */
    public List<Pieza> getPiezaPorTipo(String tipo) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Pieza pieza = null;
        List<Pieza> piezas = new ArrayList<>();

        try {
            conn = Coneccion.getConnection();
            statement = conn.prepareStatement(OBTENER_PIEZAS_POR_NOMBRE);
            result = statement.executeQuery();

            obtenerPiezas(result, piezas, pieza);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PiezaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    /**
     * Usa el objeto resultset para obtener los datos de una pieza en particular
     *
     * @param result
     * @param piezas
     * @param pieza
     * @throws SQLException
     */
    private Pieza obtenerPieza(ResultSet result, Pieza pieza) throws SQLException {
        while (result.next()) {
            pieza = new Pieza(
                    result.getString("pieza"),
                    result.getDouble("costo"),
                    result.getInt("cantidad"),
                    result.getBoolean("estado"));
        }
        return pieza;
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
