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
public class Cliente {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String telefono;

    /**
     * Constructor Cliente
     */
    public Cliente() {
    }

    /**
     * Retorna el nombre del cliente
     * @return String
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Retorna el primer apellido del cliente
     * @return String
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Retorna el segundo apellido del cliente
     * @return String
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Retorna el dni del cliente
     * @return String
     */
    public String getDni() {
        return dni;
    }

    /**
     * Retorna el telefono del cliente
     * @return int
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Inserta el nombre del cliente
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Inserta el primer apellido del cliente
     * @param apellido1 
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Inserta el segundo apellido del cliente
     * @param apellido2 
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Inserta el dni del cliente
     * @param dni 
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Inserta el telefono del cliente
     * @param telefono 
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString(){
        return "Nombre y apellidos: " + this.nombre + " " + this.apellido1 + " " + this.apellido2 + "\n"
                + "Dni: " + this.dni + "\n"
                + "Teléfono: " + this.telefono;
    }
}
