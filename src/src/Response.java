/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author araluce
 */
public class Response {
    private int id;
    private int code;
    private String response;
    
    public Response(){
        this.id = 0;
        this.code = 0;
        this.response = "";
    }
    
    public int setId(int id){
        return this.id = id;
    }
    public int setCode(int code){
        return this.code = code;
    }
    
    public String setResponse(String response){
        return this.response = response;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getResponse(){
        return this.response;
    }
}
