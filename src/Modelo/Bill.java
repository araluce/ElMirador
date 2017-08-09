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
 * Bill has many Inputs
 * Bill has many Outputs
 */
public class Bill {
    
    private int id;
    private Client client;
    private Boolean delete;
    private ArrayList<Input> inputs;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Bill(){
        this.client = null;
        this.inputs = new ArrayList<Input>();
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
    }
    
    public int getId(){
        return this.id;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    public ArrayList<Input> getInputs(){
        return this.inputs;
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
    
    public ArrayList<Input> setInputs(ArrayList<Input> inputsCollection){
        return this.inputs = inputsCollection;
    }
    
    public ArrayList<Input> addInputs(ArrayList<Input> inputsCollection){
        for(Input up: inputsCollection){
            this.inputs.add(up);
        }
        
        return this.inputs;
    }
    
    public ArrayList<Input> addInput(Input input){
        this.inputs.add(input);
        
        return this.inputs;
    }
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }
}
