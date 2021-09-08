/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad.utiles;

import java.sql.Date;

/**
 *
 * @author luis
 */
public class Util {

    public Util() {
    }

    public boolean formatoHecho(String fecha) {
        try {
            Date.valueOf(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
