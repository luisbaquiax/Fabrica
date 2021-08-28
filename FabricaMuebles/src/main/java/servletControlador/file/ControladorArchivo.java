/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author luis
 */
@WebServlet("/cargaDatos")
@MultipartConfig
public class ControladorArchivo extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part partes = request.getPart("archivo");
        InputStream inputStream = partes.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        String linea = "";
        String todaInformacion = "";
        while (buffer.ready()) {
            linea = buffer.readLine();
            String aux = "";
            char[] cadena = linea.toCharArray();
            for (int i = 0; i < cadena.length; i++) {
                if (cadena[i] == '(' || cadena[i] == ')') {
                    cadena[i] = ',';
                }
                aux += cadena[i];
            }
            todaInformacion += aux + "\n";
        }
        System.out.println(todaInformacion);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/cargaDatos.jsp");
    }

}
