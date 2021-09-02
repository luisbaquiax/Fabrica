/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.DetalleEnsamblajeDB;
import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.PrecioPiezaDB;
import db.modelo.ProductoDB;
import db.modelo.RequerimientoEnsamblajeDB;
import entidad.DetalleEnsamblaje;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import entidad.manejoErrores.FabricaExcepcion;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet("/FabricaControlador")
public class FabricaControlador extends HttpServlet {

    private PiezaDB piezaDB;
    private MuebleDB muebleDB;
    private RequerimientoEnsamblajeDB requerimientoEnsamblajeDB;
    private EnsamblajeDB ensamblajeDB;
    private PrecioPiezaDB precioPiezaDB;
    private DetalleEnsamblajeDB detalleEnsamblajeDB;
    private ProductoDB productoDB;

    public FabricaControlador() {
        this.piezaDB = new PiezaDB();
        this.muebleDB = new MuebleDB();
        this.requerimientoEnsamblajeDB = new RequerimientoEnsamblajeDB();
        this.ensamblajeDB = new EnsamblajeDB();
        this.precioPiezaDB = new PrecioPiezaDB();
        this.detalleEnsamblajeDB = new DetalleEnsamblajeDB();
        this.productoDB = new ProductoDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
        if (request.getSession().getAttribute("usuario") == null) {
            response.setHeader("Cacher-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if (usu != null && (usu.getTipo().equals(Usuario.FABRICA))) {

            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "actualizarPieza":
                        actualizarPieza(request, response);
                        break;
                    case "agregar":
                        agregarPieza(request, response);
                        break;

                    default:
                        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
                }
            } else {
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
            }

        } else {
            response.setHeader("Cacher-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
        if (request.getSession().getAttribute("usuario") == null) {
            response.setHeader("Cacher-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if (usu != null && (usu.getTipo().equals(Usuario.FABRICA))) {
            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "ver":
                        verPiezas(request, response);
                        break;
                    case "ascendente":
                        verPiezasAscendentemente(request, response);
                        break;
                    case "descendente":
                        verPiezasDescendentemente(request, response);
                        break;
                    case "editar":
                        editarPieza(request, response);
                        break;
                    case "eliminarPieza":
                        eliminarPieza(request, response);
                        break;
                    case "ensamblar":
                        listadoMueblesAEnasamblar(request, response);
                        break;
                    case "ensamblaje":
                        listarRequerimientoParaEnsamblaje(request, response);
                        break;
                    case "cancelarEnsamble":
                        listadoMueblesAEnasamblar(request, response);
                        break;
                    case "agregarEnsamble":
                        agregarEnsamble(request, response);
                        break;
                    case "registrarMuebles":
                        listarMueblesEnsambladosNoRegistrados(request, response);
                        break;
                    case "registrarMueble":
                        registrarMuebleEnTienda(request, response);
                        break;
                    case "mostrarEnsamblados":
                        mostrarEnsamblajes(request, response);
                        break;
                    case "fechaAscendente":
                        mostrarEnsamblajesFechaASC(request, response);
                        break;
                    case "fechaDescendente":
                        mostrarEnsamblajesFechaDESC(request, response);
                        break;
                    case "verDetalle":
                        verDetalleEnsamblaje(request, response);
                        break;
                    default:
                        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
                }
            } else {
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
            }
        } else {
            response.setHeader("Cacher-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void verPiezas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.precioPiezaDB.getPreciosPiezas(false);
        ArrayList<Pieza> agotadas = (ArrayList<Pieza>) this.precioPiezaDB.getPiezasAgotadas();
        request.getSession().setAttribute("piezas", piezas);
        request.getSession().setAttribute("agotadas", agotadas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    /**
     * *
     * Listar piezas Ascendentemente
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void verPiezasAscendentemente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.precioPiezaDB.getPiezasAscedentemente();
        request.getSession().setAttribute("piezas", piezas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    /**
     * Listar piezas Descendentemente
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void verPiezasDescendentemente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.precioPiezaDB.getPiezasDescedentemente();
        request.getSession().setAttribute("piezas", piezas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    /**
     * Crear una pieza
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void agregarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String tipo = request.getParameter("tipo");
            double costo = Double.parseDouble(request.getParameter("costo"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Pieza pieza = new Pieza(tipo);
            Pieza aux = this.piezaDB.getPiezaPorTipo(tipo);
            if (aux != null) {
                String mensaje = "La pieza ya existe con ese nombre.";
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
            } else {
                this.piezaDB.insertarPieza(pieza);
                this.precioPiezaDB.insertarPrecioPieza(new Pieza(tipo, costo, cantidad, false));
                String mensaje = "";
                request.getSession().setAttribute("mensaje", mensaje);
                verPiezas(request, response);
            }
        } catch (NumberFormatException e) {
            String mensaje = "Se esperaba un número.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
        } catch (SQLException ex) {
            verPiezas(request, response);
        }

    }

    /**
     * Para editar
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String tipo = request.getParameter("tipo");

            double precio = Double.parseDouble(request.getParameter("precio"));

            Pieza editado = this.precioPiezaDB.getPiezaPorPrecioYNombre(tipo, precio);
            request.getSession().setAttribute("pieza", editado);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/EditarPieza.jsp");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NumberFormatException ex) {
            System.out.println("Error del servidor");
        } catch (FabricaExcepcion ex) {
            System.out.println(ex.getMessage());
            request.getSession().setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        }

    }

    /**
     * Actualizar una pieza
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void actualizarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String mensaje = "";

        try {
            String tipo = request.getParameter("tipo");
            double precioOriginal = Double.parseDouble(request.getParameter("precio"));

            //datos del formulario
            String nombreNuevo = request.getParameter("nombreNuevo");
            double costoNuevo = Double.parseDouble(request.getParameter("costo"));
            int cantidadAgregada = Integer.parseInt(request.getParameter("cantidad"));
            int cantidadQuitada = Integer.parseInt(request.getParameter("cantidad2"));

            //buscamos la pieza-precio
            Pieza editado = this.precioPiezaDB.getPiezaPorPrecioYNombre(tipo, precioOriginal);
            //buscamos la pieza en la tabla 'pieza'
            Pieza pieza = this.piezaDB.getPiezaPorTipo(tipo);

            if (cantidadQuitada > editado.getCantidadExistente()) {
                mensaje = "No puedes quitar más de lo necesario";
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
            } else {

                //actualizacion de pieza en particular
                editado.setCosto(costoNuevo);
                editado.setCantidadExistente(cantidadAgregada);
                editado.quitarCantidad(cantidadQuitada);
                this.precioPiezaDB.actualizarPrecioPieza(editado, tipo, precioOriginal);
                //se actuliza la pieza en la tabla 'pieza'
                this.piezaDB.actualizarPieza(pieza, nombreNuevo);
                //se actualiza el detalle de ensamblaje
                for (DetalleEnsamblaje d : this.detalleEnsamblajeDB.getTodosDetalles()) {
                    this.detalleEnsamblajeDB.actualizarEnsablajePrecioYTipoPieza(costoNuevo, nombreNuevo, precioOriginal, tipo, d.getId());
                }
                //actualizar el costo de ensamblaje
                for (Ensamblaje e : this.ensamblajeDB.getEnsamblajes()) {
                    e.setCosto(this.detalleEnsamblajeDB.getCostoEnsamblaje(e.getId()));
                    this.ensamblajeDB.actualizaCostoDeEnsamblaje(e);
                }

                enviarMensajeEnBlanco(request);
                verPiezas(request, response);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            mensaje = "Error con la base de datos, comuníquese con el administrador de base de datos o con el desarrollador";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        } catch (SQLException ex) {
            mensaje = "No se permite modificar a un precio ya existente. Sugerencia disminuye la cantidad-existente de la pieza que quieres editar"
                    + " y agréguesela al que tiene el precio que quieres guardar.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        } catch (FabricaExcepcion ex) {
            request.getSession().setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        } catch (NumberFormatException ex) {
            System.out.println("se espera un numero");
        }
    }

    /**
     * Eliminar una pieza
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void eliminarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String tipo = request.getParameter("tipo");
            double precio = Double.parseDouble(request.getParameter("precio"));
            Pieza obsoleta = this.precioPiezaDB.getPiezaPorPrecioYNombre(tipo, precio);
            //se le cambia el estado de la pieza quedando obsoleta === eliminado
            obsoleta.setEstado(true);
            this.precioPiezaDB.actualizarPrecioPieza(obsoleta, tipo, precio);
            enviarMensajeEnBlanco(request);
            verPiezas(request, response);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | FabricaExcepcion ex) {
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void listadoMueblesAEnasamblar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Mueble> muebles = (ArrayList<Mueble>) this.muebleDB.listarProductos();
        request.getSession().setAttribute("mubles", muebles);

        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ensamblarMueble.jsp");
    }

    /**
     * Envía un lista de los requerimientos de un ensamblaje
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void listarRequerimientoParaEnsamblaje(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mueble = request.getParameter("mueble");
        ArrayList<RequerimientoEnsamblaje> requerimientos = (ArrayList<RequerimientoEnsamblaje>) this.requerimientoEnsamblajeDB.listarRequerimientos(mueble);
        request.getSession().setAttribute("mueble", mueble);
        request.getSession().setAttribute("requerimientos", requerimientos);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/nuevoEnsamblaje.jsp");
    }

    private void agregarEnsamble(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String mueble = request.getParameter("mueble");
        Usuario usu = (Usuario) request.getSession().getAttribute("usuario");

        ArrayList<RequerimientoEnsamblaje> requerimientos = (ArrayList<RequerimientoEnsamblaje>) this.requerimientoEnsamblajeDB.listarRequerimientos(mueble);

        //mensaje en caso de un error
        String mensaje = "";
        //se guarda los requeridos en una lista auxiliar
        boolean salir = false;
        boolean hayRequerimiento = true;
        boolean errorDelServidor = false;
        Pieza auxPieza = null;
       
        //verificamos si existe piezas todavía del tipo que requiere el ensamblaje
        try {
            for (RequerimientoEnsamblaje requerimiento : requerimientos) {
                int existentes = 0;
                auxPieza = new Pieza(requerimiento.getPieza());
                existentes = this.precioPiezaDB.getCantidadExistentePorTipoCantidadYEstado(requerimiento.getPieza());
                if (existentes < requerimiento.getCantidadPiezas()) {
                    hayRequerimiento = false;
                    break;
                }
            }
        } catch (FabricaExcepcion e) {
            mensaje = e.getMessage();
            errorDelServidor = true;
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        }

        if ((hayRequerimiento == false)) {
            mensaje = "No hay piezas suficientes del tipo: " + auxPieza.getTipo() + ".";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
            //response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
        } else {
            //proceo de ensamblar
            double costoNuevoEnsamblaje = 0;
            //lista auxiliar de piezas para el detalle
            ArrayList<Pieza> piezaDetalle = new ArrayList<>();
            for (RequerimientoEnsamblaje re : this.requerimientoEnsamblajeDB.listarRequerimientos(mueble)) {
                for (int i = 0; i < re.getCantidadPiezas(); i++) {
                    Pieza pi = this.precioPiezaDB.getPiezaPorTipo(re.getPieza());
                    System.out.println(pi.getCosto());
                    costoNuevoEnsamblaje += pi.getCosto();
                    pi.quitarCantidad(1);
                    try {
                        this.precioPiezaDB.actualizarCantidadExistentePrecioPieza(pi);
                    } catch (SQLException ex) {
                        Logger.getLogger(FabricaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(pi.toString());
                    piezaDetalle.add(pi);
                }
            }
            Mueble muebleAgregado = this.muebleDB.getMueblePorNombre(mueble);
            System.out.println(muebleAgregado.toString());
            try {
                Ensamblaje nuevo = new Ensamblaje(fechaAcutal(), costoNuevoEnsamblaje, false, mueble, usu.getNombre());
                this.ensamblajeDB.insertarEnsamblaje(nuevo);
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(FabricaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            //agregamos el detalle del ensamblaje
            List<Ensamblaje> ensamblajes = this.ensamblajeDB.getEnsamblajes();
            //recogemos el último ensamblado que se supone que es este
            Ensamblaje ensamblajeInsertado = this.ensamblajeDB.getEnsamblajesPorID(ensamblajes.get(ensamblajes.size() - 1).getId());
            for (Pieza detallePieza : piezaDetalle) {
                try {
                    this.detalleEnsamblajeDB.insertarDetalleDelEnsamblaje(
                            new DetalleEnsamblaje(
                                    ensamblajeInsertado.getId(),
                                    detallePieza.getCosto(),
                                    detallePieza.getTipo()));
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            try {
                this.productoDB.insertarProducto(ensamblajeInsertado.getId(), false);
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {

            }
            System.out.println("ensamblado con exito");
            //en caso que todo sale bien se lista las piezas
            verPiezas(request, response);

        }

    }

    private void listarMueblesEnsambladosNoRegistrados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ArrayList<Ensamblaje> noRegistrados = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorEstadoYUsuario(false, usuario.getNombre());
        request.getSession().setAttribute("ensamblajes", noRegistrados);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mueblesNoRegistrados.jsp");
    }

    private void registrarMuebleEnTienda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("mueble"));
        Ensamblaje busEnsamblaje = this.ensamblajeDB.getEnsamblajesPorID(id);

        try {
            //cambiar el estado del ensamblaje
            busEnsamblaje.setEstado(true);
            //actulizar ensamblaje
            this.ensamblajeDB.actualizaEnsamblajePorId(busEnsamblaje);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            enviarMensajeDeError("NO SE PUDO ACTUALIZAR EL REGISTRO, LO SENTIMOS. COMUNÍQUESE CON EL ADMINISTRADOS DE BASE DE DATOS", request, response);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        }
        response.sendRedirect(request.getContextPath() + "/FabricaControlador?tarea=registrarMuebles");
    }

    private void mostrarEnsamblajes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
        if (usu != null && usu.getTipo().equals(Usuario.FABRICA)) {
            ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorUsuario(usu.getNombre());
            request.getSession().setAttribute("ensamblajes", ensamblajes);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
        } else {
            request.getRequestDispatcher(request.getContextPath() + "/index.jsp");
        }

    }

    private void mostrarEnsamblajesFechaASC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && (usuario.getTipo().equals(Usuario.FABRICA))) {
            ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorFechaASC(usuario.getNombre());
            request.getSession().setAttribute("ensamblajes", ensamblajes);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
        }

    }

    private void mostrarEnsamblajesFechaDESC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && (usuario.getTipo().equals(Usuario.FABRICA))) {
            ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorFechaDESC(usuario.getNombre());
            request.getSession().setAttribute("ensamblajes", ensamblajes);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
        } else {
            request.getRequestDispatcher(request.getContextPath() + "/index.jsp");
        }
    }
    /**
     * Muestra el detalle del ensamblaje
     */
    private void verDetalleEnsamblaje(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        List<DetalleEnsamblaje> detalle = this.detalleEnsamblajeDB.getTodosDetallesById(id);
        request.getSession().setAttribute("detalle", detalle);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/detalleEnsamblaje.jsp");
    }

    private void enviarMensajeDeError(String mensaje, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
    }

    private void enviarMensajeEnBlanco(HttpServletRequest request) {
        String mensaje = "";
        request.getSession().setAttribute("mensaje", mensaje);
    }

    private String fechaAcutal() {
        return (String.valueOf(LocalDate.now()));
    }
}
