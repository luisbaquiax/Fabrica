/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file.escribirReportes;

import db.consulatsTienda.ConsultaTiendaDB;
import entidad.Mueble;
import entidad.Venta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Reporte {

    private EscribirArchivoCSV escribe;
    public static final String RUTA = "Reportes";
    private ConsultaTiendaDB consultaTiendaDB;
    private String contenido = "";

    public Reporte() {
        this.escribe = new EscribirArchivoCSV();
        this.consultaTiendaDB = new ConsultaTiendaDB();
    }

    public void escribirVentas(List<Venta> ventas) {
        contenido = "";
        contenido += "Identificador de venta, Fecha de venta, NIT-Cliente, Usuario, Mueble vendido, Precio Unitario\n";
        for (Venta venta : ventas) {

            try {
                List<Mueble> mueblesDeVenta = this.consultaTiendaDB.getMueblesEnFactura(venta.getId());
                for (Mueble mueble : mueblesDeVenta) {
                    contenido += venta.getId() + ","
                            + venta.getFecha() + ","
                            + venta.getNitCliente() + ","
                            + venta.getUsuario() + ","
                            + mueble.getNombre() + ","
                            + mueble.getPrecio() + "\n";
                }
            } catch (SQLException ex) {
            }
        }
        this.escribe.escribirArchivodeTexto("reporte1.CSV", contenido);

    }

}
