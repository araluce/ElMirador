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
 */
public class Client {
    
    private int id;
    private String name;
    private String lastname1;
    private String lastname2;
    private String dni;
    private String phone;
    private Boolean delete;
    
    private Calendar created_at;
    private Calendar updated_at;

    /**
     * Client Constructor
     */
    public Client() {
        this.delete = false;
        
        this.created_at = Calendar.getInstance();
        this.updated_at = Calendar.getInstance();
    }
    
    /**
     * Retorna el id del cliente
     *
     * @return String
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna el nombre del cliente
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retorna el primer apellido del cliente
     *
     * @return String
     */
    public String getLastname1() {
        return this.lastname1;
    }

    /**
     * Retorna el segundo apellido del cliente
     *
     * @return String
     */
    public String getLastname2() {
        return this.lastname2;
    }

    /**
     * Retorna el dni del cliente
     *
     * @return String
     */
    public String getDni() {
        return this.dni;
    }

    /**
     * Retorna el telefono del cliente
     *
     * @return int
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * Retorna si el cliente ha sido borrado
     *
     * @return boolean
     */
    public Boolean getDelete() {
        return this.delete;
    }
    
    /**
     * Retorna la fecha de creacion
     *
     * @return Calendar
     */
    public Calendar getCreatedAt() {
        return this.created_at;
    }
    
    /**
     * Retorna la fecha de actualizacion
     *
     * @return Calendar
     */
    public Calendar getUpdatedAt() {
        return this.updated_at;
    }
    
    /**
     * Inserta el id de un cliente
     *
     * @return int
     */
    public int setId(int id) {
        return this.id = id;
    }

    /**
     * Inserta el nombre del cliente
     *
     * @param name
     */
    public String setName(String name) {
        return this.name = name;
    }

    /**
     * Inserta el primer apellido del cliente
     *
     * @param apellido1
     */
    public String setLastname1(String lastname1) {
        return this.lastname1 = lastname1;
    }

    /**
     * Inserta el segundo apellido del cliente
     *
     * @param lastname2
     */
    public String setLastname2(String lastname2) {
        return this.lastname2 = lastname2;
    }

    /**
     * Inserta el dni del cliente
     *
     * @param dni
     */
    public String setDni(String dni) {
        return this.dni = dni;
    }

    /**
     * Inserta el telefono del cliente
     *
     * @param phone
     */
    public String setPhone(String phone) {
        return this.phone = phone;
    }
    
    /**
     * Borra un cliente
     *
     * @return boolean
     */
    public Boolean setDelete(Boolean delete) {
        return this.delete = delete;
    }
    
    /**
     * Actualiza la fecha de creacion
     *
     * @return Calendar
     */
    public Calendar setCreatedAt(Calendar date) {
        return this.created_at = date;
    }
    
    /**
     * Actualiza la fecha de update
     *
     * @return Calendar
     */
    public Calendar setUpdatedAt(Calendar date) {
        return this.updated_at = date;
    }

    @Override
    public String toString() {
        return "Nombre y apellidos: " + this.name + " " + this.lastname1 + " " + this.lastname2 + "\n"
                + "Dni: " + this.dni + "\n"
                + "Teléfono: " + this.phone + "\n"
                + "Borrado: " + this.delete;
    }
}
