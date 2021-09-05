/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.UsuarioDB;
import entidad.Usuario;
import java.io.IOException;
import java.util.LinkedList;
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

    public ControladorAministrador() {
        this.usuarioDB = new UsuarioDB();
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

                        break;
                    default:
                }
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

    private void craerMuebleListarPiezas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
