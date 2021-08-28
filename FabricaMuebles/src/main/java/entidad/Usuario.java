/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author luis
 */
public class Usuario {

    /**
     * tipo 1
     */
    public static final String FABRICA = "1";
    /**
     * tipo 2
     */
    public static final String VENTA = "2";
    /**
     * tipo 3
     */
    public static final String FINANCIERO = "3";

    private String nombre;
    private String password;
    private String tipo;
    /**
     * true: eliminado del sistema, false: no eliminado del sistema
     */
    private boolean estado;

    /**
     * For insert a new USER to DB
     *
     * @param nombre
     * @param password
     * @param tipo
     */
    public Usuario(String nombre, String password, String tipo) {
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     * For insert a new USER to DB
     *
     * @param nombre
     * @param password
     * @param tipo
     * @param estado
     */
    public Usuario(String nombre, String password, String tipo, boolean estado) {
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     *
     * @param estado the tipo to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", password=" + password + ", tipo=" + tipo + ", estado=" + estado + '}';
    }

}
