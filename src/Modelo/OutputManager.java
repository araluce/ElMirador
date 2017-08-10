/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Response;

/**
 *
 * @author araluce
 */
public class OutputManager {

    /**
     * Constructor de ClienteManager
     */
    public OutputManager() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);

    /* Find a output by id
     *
     * @param conn
     * @param id
     * @return Output|null
     */
    public Output find(Connection conn, int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Output WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Output output = new Output();
                output.setId(result.getInt("id"));
                
                BillManager bm = new BillManager();
                Bill bill = bm.find(conn, result.getInt("bill_id"));
                output.setBill(bill);
                
                ClientManager cm = new ClientManager();
                Client client = cm.find(conn, result.getInt("client_id"));
                output.setClient(client);
                
                output.setDestination(result.getString("destination"));
                output.setTagged(result.getString("tagged"));
                output.setNumHams(result.getInt("num_hams"));
                output.setNumPalettes(result.getInt("num_palettes"));
                output.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("pref_consumption")));
                    output.setPrefConsuption(date);
                    date.setTime(sdf.parse(result.getString("date_output")));
                    output.setDateOutput(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    output.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    output.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(OutputManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return output;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    

    /**
     * Find an output by bill
     *
     * @param bill A bill
     * @param conn
     * @return Output|null
     */
    public ArrayList<Output> findByBill(Connection conn, Bill bill) {
        ArrayList<Output> outputs = null;
        ResultSet results = null;
        try {
            Statement st = conn.createStatement();
            results = st.executeQuery("SELECT * FROM Output WHERE bill_id LIKE '" + bill.getId() + "';");
            
            outputs = this.resultSetToArray(conn, results);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return outputs;
    }
    
    /**
     * Find an output by client
     *
     * @param client A client
     * @param conn
     * @return Output|null
     */
    public ArrayList<Output> findByClient(Connection conn, Client client) {
        ArrayList<Output> outputs = null;
        ResultSet results = null;
        try {
            Statement st = conn.createStatement();
            results = st.executeQuery("SELECT * FROM Output WHERE client_id LIKE '" + client.getId() + "';");
            
            outputs = this.resultSetToArray(conn, results);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return outputs;
    }

    /**
     * Insert a new output into Output table
     *
     * @param conn
     * @param output
     * @return int
     */
    public int flush(Connection conn, Output output) {
        int result = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO Output ("
                    + "bill_id, client_id, date_output, "
                    + "pref_consumption, destination, tagged, "
                    + "num_hams, num_palettes, delete"
                    + "created_at, updated_at,) "
                    + "VALUES ("
                    + "" + output.getBill().getId()
                    + ", " + output.getClient().getId()
                    + ", '" + sdf.format((Date)output.getDateOutput().getTime())
                    + "', '" + sdf.format((Date)output.getPrefConsuption().getTime())
                    + "', '" + output.getDestination()
                    + "', '" + output.getTagged()
                    + "', " + output.getNumHams()
                    + ", " + output.getNumPalettes()
                    + ", " + output.getDelete()
                    + ", '" + sdf.format((Date) output.getCreatedAt().getTime()) 
                    + "', '" + sdf.format((Date) output.getUpdatedAt().getTime())
                    + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private ArrayList<Output> resultSetToArray(Connection conn, ResultSet result) {
        ArrayList<Output> outputs = new ArrayList<Output>();
        try {
            while (result.next()) {
                Output output = new Output();
                output.setId(result.getInt("id"));
                
                BillManager bm = new BillManager();
                Bill bill = bm.find(conn, result.getInt("bill_id"));
                output.setBill(bill);
                
                ClientManager cm = new ClientManager();
                Client client = cm.find(conn, result.getInt("client_id"));
                output.setClient(client);
                
                output.setDestination(result.getString("destination"));
                output.setTagged(result.getString("tagged"));
                output.setNumHams(result.getInt("num_hams"));
                output.setNumPalettes(result.getInt("num_palettes"));
                output.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("pref_consumption")));
                    output.setPrefConsuption(date);
                    date.setTime(sdf.parse(result.getString("date_output")));
                    output.setDateOutput(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    output.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    output.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(OutputManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                outputs.add(output);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputs;
    }

    public Response remove(Output output, Connection conn) {
        Response resp = new Response();
        resp.setCode(0);

        try {
            Statement st = conn.createStatement();
            resp.setResponse("Salida eliminada correctamente");
            resp.setCode(st.executeUpdate("UPDATE Output "
                    + "SET delete = true "
                    + "WHERE id = " + output.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("La salida no ha podido ser eliminada");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

}
