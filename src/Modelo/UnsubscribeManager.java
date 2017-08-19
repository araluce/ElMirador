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
public class UnsubscribeManager {
    
    private Model model;
    private Connection conn;
    private SimpleDateFormat sdf;

    /**
     * Constructor UnsubscribeManager
     */
    public UnsubscribeManager(Model model) {
        this.model = model;
        this.conn = model.getConnection();
        this.sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);
    }

    /* Find unsubscribe by id
     *
     * @param id
     * @return Unsubscribe|null
     */
    public Unsubscribe find(int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Unsubscribe WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Unsubscribe unsubscribe = new Unsubscribe(model);
                unsubscribe.setId(result.getInt("id"));                
                unsubscribe.setNumHamsUnsubscribes(result.getInt("num_hams_unsubscribes"));
                unsubscribe.setNumPalettesUnsubscribes(result.getInt("num_palettes_unsubscribes"));
                unsubscribe.setReason(result.getString("reason"));
                unsubscribe.setObservations(result.getString("observations"));
                unsubscribe.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("date_unsubscribe")));
                    unsubscribe.setDateUnsubscribe(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    unsubscribe.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    unsubscribe.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(UnsubscribeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return unsubscribe;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    

    /**
     * Find unsubscribe by bill
     *
     * @param bill A bill
     * @return Input|null
     */
    public ArrayList<Unsubscribe> findByBill(Bill bill) {
        ArrayList<Unsubscribe> unsubscribes = null;
        ResultSet results = null;
        try {
            Statement st = conn.createStatement();
            results = st.executeQuery("SELECT * FROM Unsubscribe WHERE bill_id LIKE '" + bill.getId() + "';");
            
            unsubscribes = this.resultSetToArray(results);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unsubscribes;
    }

    /**
     * Insert a new unsubscribe into Unsubscribe DB table
     *
     * @param unsubscribe
     * @return int
     */
    public int flush(Unsubscribe unsubscribe) {
        if(unsubscribe.getId() == 0)
            return save(unsubscribe);
        return update(unsubscribe);
    }
    
    public int save(Unsubscribe unsubscribe){
        int result = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO Unsubscribe ("
                    + "bill_id, date_unsubscribe, num_hams_unsubscribes, num_palettes_unsubscribes, "
                    + "reason, observations, delete, "
                    + "created_at, updated_at) "
                    + "VALUES ("
                    + "" + unsubscribe.getBill().getId()
                    + ", '" + sdf.format((Date)unsubscribe.getDateUnsubscribe().getTime())
                    + "', " + unsubscribe.getNumHamsUnsubscribes()
                    + ", " + unsubscribe.getNumPalettesUnsubscribes()
                    + ", '" + unsubscribe.getReason()
                    + "', '" + unsubscribe.getObservations()
                    + "', " + unsubscribe.getDelete()
                    + ", '" + sdf.format((Date) unsubscribe.getCreatedAt().getTime()) 
                    + "', '" + sdf.format((Date) unsubscribe.getUpdatedAt().getTime())
                    + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int update(Unsubscribe unsubscribe){
        int result = 0;
        
        return result;
    }

    private ArrayList<Unsubscribe> resultSetToArray(ResultSet result) {
        ArrayList<Unsubscribe> unsubscribes = new ArrayList<Unsubscribe>();
        try {
            while (result.next()) {
                Unsubscribe unsubscribe = new Unsubscribe(model);
                unsubscribe.setId(result.getInt("id"));
                unsubscribe.setNumHamsUnsubscribes(result.getInt("num_hams_unsubscribes"));
                unsubscribe.setNumPalettesUnsubscribes(result.getInt("num_palettes_unsubscribes"));
                unsubscribe.setReason(result.getString("reason"));
                unsubscribe.setObservations(result.getString("observations"));
                unsubscribe.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("date_unsubscribe")));
                    unsubscribe.setDateUnsubscribe(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    unsubscribe.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    unsubscribe.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(UnsubscribeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                unsubscribes.add(unsubscribe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unsubscribes;
    }

    public Response remove(Unsubscribe unsubcribe) {
        Response resp = new Response();
        resp.setCode(0);

        try {
            Statement st = conn.createStatement();
            resp.setResponse("Baja eliminada correctamente");
            resp.setCode(st.executeUpdate("UPDATE Unsubcribe "
                    + "SET delete = true "
                    + "WHERE id = " + unsubcribe.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("La baja no ha podido ser eliminada");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }
    
    /** 
     * Find the last id in the table
     *
     * @return int
     */
    public int getLastId() {
        ResultSet result = null;
        int last_id = 0;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT MAX(id) as last_id FROM Unsubscribe");
            if (result.next()) {
                last_id = result.getInt("last_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return last_id;
    }

}
