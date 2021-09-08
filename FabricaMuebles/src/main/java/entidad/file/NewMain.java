/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file;

import entidad.file.escribirReportes.EscribirArchivoCSV;
import entidad.file.escribirReportes.Reporte;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EscribirArchivoCSV es = new EscribirArchivoCSV();

        es.escribirArchivodeTexto("Reportes/hola luis.CSV", "hola, soy,adfafa yo");
    }

}
