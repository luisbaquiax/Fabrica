/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.PrecioPiezaDB;
import db.modelo.RequerimientoEnsamblajeDB;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public FabricaControlador() {
        this.piezaDB = new PiezaDB();
        this.muebleDB = new MuebleDB();
        this.requerimientoEnsamblajeDB = new RequerimientoEnsamblajeDB();
        this.ensamblajeDB = new EnsamblajeDB();
        this.precioPiezaDB = new PrecioPiezaDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("usuario") == null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
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
                }
            } else {
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("usuario") == null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
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
                    default:

                }
            } else {
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
            }
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void verPiezas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.precioPiezaDB.getPreciosPiezas();
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
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.piezaDB.getPiezasAscedentemente();
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
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.piezaDB.getPiezasDescedentemente();
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
    private void agregarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        double costo = Double.parseDouble(request.getParameter("costo"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        this.piezaDB.insertarPieza(new Pieza(tipo, costo, cantidad));
        verPiezas(request, response);
    }

    /**
     * Para editar
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        Pieza editado = this.piezaDB.getPiezaPorTipo(tipo);
        request.getSession().setAttribute("pieza", editado);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/EditarPieza.jsp");
    }

    /**
     * Actualizar una pieza
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void actualizarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        double costo = Double.parseDouble(request.getParameter("costo"));
        int cantidadAgregada = Integer.parseInt(request.getParameter("cantidad"));
        int cantidadQuitada = Integer.parseInt(request.getParameter("cantidad2"));
        Pieza editado = this.piezaDB.getPiezaPorTipo(tipo);

        if (cantidadQuitada > editado.getCantidadExistente()) {
            response.sendRedirect("/FabricaMuebles/JSP/Administrador/Inf.jsp");
        } else {
            editado.setCosto(costo);
            editado.setCantidadExistente(cantidadAgregada);
            editado.quitarCantidad(cantidadQuitada);
            this.piezaDB.editarPieza(editado);

            verPiezas(request, response);
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
        String tipo = request.getParameter("tipo");
        this.piezaDB.eliminarPieza(tipo);
        verPiezas(request, response);
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
        double costoEnsambleje = 0;

        //mensaje en caso de un error
        String mensaje = "";

        boolean salir = false;
        for (RequerimientoEnsamblaje requerimiento : requerimientos) {
            Pieza temp = this.piezaDB.getPiezaPorTipo(requerimiento.getPieza());

            if (temp.getCantidadExistente() >= requerimiento.getCantidadPiezas()) {
                costoEnsambleje += requerimiento.getCantidadPiezas() * temp.getCosto();
                temp.quitarCantidad(requerimiento.getCantidadPiezas());
                this.piezaDB.editarPieza(temp);
            } else {
                salir = true;
            }
        }
        System.out.println("costo: " + costoEnsambleje);
        System.out.println(usu.toString());
        if (requerimientos.isEmpty()) {
            salir = true;
        }
        if ((salir == true)) {
            mensaje = " No se pudo realizar el ensamblaje poque se han agotado las piezas o no existe los requerimientos necesarios.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
            //response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
        } else {
            Mueble agregado = this.muebleDB.getMueblePorNombre(mueble);
            try {
                this.ensamblajeDB.insertarEnsamblaje(new Ensamblaje(fechaAcutal(), costoEnsambleje, false, agregado.getNombre(), usu.getNombre()));
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                mensaje = "Ha ocurrido un error. Comuníquese con el administrador de la base de datos.";
                request.getSession().setAttribute("mensaje", mensaje);
                response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
                //response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
            }
            response.sendRedirect(request.getContextPath() + "/FabricaControlador?tarea=ensamblar");
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
            Mueble muebleBuscado = this.muebleDB.getMueblePorNombre(busEnsamblaje.getMueble());
            muebleBuscado.setCantidadExistente(1);
            this.muebleDB.actualizarCantidadMuebles(muebleBuscado.getCantidadExistente(), muebleBuscado.getNombre());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            enviarMensajeDeError("NO SE PUDO ACTUALIZAR EL REGISTRO, LO SENTIMOS. COMUNÍQUESE CON EL ADMINISTRADOS DE BASE DE DATOS", request, response);
            response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
        }
        response.sendRedirect(request.getContextPath() + "/FabricaControlador?tarea=registrarMuebles");
    }

    private void mostrarEnsamblajes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajes();
        request.getSession().setAttribute("ensamblajes", ensamblajes);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
    }

    private void mostrarEnsamblajesFechaASC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorFechaASC();
        request.getSession().setAttribute("ensamblajes", ensamblajes);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
    }

    private void mostrarEnsamblajesFechaDESC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Ensamblaje> ensamblajes = (ArrayList<Ensamblaje>) this.ensamblajeDB.getEnsamblajesPorFechaDESC();
        request.getSession().setAttribute("ensamblajes", ensamblajes);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mostrarEnsamblajes.jsp");
    }

    private void enviarMensajeDeError(String mensaje, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/mensaje.jsp");
    }

    private String fechaAcutal() {
        return (String.valueOf(LocalDate.now()));
    }
}
