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
        tablas.add("Client");
        tablas.add("Bill");
        
        this.init();
    }
    
    private void init(){
//        this.dropTable("Client");
//        this.dropTable("Bill");
        this.createClientTableIfNotExists();
        this.createBilltTableIfNotExists();
        this.createImputTableIfNotExists();
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
                    + "delete boolean DEFAULT true, "
                    + "created_at varchar(30) NOT NULL, "
                    + "updated_at varchar(30) NOT NULL"
                    + ")");
            System.out.println("Tabla Client creada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createImputTableIfNotExists() {
        try {
            Statement st = this.model.getConnection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS Input ("
                    + "id int NOT NULL AUTO_INCREMENT, "
                    + "bill_id int NOT NULL, "
                    + "date_input varchar(30) NOT NULL, "
                    + "lot_number int NOT NULL AUTO_INCREMENT, "
                    + "weight float NOT NULL, "
                    + "price float NOT NULL, "
                    + "t_reception varchar(20) NOT NULL, "
                    + "num_hams int NOT NULL, "
                    + "num_palettes int NOT NULL, "
                    + "delete boolean DEFAULT true "
                    + ")");
            System.out.println("Tabla Inputs creada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createOutputTableIfNotExists() {
        try {
            Statement st = this.model.getConnection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS Output ("
                    + "id int NOT NULL AUTO_INCREMENT, "
                    + "bill_id int NOT NULL, "
                    + "client_id int NOT NULL, "
                    + "pref_consumption varchar(30) NOT NULL, "
                    + "date_output varchar(30) NOT NULL, "
                    + "destination varchar(30) NOT NULL, "
                    + "tagged varchar(30) NOT NULL, "
                    + "num_hams int NOT NULL, "
                    + "num_palettes int NOT NULL, "
                    + "delete boolean DEFAULT true"
                    + ")");
            System.out.println("Tabla Outputs creada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createBilltTableIfNotExists() {
        try {
            Statement st = this.model.getConnection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS Bill ("
                    + "id int NOT NULL AUTO_INCREMENT, "
                    + "client_id int NOT NULL, "
                    + "created_at varchar(30) NOT NULL, "
                    + "updated_at varchar(30) NOT NULL"
                    + "delete boolean DEFAULT true"
                    + ")");
            System.out.println("Tabla Bill creada correctamente");
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
