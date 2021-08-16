/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file;

import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.RequerimientoEnsamblajeDB;
import db.modelo.UsuarioDB;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class CargaDatos {

    private ManejoArchivo file;
    private ArrayList<Pieza> piezas;
    private ArrayList<Mueble> muebles;
    private ArrayList<Ensamblaje> ensamblajes;
    private ArrayList<RequerimientoEnsamblaje> requerimientoEnsamblajes;
    private ArrayList<Usuario> usuarios;
    //errores
    private ArrayList<String> errores;
    //db
    private EnsamblajeDB ensamblajeDB;
    private MuebleDB muebleDB;
    private PiezaDB piezaDB;
    private RequerimientoEnsamblajeDB requerimientoEnsamblajeDB;
    private UsuarioDB usuarioDB;

    //auxiliar requerimiento
    public CargaDatos() {
        this.file = new ManejoArchivo();
        this.piezas = new ArrayList<>();
        this.muebles = new ArrayList<>();
        this.ensamblajes = new ArrayList<>();
        this.requerimientoEnsamblajes = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.errores = new ArrayList<>();

        this.ensamblajeDB = new EnsamblajeDB();
        this.muebleDB = new MuebleDB();
        this.piezaDB = new PiezaDB();
        this.requerimientoEnsamblajeDB = new RequerimientoEnsamblajeDB();
        this.usuarioDB = new UsuarioDB();

        leerInformacion(this.file.informacionEntrada());
    }

    private void leerInformacion(String informacion) {
        String[] lineas = informacion.split("\n");
        for (String linea : lineas) {
            String[] pedazo = linea.split(",");
            try {
                if (pedazo[0].equalsIgnoreCase("USUARIO")) {
                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(pedazo[2].substring(1, pedazo[2].length() - 1));
                    System.out.println(Integer.parseInt(quitarEspacios(pedazo[3])));

                    this.usuarios.add(new Usuario(pedazo[1].substring(1, pedazo[1].length() - 1), pedazo[2].substring(1, pedazo[2].length() - 1), quitarEspacios(pedazo[3])));
                    System.out.println("");
                } else if (pedazo[0].equalsIgnoreCase("PIEZA")) {
                    Pieza pieza = new Pieza(pedazo[1].substring(1, pedazo[1].length() - 1), Double.parseDouble(quitarEspacios(pedazo[2])));

                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(Double.parseDouble(quitarEspacios(pedazo[2])));

                    if (!existePieza(pedazo[1].substring(1, pedazo[1].length() - 1))) {
                        pieza.setCantidadExistente(1);
                        this.piezas.add(pieza);
                    } else {
                        modificarCantidadPiezas(pieza.getTipo());
                    }

                    System.out.println("");
                } else if (pedazo[0].equalsIgnoreCase("MUEBLE")) {
                    Mueble mueble = new Mueble(pedazo[1].substring(1, pedazo[1].length() - 1), Double.parseDouble(quitarEspacios(pedazo[2])));

                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(Double.parseDouble(quitarEspacios(pedazo[2])));
                    System.out.println("");

                    if (!existeMueble(pedazo[1].substring(1, pedazo[1].length() - 1))) {
                        mueble.setCantidadExistente(1);
                        this.muebles.add(mueble);
                    } else {
                        modificarCantidadMuebles(mueble.getNombre());
                    }

                } else if (pedazo[0].equalsIgnoreCase("ENSAMBLE_PIEZAS")) {
                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(pedazo[2].substring(1, pedazo[2].length() - 1));
                    System.out.println(Integer.parseInt(quitarEspacios(pedazo[3])));
                    System.out.println("");

                    if (existePieza(pedazo[2].substring(1, pedazo[2].length() - 1)) && existeMueble(pedazo[1].substring(1, pedazo[1].length() - 1))) {
                        this.requerimientoEnsamblajes.add(new RequerimientoEnsamblaje(
                                pedazo[2].substring(1, pedazo[2].length() - 1),
                                pedazo[1].substring(1, pedazo[1].length() - 1),
                                Integer.parseInt(quitarEspacios(pedazo[3]))));
                    }

                } else if (pedazo[0].equalsIgnoreCase("ENSAMBLAR_MUEBLE")) {

                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(quitarEspacios(pedazo[2]));
                    System.out.println(pedazo[3].substring(1, pedazo[3].length() - 1));
                    System.out.println("");

                    if (existeMueble(pedazo[1].substring(1, pedazo[1].length() - 1)) && existeUsuario(quitarEspacios(pedazo[2]))) {
                        
                        this.ensamblajes.add(new Ensamblaje(
                                pedazo[3].substring(1, pedazo[3].length() - 1),
                                pedazo[1].substring(1, pedazo[1].length() - 1),
                                quitarEspacios(pedazo[2])));
                    }

                } else {
                    System.out.println("error en " + linea);
                    this.errores.add("Error en: (" + linea + ")" + " línea ");
                }
            } catch (Exception e) {
                System.out.println("error ");
                this.errores.add("Error en: (" + linea + ")" + " línea ");
            }

        }

        for (int i = 0; i < this.ensamblajes.size(); i++) {
            this.ensamblajes.get(i).setCosto(calcularCostoEnsamblaje(this.ensamblajes.get(i).getMueble()));
            this.ensamblajes.get(i).setEstado("0");
        }
        imprimirDatosParaVerificar();
    }

    private double calcularCostoEnsamblaje(String muebleNombre) {
        ArrayList<RequerimientoEnsamblaje> auxRequerimientos = new ArrayList<>();

        for (int i = 0; i < this.requerimientoEnsamblajes.size(); i++) {
            if (this.requerimientoEnsamblajes.get(i).getMueble().equals(muebleNombre)) {
                auxRequerimientos.add(this.requerimientoEnsamblajes.get(i));
            }
        }
        double costo = 0;
        for (int i = 0; i < auxRequerimientos.size(); i++) {
            Pieza aux = recuperarPieza(auxRequerimientos.get(i).getPieza());
            if (aux != null) {
                costo += aux.getCosto() * auxRequerimientos.get(i).getCantidadPiezas();
            }
        }
        return costo;
    }

    private Pieza recuperarPieza(String tipo) {
        for (int i = 0; i < this.piezas.size(); i++) {
            if (this.piezas.get(i).getTipo().equals(tipo)) {
                return this.piezas.get(i);
            }
        }
        return null;
    }

    private void imprimirDatosParaVerificar() {
        System.out.println("PIEZAS");
        for (int i = 0; i < this.piezas.size(); i++) {
            System.out.println(piezas.get(i).getTipo());
            System.out.println(piezas.get(i).getCantidadExistente());
            System.out.println("");
        }
        System.out.println("muebles");
        for (int i = 0; i < this.muebles.size(); i++) {
            System.out.println(muebles.get(i).getNombre());
            System.out.println(muebles.get(i).getPrecio());
            System.out.println(muebles.get(i).getCantidadExistente());
            System.out.println("");
        }
        System.out.println("ENSAMBLE_PIEZAS");
        for (int i = 0; i < this.requerimientoEnsamblajes.size(); i++) {
            System.out.println(requerimientoEnsamblajes.get(i).getCantidadPiezas());
            System.out.println(requerimientoEnsamblajes.get(i).getMueble());
            System.out.println(requerimientoEnsamblajes.get(i).getPieza());
            System.out.println("");
        }
        System.out.println("ENSAMBLAR_MUEBLE");
        for (int i = 0; i < this.ensamblajes.size(); i++) {
            System.out.println(ensamblajes.get(i).getMueble());
            System.out.println(ensamblajes.get(i).getUsuario());
            System.out.println(ensamblajes.get(i).getFecha());
            System.out.println(ensamblajes.get(i).getCosto());
            System.out.println(ensamblajes.get(i).getEstado());
        }
    }

    private boolean existeUsuario(String usuario) {
        for (Usuario usu : usuarios) {
            if (usu.getNombre().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    private boolean existePieza(String tipo) {

        for (Pieza pieza : piezas) {
            if (pieza.getTipo().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeMueble(String nombre) {

        for (Mueble mueble : muebles) {
            if (mueble.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private void modificarCantidadPiezas(String tipo) {
        for (int i = 0; i < this.piezas.size(); i++) {
            if (piezas.get(i).getTipo().equalsIgnoreCase(tipo)) {
                piezas.get(i).setCantidadExistente(1);
            }
        }
    }

    private void modificarCantidadMuebles(String nombre) {
        for (int i = 0; i < this.muebles.size(); i++) {
            if (muebles.get(i).getNombre().equalsIgnoreCase(nombre)) {
                muebles.get(i).setCantidadExistente(1);
            }
        }
    }

    private String quitarEspacios(String cadena) {
        char[] palabra = cadena.toCharArray();
        String aux = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (palabra[i] != ' ') {
                aux += palabra[i];
            }
        }
        return aux;
    }
}
