/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.ClienteDB;
import db.modelo.DetalleVentaDB;
import db.modelo.DevolucionDB;
import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.ProductoDB;
import db.modelo.UsuarioDB;
import db.modelo.VentaDB;
import entidad.Cliente;
import entidad.DetalleVenta;
import entidad.Devolucion;
import entidad.Ensamblaje;
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
    private EnsamblajeDB ensamblajeDB;
    private DevolucionDB devolucionDB;

    public VentaControlador() {
        this.muebleDB = new MuebleDB();
        this.clienteDB = new ClienteDB();
        this.ventaDB = new VentaDB();
        this.usuarioDB = new UsuarioDB();
        this.productoDB = new ProductoDB();
        this.detalleVentaDB = new DetalleVentaDB();
        this.ensamblajeDB = new EnsamblajeDB();
        this.devolucionDB = new DevolucionDB();
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
                case "devolucion":
                    verificarDevolucion(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");

            }
        } else {
            response.sendRedirect("index.jsp");
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
                case "devolver":
                    devolverProducto(request, response);
                    break;
                case "regresar":
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.sendRedirect("index.jsp");
                    break;
                default:
                    response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
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

    private void verificarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String mensaje = "";
        try {
            int id = Integer.parseInt(request.getParameter("factura"));
            Venta buscado = this.ventaDB.getVentasByID(id);
            Venta auxi = this.ventaDB.getVerficarDevolucion(buscado.getFecha(), fechaAcutal(), id);
            if (auxi != null) {
                //Mostrar los productos que se compraron se ace la devolucion,
                List<DetalleVenta> detalles = this.detalleVentaDB.getDetallePorIDVenta(auxi.getId());
                System.out.println("haciendo la devolucion");
                request.getSession().setAttribute("productosAdevolver", detalles);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mostrarProductosDevolver.jsp");

            } else {
                //no se hace la devolucion
                mensaje = "NO SE PUEDE HACER LA DEVOLUCION DEBIDO A QUE YA HA PASADO MÁS DE UNA SEMANA";
                System.out.println("no se hace la devolucion");
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeDevolucion.jsp");
            }
        } catch (FabricaExcepcion ex) {
            System.out.println("esa factura no existe");
            ex.printStackTrace();
        }

    }

    private void devolverProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //realizar devolucion
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            DetalleVenta detalle = this.detalleVentaDB.getDetallePorID(idDetalle);
            Venta buscado = this.ventaDB.getVentasByID(detalle.getIdVenta());
            Ensamblaje producto = this.ensamblajeDB.getEnsamblajesPorID(detalle.getIdProducto());
            Devolucion dev = new Devolucion(producto.getCosto() / 3, fechaAcutal(), buscado.getNitCliente(), detalle.getIdProducto());
            if (detalle != null) {
                this.devolucionDB.insertarDevolucion(dev);
                this.detalleVentaDB.deleteDetall(idDetalle);
                String mensaje = "Devolución exitosa.";
                System.out.println("no se hace la devolucion");
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeDevolucion.jsp");
            } else {
                response.sendRedirect("Inicio.jsp");
            }

        } catch (FabricaExcepcion ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
            String mensaje = "Factura no encontrada.";
            System.out.println("no se hace la devolucion");
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeDevolucion.jsp");
        } catch (SQLException ex) {
            String mensaje = "Devolución exitosa.";
            System.out.println("no se hace la devolucion");
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeDevolucion.jsp");
        } catch (NullPointerException nulo) {
            String mensaje = "No se puede hacer la devolucion.";
            System.out.println("no se hace la devolucion");
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensajeDevolucion.jsp");
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
    private void enviarMensajeFactura(String mensaje, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Vendedor/mensaje.jsp");
    }

    private String fechaAcutal() {
        return (String.valueOf(LocalDate.now()));
    }

}
