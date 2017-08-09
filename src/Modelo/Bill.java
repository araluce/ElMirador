/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

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
    private ArrayList<Up> ups;
    
    public Bill(){
        this.client = null;
        this.ups = new ArrayList<Up>();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public ArrayList<Up> getUps(){
        return this.ups;
    }
    
    public Boolean getDelete(){
        return this.delete;
    }
    
    public int setId(int id){
        return this.id = id;
    }
    
    public Client setClient(Client client){
        return this.client = client;
    }
    
    public ArrayList<Up> setUps(ArrayList<Up> upsCollection){
        return this.ups = upsCollection;
    }
    
    public ArrayList<Up> addUps(ArrayList<Up> upsCollection){
        for(Up up: upsCollection){
            this.ups.add(up);
        }
        
        return this.ups;
    }
    
    public ArrayList<Up> addUp(Up up){
        this.ups.add(up);
        
        return this.ups;
    }
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }
}
