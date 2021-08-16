/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.coneccion;

import db.modelo.MuebleDB;
import db.modelo.UsuarioDB;
import entidad.Mueble;
import entidad.Usuario;

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
        MuebleDB muebleDB = new MuebleDB();
        //muebleDB.insertarMueble(new Mueble("Mesa3", 200));
        muebleDB.actualizarCantidadMuebles(40, "Mesa3");

    }
    
}
