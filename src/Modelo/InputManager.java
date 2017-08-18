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
public class InputManager {

    /**
     * Constructor InputeManager
     */
    public InputManager() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);

    /* Encuentra una entrada por id
     *
     * @param conn
     * @param id
     * @return Input|null
     */
    public Input find(Connection conn, int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Input WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Input input = new Input();
                input.setId(result.getInt("id"));
                
                BillManager bm = new BillManager();
                Bill bill = bm.find(conn, result.getInt("bill_id"));
                input.setBill(bill);
                
                input.setLotNumber(result.getInt("lot_number"));
                input.setWeight(result.getFloat("weight"));
                input.setPrice(result.getFloat("price"));
                input.setTReception(result.getString("t_reception"));
                input.setNumHams(result.getInt("num_hams"));
                input.setNumPalettes(result.getInt("num_palettes"));
                input.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("date_input")));
                    input.setDateInput(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    input.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    input.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(InputManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return input;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    

    /**
     * Encuentra una entrada por albarán
     *
     * @param bill A bill
     * @param conn
     * @return Input|null
     */
    public ArrayList<Input> findByBill(Connection conn, Bill bill) {
        ArrayList<Input> inputs = null;
        ResultSet results = null;
        try {
            Statement st = conn.createStatement();
            results = st.executeQuery("SELECT * FROM Input WHERE bill_id LIKE '" + bill.getId() + "';");
            
            inputs = this.resultSetToArray(conn, results);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inputs;
    }

    /**
     * Inserta un nuevo input en la tabla Input
     *
     * @param conn
     * @param input
     * @return int
     */
    public int flush(Connection conn, Input input) {
        int result = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO Input ("
                    + "bill_id, date_input, lot_number, "
                    + "weight, price, t_reception, "
                    + "num_hams, num_palettes, delete, "
                    + "created_at, updated_at) "
                    + "VALUES ("
                    + "" + input.getBill().getId()
                    + ", '" + sdf.format((Date)input.getDateInput().getTime())
                    + "', " + input.getLotNumber()
                    + ", " + input.getWeight()
                    + ", " + input.getPrice()
                    + ", '" + input.getTReception().toUpperCase()
                    + "', " + input.getNumHams()
                    + ", " + input.getNumPalettes()
                    + ", " + input.getDelete()
                    + ", '" + sdf.format((Date) input.getCreatedAt().getTime()) 
                    + "', '" + sdf.format((Date) input.getUpdatedAt().getTime())
                    + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private ArrayList<Input> resultSetToArray(Connection conn, ResultSet result) {
        ArrayList<Input> inputs = new ArrayList<Input>();
        try {
            while (result.next()) {
                Input input = new Input();
                input.setId(result.getInt("id"));
                
                BillManager bm = new BillManager();
                Bill bill = bm.find(conn, result.getInt("bill_id"));
                input.setBill(bill);
                
                input.setLotNumber(result.getInt("lot_number"));
                input.setWeight(result.getFloat("weight"));
                input.setPrice(result.getFloat("price"));
                input.setTReception(result.getString("t_reception"));
                input.setNumHams(result.getInt("num_hams"));
                input.setNumPalettes(result.getInt("num_palettes"));
                input.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("date_input")));
                    input.setDateInput(date);
                    date.setTime(sdf.parse(result.getString("created_at")));
                    input.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    input.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(InputManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                inputs.add(input);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputs;
    }

    public Response remove(Input input, Connection conn) {
        Response resp = new Response();
        resp.setCode(0);

        try {
            Statement st = conn.createStatement();
            resp.setResponse("Entrada eliminada correctamente");
            resp.setCode(st.executeUpdate("UPDATE Input "
                    + "SET delete = true "
                    + "WHERE id = " + input.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("La entrada no ha podido ser eliminada");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

}
