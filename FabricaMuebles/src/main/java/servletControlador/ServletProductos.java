/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.MuebleDB;
import db.modelo.ProductoDB;
import entidad.Mueble;
import entidad.Producto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis
 */
@WebServlet("/ControladorProductos")
public class ServletProductos extends HttpServlet {

    private ProductoDB productoDB;

    public ServletProductos() {
        this.productoDB = new ProductoDB();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "mostrar":
                    mostarProductos(request, response);
                    break;
                default:
                    mostarProductos(request, response);
            }
        } else {
            mostarProductos(request, response);
        }

    }

    private void mostarProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Producto> muebles = (ArrayList<Producto>) this.productoDB.getProducts();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("muebles", muebles);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/Productos.jsp");
    }

}
