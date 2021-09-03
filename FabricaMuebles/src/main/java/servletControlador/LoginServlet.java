/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.UsuarioDB;
import entidad.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private UsuarioDB usuarioDB;

    public LoginServlet() {
        this.usuarioDB = new UsuarioDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String nombre = request.getParameter("nombre");
            String pass = request.getParameter("pass");

            Usuario buscado = this.usuarioDB.buscarUsuario(nombre, pass);
            if (buscado != null) {
                if (buscado.getTipo().equals(Usuario.FABRICA)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", buscado);
                    response.sendRedirect("/FabricaMuebles/JSP/Fabrica/Fabricador.jsp");
                } else if (buscado.getTipo().equals(Usuario.VENTA)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", buscado);
                    response.sendRedirect("/FabricaMuebles/JSP/Vendedor/Vendedor.jsp");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", buscado);
                    response.sendRedirect("/FabricaMuebles/JSP/Administrador/Financiero.jsp");
                }
            } else {
                response.sendRedirect("/FabricaMuebles/LoginRegister.jsp");
                //request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/LoginRegister.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
