/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author araluce
 */
public class Client {
    
    private Model model;
    private BillManager bm;
    
    private int id;
    private String name;
    private String lastname1;
    private String lastname2;
    private String dni;
    private String phone;
    private Boolean delete;
    private ArrayList<Bill> bills;
    
    private Calendar created_at;
    private Calendar updated_at;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);

    /**
     * Client Constructor
     * 
     * @param model
     */
    public Client(Model model) {
        this.model = model;
        this.bm = new BillManager(model);
        
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
        this.bills = new ArrayList<Bill>();
        this.delete = false;
    }
    
    /**
     * Retorna el id del cliente
     *
     * @return String
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna el nombre del cliente
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retorna el primer apellido del cliente
     *
     * @return String
     */
    public String getLastname1() {
        return this.lastname1;
    }

    /**
     * Retorna el segundo apellido del cliente
     *
     * @return String
     */
    public String getLastname2() {
        return this.lastname2;
    }

    /**
     * Retorna el dni del cliente
     *
     * @return String
     */
    public String getDni() {
        return this.dni;
    }

    /**
     * Retorna el telefono del cliente
     *
     * @return int
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * Retorna si el cliente ha sido borrado
     *
     * @return boolean
     */
    public Boolean getDelete() {
        return this.delete;
    }
    
    /**
     * Retorna la fecha de creacion
     *
     * @return Calendar
     */
    public Calendar getCreatedAt() {
        return this.created_at;
    }
    
    /**
     * Retorna la fecha de actualizacion
     *
     * @return Calendar
     */
    public Calendar getUpdatedAt() {
        return this.updated_at;
    }
    
    /**
     * Retorna las facturas/albaranes del cliente
     *
     * @return ArrayList<Bills>
     */
    public ArrayList<Bill> getBills() {
        ArrayList<Bill> remoteBills = bm.findByClient(this, false);
        if(this.bills.size() != remoteBills.size())
            this.bills = remoteBills;
        return this.bills;
    }
    
    /**
     * Inserta el id de un cliente
     *
     * @return int
     */
    public int setId(int id) {
        return this.id = id;
    }

    /**
     * Inserta el nombre del cliente
     *
     * @param name
     */
    public String setName(String name) {
        return this.name = name;
    }

    /**
     * Inserta el primer apellido del cliente
     *
     * @param apellido1
     */
    public String setLastname1(String lastname1) {
        return this.lastname1 = lastname1;
    }

    /**
     * Inserta el segundo apellido del cliente
     *
     * @param lastname2
     */
    public String setLastname2(String lastname2) {
        return this.lastname2 = lastname2;
    }

    /**
     * Inserta el dni del cliente
     *
     * @param dni
     */
    public String setDni(String dni) {
        return this.dni = dni;
    }

    /**
     * Inserta el telefono del cliente
     *
     * @param phone
     */
    public String setPhone(String phone) {
        return this.phone = phone;
    }
    
    /**
     * Borra un cliente
     *
     * @return boolean
     */
    public Boolean setDelete(Boolean delete) {
        return this.delete = delete;
    }
    
    /**
     * Actualiza la fecha de creacion
     *
     * @return Calendar
     */
    public Calendar setCreatedAt(Calendar date) {
        return this.created_at = date;
    }
    
    /**
     * Actualiza la fecha de update
     *
     * @return Calendar
     */
    public Calendar setUpdatedAt(Calendar date) {
        return this.updated_at = date;
    }
    
    /**
     * Actializa las facturas/albaranes del cliente
     *
     * @return ArrayList<Bills>
     */
    public ArrayList<Bill> getBills(ArrayList<Bill> billCollection) {
        return this.bills = billCollection;
    }
    
    /**
     * Actializa las facturas/albaranes del cliente
     *
     * @return ArrayList<Bills>
     */
    public ArrayList<Bill> addBill(Bill bill) {
        this.bills.add(bill);
        
        return this.bills;
    }
    
    /**
     * Actializa las facturas/albaranes del cliente
     *
     * @return ArrayList<Bills>
     */
    public ArrayList<Bill> addBills(ArrayList<Bill> billCollection) {
        for(Bill bill: billCollection) {
            this.bills.add(bill);
        }
        
        return this.bills;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n"
                + "Nombre y apellidos: " + this.name + " " + this.lastname1 + " " + this.lastname2 + "\n"
                + "Dni: " + this.dni + "\n"
                + "Teléfono: " + this.phone + "\n"
                + "Creado el: " + sdf.format((Date) this.created_at.getTime()) + "\n"
                + "Actualizado el: " + sdf.format((Date) this.updated_at.getTime()) + "\n"
                + "Borrado: " + this.delete;
    }
    
    public boolean equalBills(ArrayList<Bill> bills){
        return false;
    }
}
