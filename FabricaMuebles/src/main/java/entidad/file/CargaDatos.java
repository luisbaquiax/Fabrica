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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //auxiliares
    private ArrayList<RequerimientoEnsamblaje> auxRequerimientosAsubir;
    private ArrayList<Ensamblaje> auxEnsamblajes;

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
        //
        this.auxRequerimientosAsubir = new ArrayList<>();
        this.auxEnsamblajes = new ArrayList<>();

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
                    System.out.println("");
                    Usuario usuario = new Usuario(
                            quitarEspacios(pedazo[1].substring(1, pedazo[1].length() - 1)),
                            quitarEspacios(pedazo[2].substring(1, pedazo[2].length() - 1)),
                            quitarEspacios(pedazo[3]));
                    this.usuarios.add(usuario);
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

                    this.requerimientoEnsamblajes.add(new RequerimientoEnsamblaje(
                            pedazo[2].substring(1, pedazo[2].length() - 1),
                            pedazo[1].substring(1, pedazo[1].length() - 1),
                            Integer.parseInt(quitarEspacios(pedazo[3]))));

                } else if (pedazo[0].equalsIgnoreCase("ENSAMBLAR_MUEBLE")) {

                    System.out.println(pedazo[1].substring(1, pedazo[1].length() - 1));
                    System.out.println(quitarEspacios(pedazo[2]));
                    System.out.println(pedazo[3].substring(1, pedazo[3].length() - 1));
                    System.out.println("");

                    String fecha = quitarEspacios(pedazo[3].substring(1, pedazo[3].length() - 1));
                    String mueble = pedazo[1].substring(1, pedazo[1].length() - 1);
                    String usuario = quitarEspacios(pedazo[2]);
                    Ensamblaje ensamblaje = new Ensamblaje(formatoFecha(fecha), mueble, usuario);
                    ensamblaje.setEstado(true);
                    System.out.println(ensamblaje.toString());
                    this.ensamblajes.add(ensamblaje);

                } else if (pedazo[0].equalsIgnoreCase("CLIENTE")) {
                    System.out.println(linea);
                } else {
                    System.out.println("error en " + linea);
                    this.errores.add("Error en: (" + linea + ")" + " línea ");
                }
            } catch (Exception e) {
                System.out.println("error en " + linea);
                this.errores.add("Error en: (" + linea + ")" + " línea ");
            }

        }
        //
        obtenerRequerimientosVerificados();
        verificarEnsamblajes();
        //agregamos el costo de ensamblaje
        agregarCostoDeEnsamblaje();
        //ingresamos mubles ensambladosf
        ingresarMueblesEnsamblados();
        //se suben todos los datos
        imprimirDatosParaVerificar();
    }

    /**
     *
     */
    private void imprimirDatosParaVerificar() {
        System.out.println("PIEZAS");
        for (int i = 0; i < this.piezas.size(); i++) {
            this.piezaDB.insertarPieza(this.piezas.get(i));
        }
        System.out.println("muebles");
        for (int i = 0; i < this.muebles.size(); i++) {
            this.muebleDB.insertarMueble(this.muebles.get(i));
        }
        System.out.println("ENSAMBLE_PIEZAS");
        for (int i = 0; i < this.auxRequerimientosAsubir.size(); i++) {
            this.requerimientoEnsamblajeDB.insertarRequierimientoEnsamblaje(this.auxRequerimientosAsubir.get(i));
        }
        for (Usuario usu : usuarios) {
            usuarioDB.insertarUsuario(usu);
        }
        System.out.println("ENSAMBLAR_MUEBLE");
        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {
            try {
                auxEnsamblajes.get(i).setEstado(true);
                this.ensamblajeDB.insertarEnsamblaje(this.auxEnsamblajes.get(i));
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(CargaDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     */
    public void obtenerRequerimientosVerificados() {
        for (int i = 0; i < this.requerimientoEnsamblajes.size(); i++) {
            if ((existeMueble(requerimientoEnsamblajes.get(i).getMueble()))
                    && (existePieza(requerimientoEnsamblajes.get(i).getPieza()))) {
                this.auxRequerimientosAsubir.add(requerimientoEnsamblajes.get(i));
            }
        }
    }

    public void verificarEnsamblajes() {
        for (int i = 0; i < this.ensamblajes.size(); i++) {
            if (existeMueble(ensamblajes.get(i).getMueble())
                    && (existeUsuario(ensamblajes.get(i).getUsuario()))) {
                this.auxEnsamblajes.add(ensamblajes.get(i));
            }
        }
    }

    /**
     * Modifica la cantidad de muebles de acuerdo a los ensamblados
     */
    private void ingresarMueblesEnsamblados() {
        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {
            for (int j = 0; j < this.muebles.size(); j++) {
                if (auxEnsamblajes.get(i).getMueble().equalsIgnoreCase(muebles.get(j).getNombre())) {
                    muebles.get(j).setCantidadExistente(1);
                }
            }
        }
    }

    /**
     * Caluclar el costo de cada ensamblaje
     */
    private void agregarCostoDeEnsamblaje() {
        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {
            this.auxEnsamblajes.get(i).setCosto(calcularCostoEnsamblaje(this.ensamblajes.get(i).getMueble()));
        }
    }

    /**
     * *
     *
     * @param muebleNombre
     * @return
     */
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

    /**
     *
     * @param tipo
     * @return
     */
    private Pieza recuperarPieza(String tipo) {
        for (int i = 0; i < this.piezas.size(); i++) {
            if (this.piezas.get(i).getTipo().equals(tipo)) {
                return this.piezas.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param usuario
     * @return
     */
    private boolean existeUsuario(String usuario) {
        for (Usuario usu : usuarios) {
            if (usu.getNombre().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tipo
     * @return
     */
    private boolean existePieza(String tipo) {

        for (Pieza pieza : piezas) {
            if (pieza.getTipo().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param nombre
     * @return
     */
    public boolean existeMueble(String nombre) {

        for (Mueble mueble : muebles) {
            if (mueble.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tipo
     */
    private void modificarCantidadPiezas(String tipo) {
        for (int i = 0; i < this.piezas.size(); i++) {
            if (piezas.get(i).getTipo().equalsIgnoreCase(tipo)) {
                piezas.get(i).setCantidadExistente(1);
            }
        }
    }

    /**
     *
     * @param nombre
     */
    private void modificarCantidadMuebles(String nombre) {
        for (int i = 0; i < this.muebles.size(); i++) {
            if (muebles.get(i).getNombre().equalsIgnoreCase(nombre)) {
                muebles.get(i).setCantidadExistente(1);
            }
        }
    }

    /**
     *
     * @param cadena
     * @return
     */
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

    /**
     * Dar formato a la fecha yyyy/mm/dd
     *
     * @param fecha
     * @return
     */
    private String formatoFecha(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaDate = LocalDate.parse(fecha, formato);
        return String.valueOf(fechaDate);
    }
}
