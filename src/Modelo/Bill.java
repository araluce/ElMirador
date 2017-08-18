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
 * Bill has many Unsubscribes
 */
public class Bill {
    
    private int id;
    private Client client;
    private Boolean delete;
    private ArrayList<Input> inputs;
    private ArrayList<Output> outputs;
    private ArrayList<Unsubscribe> unsubscribes;
    private Calendar created_at;
    private Calendar updated_at;
    
    public Bill(){
        this.client = null;
        this.delete = false;
        this.inputs = new ArrayList<Input>();
        this.outputs = new ArrayList<Output>();
        this.unsubscribes = new ArrayList<Unsubscribe>();
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
    
    public ArrayList<Output> getOutputs(){
        return this.outputs;
    }
    
    public ArrayList<Unsubscribe> getUnsubscribes(){
        return this.unsubscribes;
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
    
    public ArrayList<Output> setOutputs(ArrayList<Output> outputsCollection){
        return this.outputs = outputsCollection;
    }
    
    public ArrayList<Unsubscribe> setUnsubscribes(ArrayList<Unsubscribe> unsubscribesCollection){
        return this.unsubscribes = unsubscribesCollection;
    }
    
    public ArrayList<Input> addInputs(ArrayList<Input> inputsCollection){
        for(Input input: inputsCollection){
            this.inputs.add(input);
        }
        
        return this.inputs;
    }
    
    public ArrayList<Output> addOutputs(ArrayList<Output> outputsCollection){
        for(Output output: outputsCollection){
            this.outputs.add(output);
        }
        
        return this.outputs;
    }
    
    public ArrayList<Unsubscribe> addUnsubscribes(ArrayList<Unsubscribe> unsubscribesCollection){
        for(Unsubscribe unsubscribe: unsubscribesCollection){
            this.unsubscribes.add(unsubscribe);
        }
        
        return this.unsubscribes;
    }
    
    public ArrayList<Input> addInput(Input input){
        this.inputs.add(input);
        
        return this.inputs;
    }
    
    public ArrayList<Output> addOutput(Output output){
        this.outputs.add(output);
        
        return this.outputs;
    }
    
    public ArrayList<Unsubscribe> addUnsubscribe(Unsubscribe unsubscribe){
        this.unsubscribes.add(unsubscribe);
        
        return this.unsubscribes;
    }
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }
}
