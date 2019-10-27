/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grayhat;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 *
 * @author GrayHat
 */
public class Backend {
    
    HttpResponse<JsonNode> response;
    String url="https://ide.geeksforgeeks.org/main.php";
    String language,code,input;
    
    Backend(String lang,String code,String input)
    {
        this.language=lang;
        this.code=code;
        this.input=input;
    }
    
    Map<String,String> run()
    {
        Map<String,String> resp=new HashMap<>();
        try{
        this.response = Unirest.post(this.url)
                .header("Origin","https://ide.geeksforgeeks.org")
                .field("lang", this.language)
                .field("code", this.code)
                .field("input",this.input)
                .field("save", "false")
                .asJson();
        resp.put("cmpError", ""+this.response.getBody().getObject().get("cmpError"));
        resp.put("compResult", ""+this.response.getBody().getObject().get("compResult"));
        resp.put("hash", ""+this.response.getBody().getObject().get("hash"));
        resp.put("id", ""+this.response.getBody().getObject().get("id"));
        resp.put("lxcOutput", ""+this.response.getBody().getObject().get("lxcOutput"));
        resp.put("memory", ""+this.response.getBody().getObject().get("memory"));
        resp.put("output", ""+this.response.getBody().getObject().get("output"));
        resp.put("rntError", ""+this.response.getBody().getObject().get("rntError"));
        resp.put("time", ""+this.response.getBody().getObject().get("time"));
        resp.put("valid", ""+this.response.getBody().getObject().get("valid"));
        }
        catch(Exception err)
        {
            resp.put("Error", "error");
            JOptionPane.showMessageDialog(new JPanel(), "Some error occured parsing : \n"+err, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }
}
