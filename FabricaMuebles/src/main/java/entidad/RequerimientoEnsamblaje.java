/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class RequerimientoEnsamblaje {

    private String pieza;
    private String mueble;
    private int cantidadPiezas;

    /**
     * Un requerimiento de ensamblaje tiene piezas que requiere
     */
    private ArrayList<Pieza> piezas;

    /**
     * For to get a mueble_pieza that required a new assembly
     *
     * @param pieza
     * @param mueble
     * @param cantidadPiezas
     */
    public RequerimientoEnsamblaje(String pieza, String mueble, int cantidadPiezas) {
        this.pieza = pieza;
        this.mueble = mueble;
        this.cantidadPiezas = cantidadPiezas;
    }

    /**
     * Para un ensamblaje requier piezas
     *
     * @param mueble
     * @param piezas
     */
    public RequerimientoEnsamblaje(String mueble, ArrayList<Pieza> piezas) {
        this.mueble = mueble;
        this.piezas = piezas;
    }

    /**
     * @return the pieza
     */
    public String getPieza() {
        return pieza;
    }

    /**
     * @param pieza the pieza to set
     */
    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    /**
     * @return the mueble
     */
    public String getMueble() {
        return mueble;
    }

    /**
     * @param mueble the mueble to set
     */
    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    /**
     * @return the cantidadPiezas
     */
    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    /**
     * @param cantidadPiezas the cantidadPiezas to set
     */
    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }

    @Override
    public String toString() {
        return "RequerimientoEnsamblaje{" + "pieza=" + pieza + ", mueble=" + mueble + ", cantidadPiezas=" + cantidadPiezas + '}';
    }

}
