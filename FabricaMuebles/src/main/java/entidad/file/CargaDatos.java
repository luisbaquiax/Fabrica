/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.file;

import db.modelo.*;
import entidad.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class CargaDatos {

    private ManejoArchivo manejadoArchivo;
    //lista de cada objeto
    private ArrayList<Pieza> piezas;
    private ArrayList<Mueble> muebles;
    private ArrayList<Ensamblaje> ensamblajes;
    private ArrayList<RequerimientoEnsamblaje> requerimientoEnsamblajes;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cliente> clientes;
    //errores
    private ArrayList<String> errores;
    //instancias de las clases que usan la base de datos
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
    //lector
    private BufferedReader bufferedReader;

    public CargaDatos(BufferedReader bufferedReader) throws IOException {
        //obtiene la información desde el archivo de entrada
        this.manejadoArchivo = new ManejoArchivo();
        //objetos
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
        //lector
        this.bufferedReader = bufferedReader;
        //leerInformacion(this.file.informacionEntrada(this.bufferedReader));
    }

    public void leerInformacion(String informacion) {
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
                        //pieza.setCantidadExistente(0);
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

                    ArrayList<Pieza> piezasRequeridas = new ArrayList<>();

                    RequerimientoEnsamblaje reque = new RequerimientoEnsamblaje(mueble, piezasRequeridas);

                    if (!existeMuebleEnElRequerimiento(reque)) {
                        reque.getPiezas().add(new Pieza(pieza, cantidad));
                        this.requerimientoEnsamblajes.add(reque);
                    } else {
                        //verificar si a este requerimiento ya tiene la pieza que viene
                        RequerimientoEnsamblaje r = getRequerimiento(mueble);
                        if (!existePiezaDelRequerimiento(r.getPiezas(), pieza)) {
                            r.getPiezas().add(new Pieza(pieza, cantidad));
                        } else {
                            this.errores.add("Se esta repitiendo datos para el ENSAMBLE_PIEZAS: (" + linea + ")" + " línea ");
                        }
                    }

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
    }

    /**
     * Método que subirá tod la información en la base de datos
     */
    public void imprimirDatosParaVerificar() {
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
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Detalle ensamblaje");

        for (int i = 0; i < this.auxEnsamblajes.size(); i++) {

            ArrayList<Pieza> detallePiezas = this.auxEnsamblajes.get(i).getPiezas();
            for (int j = 0; j < detallePiezas.size(); j++) {
                for (int k = 0; k < detallePiezas.get(j).getCantidadExistente(); k++) {
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
                        //recorriendo piezas de ese requerimiento
                        RequerimientoEnsamblaje auxi = this.requerimientoEnsamblajes.get(j);
                        //verificamos si hay piezas todavía
                        if (aunHayPiezasSuficientes(auxi.getPiezas())) {

                            double costoEnsamblaje = 0;

                            for (int k = 0; k < auxi.getPiezas().size(); k++) {

                                Pieza piezaAuxi = auxi.getPiezas().get(k);

                                ArrayList<Pieza> piezasRequeridasParaEstaPieza = obtenerPiezasRequeridas(piezaAuxi);

                                System.out.println(piezasRequeridasParaEstaPieza.size());
                                for (int l = 0; l < piezasRequeridasParaEstaPieza.size(); l++) {

                                    costoEnsamblaje += piezasRequeridasParaEstaPieza.get(l).getCosto();
                                }

                            }
                            //establecemos costo y estado del ensamblaje
                            this.ensamblajes.get(i).setCosto(costoEnsamblaje);
                            this.ensamblajes.get(i).setEstado(true);
                            this.ensamblajes.get(i).setPiezas(this.requerimientoEnsamblajes.get(j).getPiezas());
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
                this.errores.add("No existe el o mueble para este requerimiento-ensamblaje : "
                        + "(" + ensamblajes.get(i).getMueble() + ","
                        + ensamblajes.get(i).getUsuario() + ","
                        + ensamblajes.get(i).getFecha() + ")");
            }
        }
    }

    private ArrayList<Pieza> obtenerPiezasRequeridas(Pieza pieza) {
        ArrayList<Pieza> requeridos = new ArrayList<>();
        for (int i = 0; i < pieza.getCantidadExistente(); i++) {
            for (int k = 0; k < this.todasPiezas.size(); k++) {
                if (pieza.getTipo().equalsIgnoreCase(todasPiezas.get(k).getTipo())) {
                    pieza.setCosto(todasPiezas.get(k).getCosto());
                    requeridos.add(todasPiezas.get(k));
                    System.out.println(todasPiezas.get(k).toString());
                    this.todasPiezas.remove(k);
                    break;
                }
            }
        }
        return requeridos;
    }

    private boolean aunHayPiezasSuficientes(ArrayList<Pieza> piezas) {
        int contador = 0;
        int piezasNecesarias = 0;
        for (Pieza pieza : piezas) {
            piezasNecesarias += pieza.getCantidadExistente();
        }
        for (int i = 0; i < this.todasPiezas.size(); i++) {
            for (int j = 0; j < piezas.size(); j++) {
                for (int k = 0; k < piezas.get(j).getCantidadExistente(); k++) {
                    if (this.todasPiezas.get(i).getTipo().equalsIgnoreCase(piezas.get(j).getTipo())) {
                        contador++;
                    }
                }
            }
        }
        return contador >= piezasNecesarias;
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
    private boolean existeCliente(String nit) {

        for (Cliente c : clientes) {
            if (c.getNit().equalsIgnoreCase(nit)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * En caso de ya existe este requerimiento solo que hay que agregarle las
     * piezas requeridas
     *
     * @param requerimientoEnsamblaje
     * @return
     */
    private boolean existeMuebleEnElRequerimiento(RequerimientoEnsamblaje requerimientoEnsamblaje) {
        for (RequerimientoEnsamblaje reque : this.requerimientoEnsamblajes) {
            if (reque.getMueble().equalsIgnoreCase(requerimientoEnsamblaje.getMueble())) {
                return true;
            }
        }
        return false;
    }

    private boolean existePiezaDelRequerimiento(ArrayList<Pieza> piezas, String piez) {
        for (Pieza pieza : piezas) {
            if (pieza.getTipo().equalsIgnoreCase(piez)) {
                return true;
            }
        }
        return false;
    }

    private RequerimientoEnsamblaje getRequerimiento(String mueble) {
        for (RequerimientoEnsamblaje re : this.requerimientoEnsamblajes) {
            if (re.getMueble().equalsIgnoreCase(mueble)) {
                return re;
            }
        }
        return null;
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

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public ArrayList<RequerimientoEnsamblaje> getRequerimientoEnsamblajes() {
        return requerimientoEnsamblajes;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    public ArrayList<Pieza> getPiezasTipoCosto() {
        return piezasTipoCosto;
    }

    public ArrayList<RequerimientoEnsamblaje> getAuxRequerimientosAsubir() {
        return auxRequerimientosAsubir;
    }

    public ArrayList<Ensamblaje> getAuxEnsamblajes() {
        return auxEnsamblajes;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public ManejoArchivo getManejadoArchivo() {
        return manejadoArchivo;
    }

}
