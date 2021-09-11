/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.consulatsTienda.ConsultaTiendaDB;
import db.modelo.ClienteDB;
import db.modelo.VentaDB;
import db.reportesAdministracion.ReportesAdminDB;
import entidad.Cliente;
import entidad.Mueble;
import entidad.Usuario;
import entidad.Venta;
import entidad.file.escribirReportes.Reporte;
import entidad.manejoErrores.FabricaExcepcion;
import entidad.utiles.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet("/controladorReportes")
public class ControladorReportesAdministracion extends HttpServlet {

    private VentaDB ventaDB;
    private ReportesAdminDB reportesAdminDB;
    private Util util;
    private ConsultaTiendaDB consultaTiendaDB;
    private ClienteDB clienteDB;
    private Reporte reporte;

    public ControladorReportesAdministracion() {
        this.ventaDB = new VentaDB();
        this.reportesAdminDB = new ReportesAdminDB();
        this.util = new Util();
        this.consultaTiendaDB = new ConsultaTiendaDB();
        this.clienteDB = new ClienteDB();
        this.reporte = new Reporte();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user != null && (user.getTipo().equals(Usuario.FINANCIERO))) {
            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "reporteVentaFechas":
                        reportarVentasPorFecha(request, response);
                        break;
                    case "usuarioMasVentasFechas":
                        obtenerUsuarioMasVentasFechas(request, response);
                        break;
                    default:
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user != null && (user.getTipo().equals(Usuario.FINANCIERO))) {
            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "reporteVentas":
                        reportarVentas(request, response);
                        break;
                    case "verDetalleVenta":
                        verDetalleVenta(request, response);
                        break;
                    case "usuarioMasVentas":
                        obtenerUsuarioMasVentas(request, response);
                        break;
                    case "verVentas":
                        verVentasUsuarioMas(request, response);
                        break;
                    case "muebleMasVendido":
                        break;
                    case "exportar1":
                        break;
                    default:
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    private void reportarVentas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Venta> ventas = this.ventaDB.getVentas();

        this.reporte.escribirVentas(ventas);
        String ruta = "ventas.CSV";

        request.getSession().setAttribute("ventas", ventas);
        request.getSession().setAttribute("ruta", ruta);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/listadoVentas.jsp");
    }

    private void reportarVentasPorFecha(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");
            if ((fecha1 != null) && (fecha2 != null)
                    && ((this.util.formatoHecho(fecha1)) && this.util.formatoHecho(fecha2))) {
                List<Venta> ventas = this.reportesAdminDB.getVentasEntreFechas(fecha1, fecha2);

                this.reporte.escribirVentas(ventas);
                String ruta = "ventas.CSV";

                request.getSession().setAttribute("ruta", ruta);
                request.getSession().setAttribute("ventas", ventas);
                response.sendRedirect("/FabricaMuebles/JSP/Administrador/listadoVentas.jsp");
            } else {
                reportarVentas(request, response);
            }
        } catch (Exception e) {
        }

    }

    private void verDetalleVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            List<Mueble> mueblesDeVenta = this.consultaTiendaDB.getMueblesEnFactura(id);
            Venta venta = this.ventaDB.getVentasByID(id);
            request.getSession().setAttribute("venta", venta);
            request.getSession().setAttribute("muebles", mueblesDeVenta);
            response.sendRedirect("/FabricaMuebles/JSP/Administrador/detalleVenta.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FabricaExcepcion ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obtenerUsuarioMasVentas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getSession().removeAttribute("fechaUser1");
            request.getSession().removeAttribute("fechaUser2");
            Usuario masVentas = this.reportesAdminDB.getUsuarioMasVentas();

            request.getSession().setAttribute("usuarioVentas", masVentas);
            response.sendRedirect("/FabricaMuebles/JSP/Administrador/usuarioVentas.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ControladorReportesAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Muestra el usuario con más ventas en un intervalo de fecha
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void obtenerUsuarioMasVentasFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");

            if ((fecha1 != null) && (fecha2 != null)
                    && ((this.util.formatoHecho(fecha1)) && this.util.formatoHecho(fecha2))) {
                Usuario masVentas = this.reportesAdminDB.getUsuarioMasVentasEnFechas(fecha1, fecha2);
                request.getSession().setAttribute("fechaUser1", fecha1);
                request.getSession().setAttribute("fechaUser2", fecha2);
                request.getSession().setAttribute("usuarioVentas", masVentas);
                response.sendRedirect("/FabricaMuebles/JSP/Administrador/usuarioVentas.jsp");
            } else {
                obtenerUsuarioMasVentas(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorReportesAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verVentasUsuarioMas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String user = request.getParameter("user");
            String fecha1 = (String) request.getSession().getAttribute("fechaUser1");
            String fecha2 = (String) request.getSession().getAttribute("fechaUser2");
            if (fecha1 != null && fecha2 != null) {
                List<Venta> ventasUser = this.reportesAdminDB.getUserVentasPorNombreYFechas(user, fecha1, fecha2);

                String ruta = "ventasUser.CSV";
                this.reporte.escribirVentasUser(ventasUser);

                request.getSession().setAttribute("ruta", ruta);
                request.getSession().setAttribute("ventasUser", ventasUser);
                response.sendRedirect("/FabricaMuebles/JSP/Administrador/listaVentasUserMas.jsp");
            } else {
                List<Venta> ventasUser = this.reportesAdminDB.getUserVentasByName(user);
               
                this.reporte.escribirVentasUser(ventasUser);
                String ruta = "ventasUser.CSV";
                request.getSession().setAttribute("ruta", ruta);
                request.getSession().setAttribute("ventasUser", ventasUser);
                response.sendRedirect("/FabricaMuebles/JSP/Administrador/listaVentasUserMas.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorReportesAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getMuebleMasVendidoHttpServletRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void exportarVentasReporte1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Venta> ventas = (List<Venta>) request.getSession().getAttribute("ventas");

        response.sendRedirect("/FabricaMuebles/JSP/Administrador/listadoVentas.jsp");
    }
}
