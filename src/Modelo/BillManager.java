/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Response;

/**
 *
 * @author araluce
 */
public class BillManager {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);

    /**
     * Constructor de BillManager
     */
    public BillManager() {}
    
    /* Encuentra un cliente por dni
     *
     * @param dni 8 caracteres y 1 letra que identifican a un cliente
     * @return Client|null
     */
    public Bill find(Connection conn, int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Bill WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Bill bill = new Bill();
                bill.setId(result.getInt("id"));
                
                // Find Client
                ClientManager cm = new ClientManager();
                Client client = cm.find(conn, result.getInt("client_id"));
                bill.setClient(client);
                
                bill.setDelete(result.getBoolean("delete"));
                
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    bill.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    bill.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Find Ins
                
                // Find Outs
                
                // Find Unsubscribes
                
                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Bill findByClient(Connection conn, Client client) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Bill WHERE client_id LIKE '" + client.getId() + "';");
            if (result.next()) {
                Bill bill = new Bill();
                bill.setClient(client);
                
                bill.setDelete(result.getBoolean("delete"));
                
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    bill.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    bill.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Get Inputs
                
                // Get Outputs
                
                // Get Unsubscribes
                
                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /**
     * Encuentra a todos los clientes
     *
     * @return ArrayList<Client>|null
     */
    public ArrayList<Bill> findAll(Connection conn, boolean all) {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            if (all) {
                result = st.executeQuery("SELECT * FROM Bill;");
            } else {
                result = st.executeQuery("SELECT * FROM Bill WHERE delete != false;");
            }
            billList = resultSetToArray(conn, result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return billList;
    }
    
    private ArrayList<Bill> resultSetToArray(Connection conn, ResultSet result) {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        try {
            while (result.next()) {
                Bill bill = new Bill();
                bill.setId(result.getInt("id"));
                
                ClientManager cm = new ClientManager();
                Client client = cm.find(conn, result.getInt("client_id"));
                bill.setClient(client);
                
                bill.setDelete(result.getBoolean("delete"));
                
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    bill.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    bill.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Get Inputs
                InputManager im = new InputManager();
                ArrayList<Input> bills = im.findByBill(conn, bill);
                bill.addInputs(bills);
                
                // Get Outputs
                
                // Get Unsubscribes
                
                
                billList.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billList;
    }
    
    public Response remove(Bill bill, Connection conn) {
        Response resp = new Response();
        resp.setCode(0);

        try {
            Statement st = conn.createStatement();
            resp.setResponse("Albaran eliminado correctamente");
            resp.setCode(st.executeUpdate("UPDATE Bill "
                    + "SET delete = true "
                    + "WHERE id = " + bill.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("El albaran no ha podido ser eliminado");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }
}