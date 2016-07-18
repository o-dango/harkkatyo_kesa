/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author camilla
 */
public class readWeb {
    
    private String content = "";
    private String line;
    
    public readWeb(String web) throws MalformedURLException, IOException {
    
        URL url = new URL(web);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        
        while((line = br.readLine()) != null) {
            content += line + "\n";
        }
        
    }
    
    public String getContent() {
        
        //System.out.println(content);
        return content;
        
    }
    
}
