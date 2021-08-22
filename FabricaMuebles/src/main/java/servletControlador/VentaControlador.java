/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.ClienteDB;
import db.modelo.MuebleDB;
import db.modelo.UsuarioDB;
import db.modelo.VentaDB;
import entidad.Cliente;
import entidad.Mueble;
import entidad.Usuario;
import entidad.Venta;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
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
@WebServlet("/ControladorVenta")
public class VentaControlador extends HttpServlet {

    private MuebleDB muebleDB;
    private ClienteDB clienteDB;
    private VentaDB ventaDB;
    private UsuarioDB usuarioDB;

    public VentaControlador() {
        this.muebleDB = new MuebleDB();
        this.clienteDB = new ClienteDB();
        this.ventaDB = new VentaDB();
        this.usuarioDB = new UsuarioDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "buscarCliente":
                    comprarMueble(request, response);
                    break;
                case "registrarCliente":
                    registrarClienteYcompra(request, response);
                    break;
                default:

            }
        } else {
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "solicitarCompra":
                    solicitarComra(request, response);
                    break;
                default:

            }
        } else {

        }
    }

    private void solicitarComra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LinkedList<Usuario> cajeros = this.usuarioDB.getUsurariosAreaDeVenta();

        if (cajeros.size() > 0) {
            String mueble = request.getParameter("mueble");
            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
            System.out.println(buscado.toString());
            request.getSession().setAttribute("cajeros", cajeros);
            request.getSession().setAttribute("mueble", buscado);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/compra.jsp");
        } else {
            String mensaje = "La tienda está cerrada, vuelva pronto.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeError.jsp");
        }

//        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
//        if (usuario != null && (usuario.getTipo().equals("2"))) {
//            String mueble = request.getParameter("mueble");
//            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
//            System.out.println(buscado.toString());
//            request.getSession().setAttribute("mueble", buscado);
//            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/compra.jsp");
//        } else {
//            String mensaje = "La tienda está cerrada, vuelva pronto.";
//            request.getSession().setAttribute("mensaje", mensaje);
//            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeError.jsp");
//        }
    }

    private void comprarMueble(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String mueble = request.getParameter("mueble");
            String nit = request.getParameter("nit");
            String cajero = request.getParameter("cajero");
            System.out.println(cajero);
            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
            //System.out.println(buscado.toString());
            Cliente cliente = null;
            cliente = this.clienteDB.getClientPorNit(nit);

            //buscar usuario por nombre
            Usuario usuario = this.usuarioDB.buscarUsuarioPorNombre(cajero);
            if (cliente == null) {
                request.getSession().setAttribute("cajero", cajero);
                request.getSession().setAttribute("nit", nit);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/solicitarDatos.jsp");
            } else {
                if (buscado.getCantidadExistente() > 0) {
                    //actualizar cantidad de muebles
                    buscado.quitarExistentes(1);
                    this.muebleDB.actualizarCantidadMuebles(buscado.getCantidadExistente(), buscado.getNombre());
                    //registrar venta
                    Venta venta = new Venta(fechaAcutal(), buscado.getPrecio(), true, buscado.getNombre(), nit);
                    venta.setUsuario(usuario.getNombre());
                    System.out.println(usuario.toString());
                    this.ventaDB.insertarVenta(venta);
                    ///informacion
                    ArrayList<Venta> ventas = (ArrayList<Venta>) this.ventaDB.getVentas();
                    request.getSession().setAttribute("mueble", buscado);
                    request.getSession().setAttribute("cliente", cliente);
                    request.getSession().setAttribute("compra", venta);
                    request.getSession().setAttribute("numero", ventas.get(ventas.size() - 1).getId());
                    enviarMensaje("Compra exitosa!!!", request, response);
                } else {
                    String mensaje = "Se han agotado las existencias!!!";
                    request.getSession().setAttribute("mensaje", mensaje);
                    response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeError.jsp");
                }
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void registrarClienteYcompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nit = (String) request.getSession().getAttribute("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");

        String cajero = request.getParameter("cajero");

        Mueble buscado = (Mueble) request.getSession().getAttribute("mueble");
        Cliente cliente = new Cliente(nit, nombre, direccion);
        this.clienteDB.insertarCliente(cliente);

        try {
            //se busca al cajero
            Usuario usuario = this.usuarioDB.buscarUsuarioPorNombre(cajero);
            if (buscado.getCantidadExistente() > 0) {
                //actualizar cantidad de muebles
                buscado.quitarExistentes(1);
                this.muebleDB.actualizarCantidadMuebles(buscado.getCantidadExistente(), buscado.getNombre());
                //registrar venta
                Venta venta = new Venta(fechaAcutal(), buscado.getPrecio(), false, buscado.getNombre(), nit);
                venta.setUsuario(usuario.getNombre());
                this.ventaDB.insertarVenta(venta);
                ///informacion
                ArrayList<Venta> ventas = (ArrayList<Venta>) this.ventaDB.getVentas();
                request.getSession().setAttribute("mueble", buscado);
                request.getSession().setAttribute("cliente", cliente);
                request.getSession().setAttribute("compra", venta);
                request.getSession().setAttribute("numero", ventas.get(ventas.size() - 1).getId());
                enviarMensaje("Compra exitosa!!!", request, response);
            } else {
                String mensaje = "Se han agotado las existencias!!!";
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeError.jsp");
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Ocurrio un error");
        }
    }

    /**
     * Se le envía un mensaje al cliente
     *
     * @param mensaje
     * @param request
     * @param response
     * @throws IOException
     */
    private void enviarMensaje(String mensaje, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensaje.jsp");
    }

    private String fechaAcutal() {
        return (String.valueOf(LocalDate.now()));
    }
}
