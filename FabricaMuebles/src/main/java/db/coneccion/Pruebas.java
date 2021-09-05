/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.coneccion;

import db.consulatsTienda.ConsultaTiendaDB;
import db.modelo.ClienteDB;
import db.modelo.DetalleEnsamblajeDB;
import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.PrecioPiezaDB;
import db.modelo.ProductoDB;
import db.modelo.RequerimientoEnsamblajeDB;
import db.modelo.UsuarioDB;
import db.modelo.VentaDB;
import entidad.Cliente;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.Producto;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import entidad.Venta;
import entidad.manejoErrores.FabricaExcepcion;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        PiezaDB piezaDB = new PiezaDB();
        RequerimientoEnsamblajeDB redb = new RequerimientoEnsamblajeDB();
        EnsamblajeDB ensamblajeDB = new EnsamblajeDB();
        MuebleDB muebleDB = new MuebleDB();
        VentaDB ventaDB = new VentaDB();
        UsuarioDB usuarioDB = new UsuarioDB();
        ProductoDB productoDB = new ProductoDB();
        PrecioPiezaDB precioPiezaDB = new PrecioPiezaDB();
        ClienteDB clienteDB = new ClienteDB();
        DetalleEnsamblajeDB detalleEnsamblajeDB = new DetalleEnsamblajeDB();
        ConsultaTiendaDB consultaTiendaDB = new ConsultaTiendaDB();
        try {
            for (Mueble m : consultaTiendaDB.getMueblesEnFactura(1)) {
                System.out.println(m.toString());
            }
//            for (Venta v : consultaTiendaDB.getVentasPorNitCliente("31919444")) {
//                System.out.println(v.toString());
//            }
        
            
//            for (Venta v : consultaTiendaDB.getVentasPorNitCliente("31919444", "2021-09-04", "2021-09-04")) {
//                System.out.println(v.toString());
//            }
        } catch (Exception e) {
        }

//        for (Usuario u : usuarioDB.getTodosUsuarios()) {
//            System.out.println(u.toString());
//        }
//            for (Cliente c : clienteDB.getTodosClientes()) {
//                System.out.println(c.toString());
//            }
//            System.out.println("");
//            ConsultaTiendaDB consultaTiendaDB = new ConsultaTiendaDB();
//            for (Venta Venta : consultaTiendaDB.getVentasDelDia("D", String.valueOf(LocalDate.now()))) {
//                System.out.println(Venta.toString());
//            }
//            ArrayList<String[]> lista = consultaTiendaDB.getClientesUsuario("D");
//            for (String[] strings : lista) {
//                System.out.println(strings[0]);
//                System.out.println(strings[1]);rrrrr
//                System.out.println(strings[2]);
//                System.out.println(strings[3]);
//            }
//            
//            try {
//                System.out.println(precioPiezaDB.getPiezaPorTipo("Pata cuadrada").toString());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
////        for (Usuario a : usuarioDB.getTodosUsuarios()) {A
////            System.out.println(a.toString());
////        }
////        System.out.println(detalleEnsamblajeDB.getCostoEnsamblaje(1));
//
////        System.out.println(clienteDB.getClientPorNit("nit45").toString());
////        for (Ensamblaje a : ensamblajeDB.getEnsamblajesPorUsuario("C")) {
////            System.out.println(a.toString());
////        }
//for (Producto a : productoDB.getProducts()) {
//    System.out.println(a.toString());
//}
////        
//        for (Usuario u : usuarioDB.getUsurariosVentaYFabrica()) {
//            System.out.println(u.toString());
//        }
//        try {
//            System.out.println(ventaDB.getVentasByID(100));
//
//        } catch (FabricaExcepcion e) {
//            e.printStackTrace();
//        }
//        for (Venta v : ventaDB.getVentas()) {
//            System.out.println(v.toString());
//        }
//        System.out.println("el ultimo");
//        System.out.println(ventaDB.getVentas().get(ventaDB.getVentas().size() - 1).getId() + 1);
//        System.out.println(edb.getEnsamblajesPorID(1));
//        for (Ensamblaje e : edb.getEnsamblajesPorEstadoYUsuario(false, "Luis")) {
//            System.out.println(e.toString());
//        }
//        try {
//            edb.insertarEnsamblaje(new Ensamblaje("2021-05-02", 50, "true", "Mesa rustica", "Luis"));
//        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (Ensamblaje e : edb.getEnsamblajes()) {
//            System.out.println(e.toString());
//        }
//        for (RequerimientoEnsamblaje re : redb.listarRequerimientos("Mesa rustica")) {
//            System.out.println(re.toString());
//        }
//        p.editarPieza(new Pieza("Pieza Hola", 50));
//        p.eliminarPieza("Pieza Hola");
//        for (Pieza pa : p.getPiezas()) {
//            System.out.println(pa.toString());
//        }
//        
//        String mueble = "Mesa rustica";
//        ArrayList<RequerimientoEnsamblaje> requerimientos = (ArrayList<RequerimientoEnsamblaje>) redb.listarRequerimientos(mueble);
//        double costoEnsambleje = 0;
//
//        //mensaje en caso de un error
//        String mensaje = "";
//
//        boolean salir = false;
//        for (RequerimientoEnsamblaje requerimiento : requerimientos) {
//            Pieza temp = p.getPiezaPorTipo(requerimiento.getPieza());
//
//            if (temp.getCantidadExistente() >= requerimiento.getCantidadPiezas()) {
//                costoEnsambleje += requerimiento.getCantidadPiezas() * temp.getCosto();
//                temp.quitarCantidad(requerimiento.getCantidadPiezas());
//                p.editarPieza(temp);
//            } else {
//                salir = true;
//            }
//        }
//        System.out.println("costo: " + costoEnsambleje);
//        System.out.println();
//
//        Mueble agregado = muebleDB.getMueblePorNombre(mueble);
//        try {
//            en.insertarEnsamblaje(new Ensamblaje(String.valueOf(LocalDate.now()), 100, "0", agregado.getNombre(), "Luis"));
//
//            System.out.println("insertado");
//        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//            System.out.println("error al insertar");
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        System.out.println(muebleDB.getMueblePorNombre("Mesa rustica").toString());
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
