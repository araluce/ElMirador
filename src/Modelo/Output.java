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
 * Output belongs to Bill
 * Output belongs to Client
 */
public class Output {
    
    private int id;
    private Bill bill;
    private Client client;
    private Calendar date_output;
    private Calendar pref_consumption;
    private String destination;
    private String tagged;
    private int num_hams;
    private int num_palettes;
    private Boolean delete;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Output(){
        this.bill = null;
        this.client = null;
        this.date_output = Calendar.getInstance();
        this.num_hams = 0;
        this.num_palettes = 0;
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Bill getBill(){
        return this.bill;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public Calendar getDateOutput(){
        return this.date_output;
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

    public Calendar getPrefConsuption() {
        return pref_consumption;
    }

    public String getDestination() {
        return destination;
    }

    public String getTagged() {
        return tagged;
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
    
    public Client setClient(Client client){
        return this.client = client;
    }
    
    public Calendar setDateOutput(Calendar date_input){
        return this.date_output = date_input;
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

    public Calendar setPrefConsuption(Calendar pref_consumption) {
        return this.pref_consumption = pref_consumption;
    }

    public String setDestination(String destination) {
        return this.destination = destination;
    }

    public String setTagged(String tagged) {
        return this.tagged = tagged;
    }

    public int setNumHams(int num_hams) {
        return this.num_hams = num_hams;
    }

    public int setNumPalettes(int num_palettes) {
        return this.num_palettes = num_palettes;
    }
    
}
