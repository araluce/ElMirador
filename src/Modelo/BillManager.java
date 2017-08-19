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
import java.util.Date;
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

    private Model model;
    private Connection conn;
    private SimpleDateFormat sdf;

    /**
     * Constructor de BillManager
     */
    public BillManager(Model model) {
        this.model = model;
        this.conn = model.getConnection();
        this.sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);
    }

    /* Find bill by id
     *
     * @param id
     * @return Bill|null
     */
    public Bill find(int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Bill WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Bill bill = new Bill(model);
                bill.setId(result.getInt("id"));
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

                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /**
     * Find bill by Client
     * @param client
     * @param all
     * @return 
     */
    public ArrayList<Bill> findByClient(Client client, boolean all) {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            if (all) {
                result = st.executeQuery("SELECT * FROM Bill WHERE client_id LIKE '" + client.getId() + "';");
            } else {
                result = st.executeQuery("SELECT * FROM Bill WHERE client_id LIKE '" + client.getId() + "' AND delete != false;");
            }

            billList = resultSetToArray(result);

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return billList;
    }
    
    /**
     * Find one bill by input
     * @param input
     * @return 
     */
    public Bill findOneByInput(Input input) {
        Bill bill = new Bill(model);
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT bill_id FROM Input WHERE id = " + input.getId() + ";");
            if (result.next()) {
                bill = find(result.getInt("bill_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bill;
    }
    
    /**
     * Find one bill by output
     * @param output
     * @return 
     */
    public Bill findOneByOutput(Output output) {
        Bill bill = new Bill(model);
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT bill_id FROM Output WHERE id = " + output.getId() + ";");
            if (result.next()) {
                bill = find(result.getInt("bill_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bill;
    }
    
    /**
     * Find one bill by unsubscribe
     * @param unsubscribe
     * @return 
     */
    public Bill findOneByUnsubscribe(Unsubscribe unsubscribe) {
        Bill bill = new Bill(model);
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT bill_id FROM Unsubscribe WHERE id = " + unsubscribe.getId() + ";");
            if (result.next()) {
                bill = find(result.getInt("bill_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bill;
    }

    /**
     * Find all bills
     *
     * @param all
     * @return ArrayList<Bill>|null
     */
    public ArrayList<Bill> findAll(boolean all) {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            if (all) {
                result = st.executeQuery("SELECT * FROM Bill;");
            } else {
                result = st.executeQuery("SELECT * FROM Bill WHERE delete != false;");
            }
            billList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return billList;
    }

    /**
     * Make a Bill array with a given resulset
     * @param result
     * @return 
     */
    private ArrayList<Bill> resultSetToArray(ResultSet result) {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        try {
            while (result.next()) {
                Bill bill = new Bill(model);
                bill.setId(result.getInt("id"));

                ClientManager cm = new ClientManager(model);
                Client client = cm.find(result.getInt("client_id"));
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

                billList.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return billList;
    }

    /**
     * Check a given bill as removed
     * @param bill
     * @return 
     */
    public Response remove(Bill bill) {
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

    /**
     * Insert a new bill into a Bill DB table
     *
     * @param bill
     * @return int
     */
    public int flush(Bill bill) {
        if (bill.getId() == 0)
            return save(bill);
        return update(bill);
    }
    
    public int save(Bill bill){
        int result = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO Bill ("
                    + "client_id, delete, "
                    + "created_at, updated_at) "
                    + "VALUES ("
                    + "" + bill.getClient().getId()
                    + ", " + bill.getDelete()
                    + ", '" + sdf.format((Date) bill.getCreatedAt().getTime())
                    + "', '" + sdf.format((Date) bill.getUpdatedAt().getTime())
                    + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int update(Bill bill){
        int result = 0;
        
        return result;
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
            result = st.executeQuery("SELECT MAX(id) as last_id FROM Bill");
            if (result.next()) {
                last_id = result.getInt("last_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return last_id;
    }
}
