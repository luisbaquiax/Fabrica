/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.RequerimientoEnsamblajeDB;
import db.modelo.UsuarioDB;
import entidad.Mueble;
import entidad.Pieza;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet("/controladorAdmin")
public class ControladorAministrador extends HttpServlet {

    private UsuarioDB usuarioDB;
    private PiezaDB piezaDB;
    private MuebleDB muebleDB;
    private RequerimientoEnsamblajeDB requerimientoEnsamblajeDB;

    public ControladorAministrador() {
        this.usuarioDB = new UsuarioDB();
        this.piezaDB = new PiezaDB();
        this.muebleDB = new MuebleDB();
        this.requerimientoEnsamblajeDB = new RequerimientoEnsamblajeDB();

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (user != null && (user.getTipo().equals(Usuario.FINANCIERO))) {
            String tarea = request.getParameter("tarea");
            if (tarea != null) {
                switch (tarea) {
                    case "agregar":
                        agregarNuevoUsuario(request, response);
                        break;
                    case "editarMueble":
                        editarMuebleNuevo(request, response);
                        break;
                    case "agregarCantidad":
                        agregarCantidad(request, response);
                        break;
                    default:
                        listarUsuarios(request, response);
                }
            } else {
                listarUsuarios(request, response);
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
                    case "usuarios":
                        listarUsuarios(request, response);
                        break;
                    case "cancelar":
                        cancelarUsuario(request, response);
                        break;
                    case "reactivar":
                        reactivarUsuario(request, response);
                        break;
                    case "crearMueble":
                        crearMuebleListarPiezas(request, response);
                        break;
                    case "editarCantidad":
                        editarCantidades(request, response);
                        break;
                    case "guardar":
                        guardarNuevoMueble(request, response);
                        break;
                    default:
                        listarUsuarios(request, response);
                }
            } else {
                listarUsuarios(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    /**
     *
     * Sirve para listar los usuarios diferentes al tipo administrador
     *
     * @param request
     * @param response
     */
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LinkedList<Usuario> usuarios = this.usuarioDB.getUsurariosVentaYFabrica();

        request.getSession().setAttribute("usuarios", usuarios);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/usuarios.jsp");
    }

    private void agregarNuevoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("name");
        String tipo = request.getParameter("tipo");
        String pass = request.getParameter("pass");
        System.out.println(tipo);
        this.usuarioDB.insertarUsuario(new Usuario(nombre, pass, tipo, false));
        String mensaje = "Usuario agregado con exito";
        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/mensaje.jsp");
    }

    private void cancelarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        this.usuarioDB.cambiarEstadoUsuario(user, true);
        listarUsuarios(request, response);
    }

    private void reactivarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        this.usuarioDB.cambiarEstadoUsuario(user, false);
        listarUsuarios(request, response);
    }

    private void crearMuebleListarPiezas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pieza> piezasDisponibles = this.piezaDB.getPiezas();
        request.getSession().setAttribute("piezas", piezasDisponibles);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/crearMueble.jsp");
    }

    private void editarMuebleNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            Mueble muebleNuevo = new Mueble(nomre, precio);
            String[] piezas = request.getParameterValues("piezas");
            List<Pieza> piezasNecesarias = new ArrayList<>();
            for (String cadena : piezas) {
                Pieza p = new Pieza(cadena);
                p.setCantidadExistente(1);
                piezasNecesarias.add(p);
            }
            request.getSession().setAttribute("muebleNuevo", muebleNuevo);
            request.getSession().setAttribute("piezasNecesarias", piezasNecesarias);
            response.sendRedirect("/FabricaMuebles/JSP/Administrador/editarMuebleNuevo.jsp");
        } catch (IOException | NumberFormatException e) {
            crearMuebleListarPiezas(request, response);
        } catch (NullPointerException ex) {
            System.out.println("debes seleccionar los las peizas requeridas");
            crearMuebleListarPiezas(request, response);
        }

    }

    private void editarCantidades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pieza = request.getParameter("pieza");
        request.getSession().setAttribute("requerido", pieza);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/agregarCantidadPieza.jsp");

    }

    private void agregarCantidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int unidades = Integer.parseInt(request.getParameter("unidades"));
        String pieza = request.getParameter("pieza");
        List<Pieza> piezasNecesarias = (List<Pieza>) request.getSession().getAttribute("piezasNecesarias");

        for (Pieza pieza1 : piezasNecesarias) {
            if (pieza1.getTipo().equals(pieza)) {
                pieza1.setCantidadExistente(unidades);
                break;
            }
        }

        request.getSession().setAttribute("piezasNecesarias", piezasNecesarias);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/editarMuebleNuevo.jsp");
    }

    private void guardarNuevoMueble(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mueble nuevo = (Mueble) request.getSession().getAttribute("muebleNuevo");
        List<Pieza> necesarias = (List<Pieza>) request.getSession().getAttribute("piezasNecesarias");
        if ((nuevo != null) && (necesarias != null)) {
            this.muebleDB.insertarMueble(nuevo);
            for (Pieza necesaria : necesarias) {
                this.requerimientoEnsamblajeDB.insertarRequierimientoEnsamblaje(
                        new RequerimientoEnsamblaje(
                                necesaria.getTipo(),
                                nuevo.getNombre(),
                                necesaria.getCantidadExistente()));
            }
            crearMuebleListarPiezas(request, response);
        } else {
            crearMuebleListarPiezas(request, response);
        }
    }
}
