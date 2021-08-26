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
public class Producto {

    private String nombre;
    private double precio;
    private int identificador;
    private boolean vendido;

    /**
     * Productos a mostrar
     *
     * @param nombre
     * @param precio
     * @param identificador
     */
    public Producto(String nombre, double precio, int identificador) {
        this.nombre = nombre;
        this.precio = precio;
        this.identificador = identificador;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     *
     * @return
     */
    public boolean isVendido() {
        return vendido;
    }

    /**
     *
     * @param vendido
     */
    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", identificador=" + identificador + ", vendido=" + vendido + '}';
    }

}
