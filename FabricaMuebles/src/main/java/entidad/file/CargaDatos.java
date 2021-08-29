/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file;

import db.modelo.ClienteDB;
import db.modelo.DetalleEnsamblajeDB;
import db.modelo.EnsamblajeDB;
import db.modelo.MuebleDB;
import db.modelo.PiezaDB;
import db.modelo.PrecioPiezaDB;
import db.modelo.ProductoDB;
import db.modelo.RequerimientoEnsamblajeDB;
import db.modelo.UsuarioDB;
import entidad.Cliente;
import entidad.DetalleEnsamblaje;
import entidad.Ensamblaje;
import entidad.Mueble;
import entidad.Pieza;
import entidad.RequerimientoEnsamblaje;
import entidad.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Array;
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
    //lista de cada objeto
    private ArrayList<Pieza> piezas;
    private ArrayList<Mueble> muebles;
    private ArrayList<Ensamblaje> ensamblajes;
    private ArrayList<RequerimientoEnsamblaje> requerimientoEnsamblajes;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cliente> clientes;
    //errores
    private ArrayList<String> errores;
    //db
    private EnsamblajeDB ensamblajeDB;
    private MuebleDB muebleDB;
    private PiezaDB piezaDB;
    private RequerimientoEnsamblajeDB requerimientoEnsamblajeDB;
    private UsuarioDB usuarioDB;
    private ClienteDB clienteDB;
    private PrecioPiezaDB precioPiezaDB;
    private DetalleEnsamblajeDB detalleEnsamblajeDB;
    private ProductoDB productoDB;
    //lista para todas las piezas
    private ArrayList<Pieza> todasPiezas;
    private ArrayList<Pieza> piezasTipoCosto;
    //auxiliares
    private ArrayList<RequerimientoEnsamblaje> auxRequerimientosAsubir;
    private ArrayList<Ensamblaje> auxEnsamblajes;

    public CargaDatos(BufferedReader bufferedReader) throws IOException {
        this.file = new ManejoArchivo();
        this.piezas = new ArrayList<>();
        this.muebles = new ArrayList<>();
        this.ensamblajes = new ArrayList<>();
        this.requerimientoEnsamblajes = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.errores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        //db
        this.ensamblajeDB = new EnsamblajeDB();
        this.muebleDB = new MuebleDB();
        this.piezaDB = new PiezaDB();
        this.requerimientoEnsamblajeDB = new RequerimientoEnsamblajeDB();
        this.usuarioDB = new UsuarioDB();
        this.clienteDB = new ClienteDB();
        this.precioPiezaDB = new PrecioPiezaDB();
        this.detalleEnsamblajeDB = new DetalleEnsamblajeDB();
        this.productoDB = new ProductoDB();
        //
        this.todasPiezas = new ArrayList<>();
        this.piezasTipoCosto = new ArrayList<>();
        //inicializacion de auxilieare
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

                    String nombre = quitarEspacios(pedazo[1].substring(1, pedazo[1].length() - 1));
                    String pass = quitarEspacios(pedazo[2].substring(1, pedazo[2].length() - 1));
                    String tipo = quitarEspacios(pedazo[3]);
                    Usuario usuario = new Usuario(nombre, pass, tipo);
                    if (!existeUsuario(usuario.getNombre())) {
                        this.usuarios.add(usuario);
                    } else {
                        this.errores.add("El nombre de usuario ya existe (" + linea + ")" + " línea ");
                    }
                } else if (pedazo[0].equalsIgnoreCase("PIEZA")) {
                    String tipo = pedazo[1].substring(1, pedazo[1].length() - 1);
                    double precio = Double.parseDouble(quitarEspacios(pedazo[2]));

                    Pieza pieza = new Pieza(tipo, precio);

                    this.todasPiezas.add(pieza);
                    if (!existePieza(tipo)) {
                        this.piezas.add(new Pieza(tipo));
                    }
                    if (!existePiezaTipoCosto(pieza)) {
                        pieza.setCantidadExistente(0);
                        pieza.setEstado(false);
                        this.piezasTipoCosto.add(pieza);
                    }
                } else if (pedazo[0].equalsIgnoreCase("MUEBLE")) {
                    String nombre = pedazo[1].substring(1, pedazo[1].length() - 1);
                    double costo = Double.parseDouble(quitarEspacios(pedazo[2]));

                    Mueble mueble = new Mueble(nombre, costo);

                    if (!existeMueble(nombre)) {
                        this.muebles.add(mueble);
                    } else {
                        this.errores.add("El nombre del mueble ya existe, detalle: (" + linea + ")" + " línea ");
                    }
                } else if (pedazo[0].equalsIgnoreCase("ENSAMBLE_PIEZAS")) {

                    String mueble = pedazo[1].substring(1, pedazo[1].length() - 1);
                    String pieza = pedazo[2].substring(1, pedazo[2].length() - 1);
                    int cantidad = Integer.parseInt(quitarEspacios(pedazo[3]));

                    RequerimientoEnsamblaje reque = new RequerimientoEnsamblaje(pieza, mueble, cantidad);
                    this.requerimientoEnsamblajes.add(reque);

                } else if (pedazo[0].equalsIgnoreCase("ENSAMBLAR_MUEBLE")) {
                    String fecha = quitarEspacios(pedazo[3].substring(1, pedazo[3].length() - 1));
                    String mueble = pedazo[1].substring(1, pedazo[1].length() - 1);
                    String usuario = quitarEspacios(pedazo[2]);

                    Ensamblaje ensamblaje = new Ensamblaje(formatoFecha(fecha), mueble, usuario);
                    this.ensamblajes.add(ensamblaje);
                    System.out.println(ensamblaje.toString());

                } else if (pedazo[0].equalsIgnoreCase("CLIENTE")) {
                    ingresarCliente(linea);
                } else {
                    System.out.println("error en " + linea);
                    this.errores.add("Error en: (" + linea + ")" + " línea ");
                }
            } catch (Exception e) {
                System.out.println("error en " + linea);
                this.errores.add("Error en: (" + linea + ")" + " línea ");
            }

        }
        //verificamos lo requerimiento necesarios
        obtenerRequerimientosVerificados();
        //verificar ensamblajes y
        //agregamos el costo de ensamblaje
        verificarEnsamblajes();
        //modificamos la cantidad de piezs existentes para cada tipo y precio
        modificarCantidadDePiezasSegunTipoYPrecio();
        //se suben todos los datos
        imprimirDatosParaVerificar();
    }

    /**
     *
     */
    private void imprimirDatosParaVerificar() {
        System.out.println("PIEZAS");
        for (int i = 0; i < this.piezas.size(); i++) {
            try {
                this.piezaDB.insertarPieza(this.piezas.get(i));
            } catch (SQLException ex) {

            }
        }
        System.out.println("precio-piezas");
        for (Pieza pieza : piezasTipoCosto) {
            try {
                //pieza.setCantidadExistente(0);
                this.precioPiezaDB.insertarPrecioPieza(pieza);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
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
            usu.setEstado(false);
            usuarioDB.insertarUsuario(usu);
        }
        System.out.println("ENSAMBLAR_MUEBLE");
        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {
            try {
                //ensamblaje registrado, es decir se agrega a la sala de ventas
                this.auxEnsamblajes.get(i).setEstado(true);
                System.out.println(this.auxEnsamblajes.get(i).toString());
                this.ensamblajeDB.insertarEnsamblaje(this.auxEnsamblajes.get(i));
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                System.out.println("error al insertar el ensamblaje");
                Logger.getLogger(CargaDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Cliente c : clientes) {
            this.clienteDB.insertarCliente(c);
        }
        System.out.println("insertar producto");
        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {
            try {
                this.productoDB.insertarProducto((i + 1), false);
                System.out.println("producto insertado");
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                System.out.println("producto no insertado");
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Detalle ensamblaje");

        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {

            ArrayList<Pieza> detallePiezas = this.auxEnsamblajes.get(i).getPiezas();
            for (int j = 0; j < detallePiezas.size(); j++) {
                try {
                    DetalleEnsamblaje detalle = new DetalleEnsamblaje(
                            (i + 1),
                            detallePiezas.get(j).getCosto(),
                            detallePiezas.get(j).getTipo());

                    this.detalleEnsamblajeDB.insertarDetalleDelEnsamblaje(detalle);
                    System.out.println("detalle insertado");
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    System.out.println("detalle no insertado");
                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println("");
        System.out.println("Errores");
        for (String e : errores) {
            System.out.println(e);
        }
        System.out.println("Pieza - precios");
        for (Pieza pieza : piezasTipoCosto) {
            System.out.println(pieza.toString());
        }

    }

    /**
     * Verifia si existe los requerimientos necesarios nombre-mueble y
     * tipo-pieza
     */
    private void obtenerRequerimientosVerificados() {
        for (int i = 0; i < this.requerimientoEnsamblajes.size(); i++) {
            if ((existeMueble(requerimientoEnsamblajes.get(i).getMueble())) && (existePieza(requerimientoEnsamblajes.get(i).getPieza()))) {
                this.auxRequerimientosAsubir.add(requerimientoEnsamblajes.get(i));
            } else {
                this.errores.add("Hace falta piezas requeridas para este tipo de ensamblaje: ("
                        + requerimientoEnsamblajes.get(i).getMueble()
                        + requerimientoEnsamblajes.get(i).getPieza()
                        + requerimientoEnsamblajes.get(i).getCantidadPiezas() + ")");
            }
        }
    }

    /**
     * Verifica si se puede realizar los ensamblajes
     */
    private void verificarEnsamblajes() {
        for (int i = 0; i < this.ensamblajes.size(); i++) {
            //verificamso si existe el mueble y el usuario que ensambló
            if (existeMueble(ensamblajes.get(i).getMueble()) && (existeUsuario(ensamblajes.get(i).getUsuario()))) {

                for (int j = 0; j < this.requerimientoEnsamblajes.size(); j++) {
                    //
                    if (ensamblajes.get(i).getMueble().equals(requerimientoEnsamblajes.get(j).getMueble())) {
                        //verificamos si hay piezas todavía
                        if (aunHayPiezasSuficientes(this.requerimientoEnsamblajes.get(j))) {

                            double costoEnsamblaje = 0;

                            ArrayList<Pieza> piezasRequeridas = obtenerPiezasRequeridas(requerimientoEnsamblajes.get(i));
                            System.out.println(piezasRequeridas.size());
                            for (int k = 0; k < piezasRequeridas.size(); k++) {
                                costoEnsamblaje += piezasRequeridas.get(k).getCosto();
                            }
                            //establecemos costo y estado del ensamblaje
                            this.ensamblajes.get(i).setCosto(costoEnsamblaje);
                            this.ensamblajes.get(i).setEstado(true);
                            this.ensamblajes.get(i).setPiezas(piezasRequeridas);
                            //
                            this.auxEnsamblajes.add(this.ensamblajes.get(i));
                        } else {
                            System.out.println("piezas ya no alcanzan");
                            this.errores.add("Ya no hay piezas suficientes para el ensamblaje : "
                                    + "(" + ensamblajes.get(i).getMueble() + ","
                                    + ensamblajes.get(i).getUsuario() + ","
                                    + ensamblajes.get(i).getFecha() + ")");
                        }
                    }
                }
            } else {
                this.errores.add("No existe la pieza o mueble para este requerimiento-ensamblaje : "
                        + "(" + ensamblajes.get(i).getMueble() + ","
                        + ensamblajes.get(i).getUsuario() + ","
                        + ensamblajes.get(i).getFecha() + ")");
            }
        }
    }

    private ArrayList<Pieza> obtenerPiezasRequeridas(RequerimientoEnsamblaje requerimiento) {
        ArrayList<Pieza> requeridos = new ArrayList<>();
        for (int i = 0; i < requerimiento.getCantidadPiezas(); i++) {
            for (int k = 0; k < this.todasPiezas.size(); k++) {
                if (requerimiento.getPieza().equalsIgnoreCase(todasPiezas.get(k).getTipo())) {
                    requeridos.add(todasPiezas.get(k));
                    this.todasPiezas.remove(k);
                    break;
                }
            }
        }
        return requeridos;
    }

    private boolean aunHayPiezasSuficientes(RequerimientoEnsamblaje requerimiento) {
        int contador = 0;
        for (int i = 0; i < this.todasPiezas.size(); i++) {
            if (requerimiento.getPieza().equalsIgnoreCase(todasPiezas.get(i).getTipo())) {
                contador++;
            }
        }
        return contador >= requerimiento.getCantidadPiezas();
    }

    private void modificarCantidadDePiezasSegunTipoYPrecio() {
        System.out.println("todas piezs" + todasPiezas.size());
        for (int i = 0; i < this.piezasTipoCosto.size(); i++) {
            int contador = 0;
            for (int j = 0; j < this.todasPiezas.size(); j++) {
                if (todasPiezas.get(j).getTipo().equalsIgnoreCase(piezasTipoCosto.get(i).getTipo())
                        && (todasPiezas.get(j).getCosto() == piezasTipoCosto.get(i).getCosto())) {
                    contador++;
                }
            }
            this.piezasTipoCosto.get(i).setCantidadExistente(contador);
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
     * Verifica si en el archivo de entrada existe un usuario con el mismo
     * nombre importando mayúsculas y minúsculas
     *
     * @param usuario
     * @return
     */
    private boolean existeUsuario(String usuario) {
        for (Usuario usu : usuarios) {
            if (usu.getNombre().equalsIgnoreCase(usuario)) {
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

    private boolean existePiezaTipoCosto(Pieza pieza) {

        for (int i = 0; i < this.piezasTipoCosto.size(); i++) {
            if (this.piezasTipoCosto.get(i).getTipo().equalsIgnoreCase(pieza.getTipo()) && (this.piezasTipoCosto.get(i).getCosto() == pieza.getCosto())) {
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
     * Analiza la linea con el comando CLIENTE al principio
     *
     * @param cadena
     */
    public void ingresarCliente(String cadena) {
        String[] cadenas = cadena.split(",");
        String nombre = "";
        String nit = "";
        String direccion = "";
        String municipio = "";
        String departamento = "";
        Cliente c = null;
        if (cadenas.length == 6) {
            nombre = cadenas[1].substring(1, cadenas[1].length() - 1);
            nit = cadenas[2].substring(1, cadenas[2].length() - 1);
            direccion = cadenas[3].substring(1, cadenas[3].length() - 1);
            municipio = cadenas[4].substring(1, cadenas[4].length() - 1);
            departamento = cadenas[5].substring(1, cadenas[5].length() - 1);
            c = new Cliente(nit, nombre, direccion + ", " + municipio + ", " + departamento);

        } else if (cadenas.length == 4) {
            nombre = cadenas[1].substring(1, cadenas[1].length() - 1);
            nit = cadenas[2].substring(1, cadenas[2].length() - 1);
            direccion = cadenas[3].substring(1, cadenas[3].length() - 1);
            c = new Cliente(nit, nombre, direccion);
        }
        if (!existeCliente(nit) && !(nit.contains("-"))) {
            this.clientes.add(c);
        } else {
            if (existeCliente(nit) || (nit.contains("-"))) {
                this.errores.add("El nit del cliente está ya existe o el nit contiene (-). Detalle: (" + cadena + ")" + " línea ");

            }
        }

    }

    /**
     * Verifica si el nit ya existe
     *
     * @param nit
     * @return
     */
    public boolean existeCliente(String nit) {

        for (Cliente c : clientes) {
            if (c.getNit().equalsIgnoreCase(nit)) {
                return true;
            }
        }
        return false;
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
