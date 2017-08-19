/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Calendar;

/**
 *
 * @author araluce
 * 
 * Bill belongs to Client
 * Bill has many Ups
 * Bill has many Downs
 */
public class Input {
    
    private Model model;
    private BillManager bm;
    
    private int id;
    private Bill bill;
    private Calendar date_input;
    private int lot_number;
    private float weight;
    private float price;
    private String t_reception;
    private int num_hams;
    private int num_palettes;
    private Boolean delete;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Input(Model model){
        this.model = model;
        this.bm = new BillManager(model);
        
        this.bill = null;
        this.date_input = Calendar.getInstance();
        this.lot_number = 0;
        this.weight = 0;
        this.price = 0;
        this.num_hams = 0;
        this.num_palettes = 0;
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
        this.delete = false;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Bill getBill(){
        if(this.bill == null)
            this.bill = bm.findOneByInput(this);
        return this.bill;
    }
    
    public Calendar getDateInput(){
        return this.date_input;
    }
    
    public Boolean getDelete(){
        return this.delete;
    }

    public Calendar getCreatedAt() {
        return created_at;
    }

    public Calendar getUpdatedAt() {
        return updated_at;
    }

    public int getLotNumber() {
        return lot_number;
    }

    public float getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public String getTReception() {
        return t_reception;
    }

    public int getNumHams() {
        return num_hams;
    }

    public int getNumPalettes() {
        return num_palettes;
    }
    
    public int setId(int id){
        return this.id = id;
    }
    
    public Bill setBill(Bill bill){
        return this.bill = bill;
    }
    
    public Calendar setDateInput(Calendar date_input){
        return this.date_input = date_input;
    }
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }

    public Calendar setCreatedAt(Calendar created_at) {
        return this.created_at = created_at;
    }

    public Calendar setUpdatedAt(Calendar updated_at) {
        return this.updated_at = updated_at;
    }

    public int setLotNumber(int lot_number) {
        return this.lot_number = lot_number;
    }

    public float setWeight(float weight) {
        return this.weight = weight;
    }

    public float setPrice(float price) {
        return this.price = price;
    }

    public String setTReception(String t_reception) {
        return this.t_reception = t_reception;
    }

    public int setNumHams(int num_hams) {
        return this.num_hams = num_hams;
    }

    public int setNumPalettes(int num_palettes) {
        return this.num_palettes = num_palettes;
    }
    
    public String toString(){
        return "id: " + getId() + "\n"
                + "bill_id: " + getBill().getId()+ "\n"
                + "t_recepction: " + getTReception()+ "\n"
                + "lot number: " + getLotNumber()+ "\n"
                + "number of hams: " + getNumHams()+ "\n"
                + "number of palettes: " + getNumPalettes()+ "\n"
                + "price: " + getPrice()+ "\n"
                + "weight: " + getWeight()+ "\n"
                + "delete: " + getDelete()+ "\n"
                + "created at: " + getCreatedAt()+ "\n"
                + "updated at: " + getUpdatedAt()+ "\n"
                + "";
    }
    
}
