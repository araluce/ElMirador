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
 * Bill belongs to Bill
 */
public class Unsubscribe {
    
    private int id;
    private Bill bill;
    private Calendar date_unsubscribe;
    private int num_hams_unsubscribes;
    private int num_palettes_unsubscribes;
    private String reason;
    private String observations;
    private Boolean delete;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Unsubscribe(){
        this.bill = null;
        this.date_unsubscribe = Calendar.getInstance();
        this.num_hams_unsubscribes = 0;
        this.num_palettes_unsubscribes = 0;
        this.delete = false;
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Bill getBill(){
        return this.bill;
    }
    
    public Calendar getDateUnsubscribe(){
        return this.date_unsubscribe;
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

    public int getNumHamsUnsubscribes() {
        return this.num_hams_unsubscribes;
    }
    
    public int getNumPalettesUnsubscribes() {
        return this.num_palettes_unsubscribes;
    }

    public String getObservations() {
        return this.observations;
    }

    public String getReason() {
        return this.reason;
    }
    
    public int setId(int id){
        return this.id = id;
    }
    
    public Bill setBill(Bill bill){
        return this.bill = bill;
    }
    
    public Calendar setDateUnsubscribe(Calendar date_unsubscribe){
        return this.date_unsubscribe = date_unsubscribe;
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

    public int setNumHamsUnsubscribes(int num_unsubscribes) {
        return this.num_hams_unsubscribes = num_unsubscribes;
    }
    
    public int setNumPalettesUnsubscribes(int num_unsubscribes) {
        return this.num_palettes_unsubscribes = num_unsubscribes;
    }

    public String setObservations(String observations) {
        return this.observations = observations;
    }

    public String setReason(String reason) {
        return this.reason = reason;
    }
}
