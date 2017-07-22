/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Modelo.*;
import Vista.*;
import java.util.ArrayList;

/**
 *
 * @author araluce
 */
public class Inicio {

    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        
//        modelo.dropTable();
        modelo.createTablesIfNotExists();
        Principal principal = new Principal(modelo);
        principal.setVisible(true);
    }
}
