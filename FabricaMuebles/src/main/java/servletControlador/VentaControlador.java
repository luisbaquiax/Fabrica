/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.ClienteDB;
import db.modelo.DetalleVentaDB;
import db.modelo.MuebleDB;
import db.modelo.ProductoDB;
import db.modelo.UsuarioDB;
import db.modelo.VentaDB;
import entidad.Cliente;
import entidad.DetalleVenta;
import entidad.Mueble;
import entidad.Producto;
import entidad.Usuario;
import entidad.Venta;
import entidad.manejoErrores.FabricaExcepcion;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
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
@WebServlet("/ControladorVenta")
public class VentaControlador extends HttpServlet {

    private MuebleDB muebleDB;
    private ClienteDB clienteDB;
    private VentaDB ventaDB;
    private UsuarioDB usuarioDB;
    private ProductoDB productoDB;
    private DetalleVentaDB detalleVentaDB;

    public VentaControlador() {
        this.muebleDB = new MuebleDB();
        this.clienteDB = new ClienteDB();
        this.ventaDB = new VentaDB();
        this.usuarioDB = new UsuarioDB();
        this.productoDB = new ProductoDB();
        this.detalleVentaDB = new DetalleVentaDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "buscarCliente":
                    buscarClienteRealizarCompra(request, response);
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void solicitarComra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LinkedList<Usuario> cajeros = this.usuarioDB.getUsurariosAreaDeVenta();

        if (cajeros.size() > 0) {
            int idEnsamblaje = Integer.parseInt(request.getParameter("idEnsamblaje"));
            String mueble = request.getParameter("mueble");
            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
            System.out.println(buscado.toString());

//            request.setAttribute("idEnsamblaje", idEnsamblaje);
//            request.setAttribute("cajeros", cajeros);
//            request.setAttribute("mueble", buscado);
//            request.getRequestDispatcher("/FabricaMuebles/JSP/Vendedor/compra.jsp").forward(request, response);
            request.getSession().setAttribute("idEnsamblaje", idEnsamblaje);
            request.getSession().setAttribute("cajeros", cajeros);
            request.getSession().setAttribute("mueble", buscado);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/compra.jsp");
        } else {
            String mensaje = "La tienda está cerrada, vuelva pronto.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeError.jsp");
        }

    }

    private void buscarClienteRealizarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            int idEnsamblaje = Integer.parseInt(request.getParameter("idEnsamblaje"));
            String mueble = request.getParameter("mueble");
            String nit = request.getParameter("nit");
            String cajero = request.getParameter("cajero");
            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
            Cliente cliente = null;
            cliente = this.clienteDB.getClientPorNit(nit);
            //
            Usuario vendedor = this.usuarioDB.buscarUsuarioPorNombre(cajero);
            //Validamos en caso de haya un cajero para realizar la compra
            if (cliente == null) {

//                request.setAttribute("idEnsamblaje", idEnsamblaje);
//                request.setAttribute("mueble", mueble);
//                request.setAttribute("cajero", cajero);
//                request.setAttribute("nit", nit);
//                request.getRequestDispatcher("/FabricaMuebles/JSP/Vendedor/solicitarDatos.jsp").forward(request, response);
                request.getSession().setAttribute("idEnsamblaje", idEnsamblaje);
                request.getSession().setAttribute("mueble", mueble);
                request.getSession().setAttribute("cajero", cajero);
                request.getSession().setAttribute("nit", nit);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/solicitarDatos.jsp");
            } else {
                try {
                    //actualizar estado del producto
                    Producto temp = new Producto(idEnsamblaje, true);
                    this.productoDB.actualizarProducto(temp);
                    //registrar venta
                    Venta venta = new Venta(fechaAcutal(), buscado.getPrecio(), nit, vendedor.getNombre());
                    this.ventaDB.insertarVenta(venta);
                    //agregar detalle de venta
                    List<Venta> getVentas = this.ventaDB.getVentas();
                    Venta ventaAux = this.ventaDB.getVentasByID(getVentas.get(getVentas.size() - 1).getId());
                    DetalleVenta detalleVEnta = new DetalleVenta(ventaAux.getId(), idEnsamblaje);
                    this.detalleVentaDB.insertDetalleVenta(detalleVEnta);
                    //informacion
                    ArrayList<Venta> ventas = (ArrayList<Venta>) this.ventaDB.getVentas();

                    request.getSession().setAttribute("mueble", buscado);
                    request.getSession().setAttribute("cliente", cliente);
                    request.getSession().setAttribute("compra", venta);
                    request.getSession().setAttribute("numero", ventas.get(ventas.size() - 1).getId());
                    enviarMensajeFactura("Compra exitosa!!!", request, response);
                } catch (SQLException ex) {
                    System.out.println("valio madres");
                    Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FabricaExcepcion e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            System.out.println("no se pudo hacer la compra");
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void registrarClienteYcompra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            //hidenn
            int idEnsamblaje = Integer.parseInt(request.getParameter("idEnsamblaje"));
            String mueble = request.getParameter("mueble");
            String cajero = request.getParameter("cajero");
            //formulario
            String nit = (String) request.getSession().getAttribute("nit");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");

            Mueble buscado = this.muebleDB.getMueblePorNombre(mueble);
            Cliente cliente = new Cliente(nit, nombre, direccion);
            this.clienteDB.insertarCliente(cliente);

            //
            Usuario vendedor = this.usuarioDB.buscarUsuarioPorNombre(cajero);

            try {
                realizarCompraPorUnidad(request, response, buscado, cajero, cliente, nit, idEnsamblaje, vendedor);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | FabricaExcepcion ex) {
                Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     *
     * @param request
     * @param response
     * @param buscado
     * @param cajero
     * @param cliente
     * @param nit
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IOException
     * @throws IllegalAccessException
     */
    private void realizarCompraPorUnidad(HttpServletRequest request, HttpServletResponse response,
            Mueble buscado,
            String cajero,
            Cliente cliente,
            String nit,
            int idEnsamblaje,
            Usuario vendedor
    ) throws SQLException, ClassNotFoundException, InstantiationException, IOException, IllegalAccessException, FabricaExcepcion, ServletException {
        //actualizar estado del producto
        Producto temp = new Producto(idEnsamblaje, true);
        this.productoDB.actualizarProducto(temp);
        //registrar venta
        Venta venta = new Venta(fechaAcutal(), buscado.getPrecio(), nit, vendedor.getNombre());
        this.ventaDB.insertarVenta(venta);
        //agregar detalle de venta
        List<Venta> getVentas = this.ventaDB.getVentas();
        Venta ventaAux = this.ventaDB.getVentasByID(getVentas.get(getVentas.size() - 1).getId());
        DetalleVenta detalleVEnta = new DetalleVenta(ventaAux.getId(), idEnsamblaje);
        this.detalleVentaDB.insertDetalleVenta(detalleVEnta);
        //informacion
        ArrayList<Venta> ventas = (ArrayList<Venta>) this.ventaDB.getVentas();

        request.getSession().setAttribute("mueble", buscado);
        request.getSession().setAttribute("cliente", cliente);
        request.getSession().setAttribute("compra", venta);
        request.getSession().setAttribute("numero", ventas.get(ventas.size() - 1).getId());
        enviarMensajeFactura("Compra exitosa!!!", request, response);
    }

    /**
     * Se le envía un mensaje al cliente
     *
     * @param mensaje
     * @param request
     * @param response
     * @throws IOException
     */
    private void enviarMensajeFactura(String mensaje, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensaje.jsp");
    }

    private String fechaAcutal() {
        return (String.valueOf(LocalDate.now()));
    }
}
