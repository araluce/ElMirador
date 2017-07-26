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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Response;

/**
 *
 * @author araluce
 */
public class BillManager {

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
                
                ClientManager cm = new ClientManager();
                Client client = cm.find(conn, result.getInt("client_id"));
                bill.setClient(client);
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                client.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                client.setUpdatedAt(date);
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
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                bill.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                bill.setUpdatedAt(date);
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
                
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                bill.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                bill.setUpdatedAt(date);
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
            resp.setCode(st.executeUpdate("UPDATE Albaran "
                    + "SET delete = true "
                    + "WHERE id = " + bill.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("El albaran no ha podido ser eliminado");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }
}