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
    private int code;
    private String response;
    
    public Response(){
        this.code = 0;
        this.response = "";
    }
    
    public void setCode(int code){
        this.code = code;
    }
    
    public void setResponse(String response){
        this.response = response;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getResponse(){
        return this.response;
    }
}
