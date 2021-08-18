/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.coneccion;

import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.UsuarioDB;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PiezaDB p = new PiezaDB();
//        p.eliminarPieza("Pieza Hola");
//        for (Pieza pa : p.getPiezas()) {
//            System.out.println(pa.toString());
//        }
//        
        MuebleDB muebleDB = new MuebleDB();
        //muebleDB.insertarMueble(new Mueble("Mesa3", 200));
        //muebleDB.actualizarCantidadMuebles(40, "Mesa3");

        //ENSAMBLAR_MUEBLE(“Mesa rustica”,Luis,”21/04/2021”)
        //ENSAMBLAR_MUEBLE(“Mesa rustica”,Luis,”2021/04/21”)
//        EnsamblajeDB ensamblajeDB = new EnsamblajeDB();
//        try {
//            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate fecha = LocalDate.parse("21/04/2021", formato);
//            ensamblajeDB.insertarEnsamblaje(new Ensamblaje(String.valueOf(fecha), 10, "0", "Mesa rustica", "Luis"));
//            System.out.println("insertado");
//        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        for (int i = 0; i < muebleDB.listarMuebles().size(); i++) {
//            System.out.println(muebleDB.listarMuebles().get(i).toString());
//        }
//        UsuarioDB u = new UsuarioDB();
//        try {
//            System.out.println(u.buscarUsuario("Luis", "admin").toString());
//        } catch (SQLException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
