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
public class RequerimientoEnsamblaje {

    private String pieza;
    private String mueble;
    private int cantidadPiezas;

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

}
