/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador.file;

import db.modelo.UsuarioDB;
import entidad.Usuario;
import entidad.file.CargaDatos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
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

    /**
     * Desde la carga de datos se lee el archivo de entrada y luego se muestra
     * al usuario-administrador
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Part partes = request.getPart("archivo");
        InputStream inputStream = partes.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        CargaDatos cargaDatos = new CargaDatos(buffer);
        cargaDatos.leerInformacion(cargaDatos.getManejadoArchivo().informacionEntrada(cargaDatos.getBufferedReader()));
        //cargaDatos.imprimirDatosParaVerificar();
        request.getSession().setAttribute("carga", cargaDatos);
        response.sendRedirect("/FabricaMuebles/JSP/Administrador/cargaDatos.jsp");

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tarea = request.getParameter("tarea");
        if (tarea != null) {
            switch (tarea) {
                case "subir":
                    CargaDatos cargaDatos = (CargaDatos) request.getSession().getAttribute("carga");
                    cargaDatos.imprimirDatosParaVerificar();
                    request.getSession().invalidate();
                    response.sendRedirect("/FabricaMuebles/index.jsp");
                    break;
                default:
                    response.sendRedirect("/FabricaMuebles/index.jsp");
            }
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            List<Usuario> usuarios = usuarioDB.getTodosUsuarios();
            if (usuarios.isEmpty() || (usuarioDB == null)) {
                response.sendRedirect("/FabricaMuebles/JSP/Administrador/cargaDatos.jsp");
            } else {
                response.sendRedirect("/FabricaMuebles/Inicio.jsp");
            }
        }

    }
}
