/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.consulatsTienda.ConsultaTiendaDB;
import db.modelo.ClienteDB;
import db.modelo.DevolucionDB;
import db.modelo.ProductoDB;
import db.modelo.VentaDB;
import entidad.Cliente;
import entidad.Devolucion;
import entidad.Mueble;
import entidad.Producto;
import entidad.Usuario;
import entidad.Venta;
import entidad.manejoErrores.FabricaExcepcion;
import entidad.utiles.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
@WebServlet("/controladorTienda")
public class TiendaControlador extends HttpServlet {

    private VentaDB ventaDB;
    private ConsultaTiendaDB consultaTiendaDB;
    private ProductoDB productoDB;
    private ClienteDB clienteDB;
    private Util util;
    private DevolucionDB devolucionDB;

    public TiendaControlador() {
        this.ventaDB = new VentaDB();
        this.consultaTiendaDB = new ConsultaTiendaDB();
        this.productoDB = new ProductoDB();
        this.clienteDB = new ClienteDB();
        this.util = new Util();
        this.devolucionDB = new DevolucionDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user.getTipo().equals(Usuario.VENTA) && (user != null)) {
            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "verComprasFecha":
                        verComprasDeUnClientePorFecha(request, response);
                        break;
                    case "devolucionFecha":
                        verDevolucionesPorFechas(request, response);
                        break;
                    default:
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/Login");
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");

        } else {
            if (user.getTipo().equals(Usuario.VENTA) && (user != null)) {
                String tarea = request.getParameter("tarea");
                if (tarea != null) {
                    switch (tarea) {
                        case "listadoClientes":
                            listarClientes(request, response);
                            break;
                        case "verCompras":
                            verComprasDeUnCliente(request, response);
                            break;
                        case "verDetalle":
                            verDetalleCompra(request, response);
                            break;
                        case "productosDisponibles":
                            mostrarProductoDisponibles(request, response);
                            break;
                        case "ventasDelDia":
                            verVentasDelDia(request, response);
                            break;
                        case "verDevolucion":
                            verDevoluciones(request, response);
                            break;
                        default:

                    }
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
        }

    }

    private void verComprasDeUnCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nit = request.getParameter("nit");
            ArrayList<Venta> compras = this.consultaTiendaDB.getVentasPorNitCliente(nit);
            request.getSession().setAttribute("nit", nit);
            request.getSession().setAttribute("compras", compras);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/clientesCompras.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verComprasDeUnClientePorFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");
            String nit = request.getParameter("nit");

            if ((fecha1 != null) && (fecha2 != null)
                    && ((this.util.formatoHecho(fecha1)) && this.util.formatoHecho(fecha2))) {
                ArrayList<Venta> compras = this.consultaTiendaDB.getVentasPorNitCliente(nit, fecha1, fecha2);
                request.getSession().setAttribute("compras", compras);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/clientesCompras.jsp");
            } else {
                verComprasDeUnCliente(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verDetalleCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            List<Mueble> mueblesEnFactura = this.consultaTiendaDB.getMueblesEnFactura(id);
            Venta venta = this.ventaDB.getVentasByID(id);
            Cliente cliente = this.clienteDB.getClientPorNit(venta.getNitCliente());
            request.getSession().setAttribute("cliente", cliente);
            request.getSession().setAttribute("venta", venta);
            request.getSession().setAttribute("mueblesFactura", mueblesEnFactura);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/detalleCompras.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FabricaExcepcion ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProductoDisponibles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Producto> muebles = (ArrayList<Producto>) this.productoDB.getProducts();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("muebles", muebles);
        response.sendRedirect("/FabricaMuebles/JSP/Vendedor/productosDisponibles.jsp");
    }

    private void verVentasDelDia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String user = request.getParameter("user");
            ArrayList<Venta> ventasHoy = this.consultaTiendaDB.getVentasDelDia(user, String.valueOf(LocalDate.now()));
            for (Venta venta : ventasHoy) {
                System.out.println(venta.toString());
            }
            request.getSession().setAttribute("ventasHoy", ventasHoy);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/ventasHoy.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Cliente> clientesDisponibles = this.clienteDB.getTodosClientes();
        request.getSession().setAttribute("clientes", clientesDisponibles);
        response.sendRedirect("/FabricaMuebles/JSP/Vendedor/listadoClientes.jsp");
    }

    private void verDevoluciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getSession().removeAttribute("fecha1DEV");
            request.getSession().removeAttribute("fecha2DEV");
            String nit = request.getParameter("nit");
            List<Devolucion> listaDevolucions = this.devolucionDB.getDevoluciones(nit);
            request.getSession().setAttribute("nit", nit);
            request.getSession().setAttribute("devoluciones", listaDevolucions);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/listaDevolucion.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verDevolucionesPorFechas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nit = request.getParameter("nit");
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");
            if ((fecha1 != null) && (fecha2 != null)
                    && ((this.util.formatoHecho(fecha1)) && this.util.formatoHecho(fecha2))) {
                List<Devolucion> listaDevolucions = this.devolucionDB.getDevolucionesEntreFechas(nit, fecha1, fecha2);
                request.getSession().setAttribute("fecha1DEV", fecha1);
                request.getSession().setAttribute("fecha2DEV", fecha2);
                request.getSession().setAttribute("nit", nit);
                request.getSession().setAttribute("devoluciones", listaDevolucions);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/listaDevolucion.jsp");
            } else {
                verDevoluciones(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TiendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
