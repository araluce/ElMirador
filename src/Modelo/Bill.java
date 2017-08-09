/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author araluce
 * 
 * Bill belongs to Client
 * Bill has many Ups
 * Bill has many Downs
 */
public class Bill {
    
    private int id;
    private Client client;
    private Boolean delete;
    private ArrayList<In> ups;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Bill(){
        this.client = null;
        this.ups = new ArrayList<In>();
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public ArrayList<In> getUps(){
        return this.ups;
    }
    
    public Boolean getDelete(){
        return this.delete;
    }
    
    public Calendar getCreatedAt(){
        return this.created_at;
    }
    
    public Calendar getUpdatedAt(){
        return this.updated_at;
    }
    
    public int setId(int id){
        return this.id = id;
    }
    
    public Calendar setCreatedAt(Calendar created_at){
        return this.created_at = created_at;
    }
    
    public Calendar setUpdatedAt(Calendar updated_at){
        return this.updated_at = updated_at;
    }
    
    public Client setClient(Client client){
        return this.client = client;
    }
    
    public ArrayList<In> setUps(ArrayList<In> upsCollection){
        return this.ups = upsCollection;
    }
    
    public ArrayList<In> addUps(ArrayList<In> upsCollection){
        for(In up: upsCollection){
            this.ups.add(up);
        }
        
        return this.ups;
    }
    
    public ArrayList<In> addUp(In up){
        this.ups.add(up);
        
        return this.ups;
    }
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }
}
