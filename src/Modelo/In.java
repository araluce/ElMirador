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
public class In {
    
    private int id;
    private Bill bill;
    private int lot_number;
    private float weight;
    private double price;
    private String t_reception;
    private int num_hams;
    private int num_palettes;
    private Boolean delete;
    private Calendar created_at;
    private Calendar updated_at;
    
    public In(){
        this.bill = null;
        this.lot_number = 0;
        this.weight = 0;
        this.price = 0;
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
    
    public Boolean setDelete(Boolean delete){
        return this.delete = delete;
    }

    public Calendar setCreatedAt(Calendar created_at) {
        return this.created_at = created_at;
    }

    public Calendar setUpdatedAt(Calendar updated_at) {
        return this.updated_at = updated_at;
    }

    public void setLotNumber(int lot_number) {
        this.lot_number = lot_number;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTReception(String t_reception) {
        this.t_reception = t_reception;
    }

    public void setNumHams(int num_hams) {
        this.num_hams = num_hams;
    }

    public void setNumPalettes(int num_palettes) {
        this.num_palettes = num_palettes;
    }
    
}
