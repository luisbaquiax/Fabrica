/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.VentaDB;
import entidad.Venta;
import entidad.manejoErrores.FabricaExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet("/controladorTienda")
public class TiendaControlador extends HttpServlet {

    private VentaDB ventaDB;

    public TiendaControlador() {
        this.ventaDB = new VentaDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "devolucion":
                    break;
                default:
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "":
                    break;
                default:
            }
        }
    }

    private void buscarVentaPorID(HttpServletRequest request, HttpServletResponse response) {
        int idVenta = Integer.parseInt(request.getParameter("factura"));
        try {
            Venta venta = this.ventaDB.getVentasByID(idVenta);

        } catch (FabricaExcepcion ex) {

        }
    }
}
