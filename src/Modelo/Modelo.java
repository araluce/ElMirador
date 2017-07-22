/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author araluce
 */
public class Modelo {

    private Connection conn;

    /**
     * Constructor de la clase Modelo
     */
    public Modelo() {
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:../elMiradorDB;IFEXISTS=TRUE", "test", "test");
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }
    
    public void dropTable(){
        try {
            Statement st = this.conn.createStatement();
            st.execute("DROP TABLE CLIENTE");
            System.out.println("Tabla creada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba para crear una tabla nueva llamada TEST con una columna "nombre"
     */
    public void createTablesIfNotExists() {
        try {
            Statement st = this.conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS CLIENTE ("
                    + "nombre varchar(20) NOT NULL, "
                    + "apellido1 varchar(20) NOT NULL, "
                    + "apellido2 varchar(20) NOT NULL, "
                    + "dni varchar(9) NOT NULL, "
                    + "telefono varchar(9) NOT NULL"
                    + ")");
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ejecuta inserciones dentro de la base de datos
     *
     * @param query
     * @return ResultSet resultados de la query
     */
    public int update(String query) {
        int resultado = 0;
        try {
            Statement st = this.conn.createStatement();
            resultado = st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Ejecuta consultas dentro de la base de datos
     *
     * @param query
     * @return ResultSet resultados de la query
     */
    public ResultSet query(String query) {
        ResultSet resultado = null;
        try {
            Statement st = this.conn.createStatement();
            resultado = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
