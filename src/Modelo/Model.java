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
import src.Response;

/**
 *
 * @author araluce
 */
public class Model {

    private Connection conn;

    /**
     * Constructor de la clase Modelo
     */
    public Model() {
        try {
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection("jdbc:h2:./elMiradorDB;IFEXISTS=TRUE;mv_store=false", "test", "test");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
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
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public Response hardReset(){
        Response resp = new Response();
        
        return resp;
    }
}
