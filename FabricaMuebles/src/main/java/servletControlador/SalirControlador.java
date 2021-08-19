/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet("/Salir")
public class SalirControlador extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tarea = request.getParameter("tarea");
        if (tarea.equals("salir")) {
            if (request.getSession().getAttribute("usuario") != null) {
                request.getSession().removeAttribute("usuario");
                request.getSession().invalidate();
                //response.sendRedirect("index.jsp");
            }
        }
        response.sendRedirect("index.jsp");
    }
}
