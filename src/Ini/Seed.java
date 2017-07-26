package Ini;


import Modelo.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author araluce
 */
public class Seed {
    
    private Model model;
    private ArrayList<String> tablas;
    
    public Seed(Model external_model){
        this.model = external_model;
        
        tablas = new ArrayList<String>();
        tablas.add("CLIENTE");
        tablas.add("ALBARAN");
        
        this.init();
    }
    
    private void init(){
        this.createClientTableIfNotExists();
    }
    
    public void createClientTableIfNotExists() {
        try {
            Statement st = this.model.getConnection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS Client ("
                    + "id int NOT NULL AUTO_INCREMENT, "
                    + "name varchar(20) NOT NULL, "
                    + "lastname1 varchar(20) NOT NULL, "
                    + "lastname2 varchar(20) NOT NULL, "
                    + "dni varchar(9) NOT NULL, "
                    + "phone varchar(9) NOT NULL, "
                    + "delete boolean DEFAULT true"
                    + ")");
            System.out.println("Tabla Client creada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropTable(String table){
        try {
            Statement st = this.model.getConnection().createStatement();
            st.execute("DROP TABLE " + table);
            System.out.println("Tabla " + table + " borrada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}