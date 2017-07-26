/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
    
    public Bill(){
        this.client = null;
    }
    
    public int getId(){
        return this.id;
    }
    
    public Client getClient(){
        return this.client;
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
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }
}
