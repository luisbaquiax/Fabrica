/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author luis
 */
public class ManejoArchivo {

    public ManejoArchivo() {
    }

    public String informacionEntrada(BufferedReader bufferedReader) throws IOException {

        String linea = "";
        String todaInformacion = "";
        while (bufferedReader.ready()) {
            linea = bufferedReader.readLine();
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
        return todaInformacion;

    }

    public String informacionEntrada() {
        FileReader leerArchivo = null;
        JFileChooser choser = new JFileChooser();

//        choser.addChoosableFileFilter(new FileNameExtensionFilter("documento Luis", "txt"));
//        choser.setAcceptAllFileFilterUsed(false);
        int selecionado = choser.showOpenDialog(choser);
        if (selecionado == JFileChooser.APPROVE_OPTION) {
            try {

                File archivo = choser.getSelectedFile();
                leerArchivo = new FileReader(archivo.getAbsoluteFile());
                BufferedReader leyendo = new BufferedReader(leerArchivo);//para leer linea por linea
                String linea = "";
                String todaInformacion = "";
                while (leyendo.ready()) {
                    linea = leyendo.readLine();
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
                return todaInformacion;

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManejoArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManejoArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
