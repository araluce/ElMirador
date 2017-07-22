/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author araluce
 */
public class Bill {
    
    private int id;
    private Client cliente;
    private int jamones;
    private int paletas;
    private float precio;
    private String t_recepcion;
    
    public Bill(){
        this.cliente = null;
        this.jamones = 0;
        this.paletas = 0;
    }
}
