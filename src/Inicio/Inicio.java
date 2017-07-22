/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Ini.Seed;
import Modelo.*;
import Vista.*;
import Ini.*;
import java.util.ArrayList;

/**
 *
 * @author araluce
 */
public class Inicio {

    public static void main(String[] args) {
        Model model = new Model();
        Seed seed = new Seed(model); 
        
//        modelo.dropTable("CLIENTE");
        
        
        Principal principal = new Principal(model);
        principal.setVisible(true);
    }
}
