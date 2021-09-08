/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file.escribirReportes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author luis
 */
public class EscribirArchivoCSV {
    
    public void escribirArchivodeTexto(String ruta, String contenido) {
        try {
            File file = new File(ruta);
            // Si el archivo no existe es creado
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            System.out.println(file.getAbsolutePath());
            FileWriter escribeArchivo = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(escribeArchivo);
            bw.write(contenido);
            bw.close();
            System.out.println("hecho");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
