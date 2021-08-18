/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletControlador;

import db.modelo.PiezaDB;
import entidad.Pieza;
import java.io.IOException;
import java.util.ArrayList;
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

    public FabricaControlador() {
        this.piezaDB = new PiezaDB();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
                default:
            }
        } else {
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void verPiezas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.piezaDB.getPiezas();
        request.getSession().setAttribute("piezas", piezas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    private void verPiezasAscendentemente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.piezaDB.getPiezasAscedentemente();
        request.getSession().setAttribute("piezas", piezas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    private void verPiezasDescendentemente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Pieza> piezas = (ArrayList<Pieza>) this.piezaDB.getPiezasDescedentemente();
        request.getSession().setAttribute("piezas", piezas);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp");
    }

    private void agregarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        double costo = Double.parseDouble(request.getParameter("costo"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        this.piezaDB.insertarPieza(new Pieza(tipo, costo, cantidad));
        verPiezas(request, response);
    }

    private void editarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        Pieza editado = this.piezaDB.getPiezaPorTipo(tipo);
        request.getSession().setAttribute("pieza", editado);
        response.sendRedirect("/FabricaMuebles/JSP/Fabrica/EditarPieza.jsp");
    }

    private void actualizarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        double costo = Double.parseDouble(request.getParameter("costo"));
        int cantidadAgregada = Integer.parseInt(request.getParameter("cantidad"));
        int cantidadQuitada = Integer.parseInt(request.getParameter("cantidad2"));
        Pieza editado = this.piezaDB.getPiezaPorTipo(tipo);
        if (cantidadQuitada > editado.getCantidadExistente()) {
            response.sendRedirect("/FabricaMuebles/JSP/Administrador/Inf.jsp");
        } else {
            editado.setCantidadExistente(cantidadAgregada);
            editado.quitarCantidad(cantidadQuitada);
            this.piezaDB.editarPieza(editado);

            verPiezas(request, response);
        }
    }

    private void eliminarPieza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        this.piezaDB.eliminarPieza(tipo);
        verPiezas(request, response);
    }
}
