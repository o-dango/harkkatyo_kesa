/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camilla
 */
public class IkkunapohjaController implements Initializable {

    @FXML
    private WebView webViewer;
    @FXML
    private ComboBox<String> chooseParcel;
    @FXML
    private Button addToMap;
    @FXML
    private ComboBox<String> chooseMachine;
    @FXML
    private Button clearRoutes;
    @FXML
    private Button sendParcel;
    @FXML
    private Button addNewParcel;
    
    private readWeb rw;
    private webReader names;
    Sqlite sql = Sqlite.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            rw = new readWeb("http://smartpost.ee/fi_apt.xml");
            String content = rw.getContent();
            //names = new webReader(content); //tietokantaan kirjoittamista varten
            webViewer.getEngine().load(getClass().getResource("index.html").toExternalForm());
            
        } catch (IOException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //readParcels();
        
        String statement = "SELECT * FROM Postiautomaatti;";
        ArrayList results = sql.getCityData(statement);
        String temp;
        
        //System.out.println(results);
        int i = 0;
        
        while(results.size() > i) {
            
            temp = results.get(i).toString().toUpperCase();
            if(i == 0) {
                chooseMachine.getItems().add(temp);
                
            }
            else if(chooseMachine.getItems().get(chooseMachine.getItems().size()-1).equals(temp)) {
                //continue
                
            }
            else {
            
                chooseMachine.getItems().add(temp);
                
            }
            i++;
            
        }
        
    }    

    @FXML
    private void getParcelAction(ActionEvent event) {
        System.out.println("Valitsee paketin");
        
    }

    @FXML
    private void addToMapAction(ActionEvent event) {
        String address;
        String info;
        String[] temp;
        ArrayList<String[]> results = new ArrayList();
        int i = 0;
        //webViewer.getEngine().executeScript("document.goToLocation('" + address + "','" + info + "','blue')");
        
        String statement = "SELECT * FROM Automaatit "
                + "WHERE Kaupunki = '" + chooseMachine.getValue() + "';";
        System.out.println(statement);
        
        results = sql.getAddressData(statement);
        System.out.println(results.size());
        
        while(results.size() > i) {
            
            temp = results.get(i);
            System.out.println(Arrays.toString(temp));
            address = temp[0] + ", " + temp[1] + " " + temp[2]; //osote, postinro, kaupunki
            info = address + " " + temp[3] + " " + temp[4]; //koko osote, nimi, aukioloaika
            
            webViewer.getEngine().executeScript("document.goToLocation('" + address + "','" + info + "','blue')");
            i++;
            
        }
        
    }

    @FXML
    private void chooseMachineAction(ActionEvent event) {
        System.out.println("Valitsee postimaatit");
        
    }

    @FXML
    private void clearRoutesAction(ActionEvent event) {
        System.out.println("tyhjentää kaikki reitit");
    }

    @FXML
    private void sendParcelAction(ActionEvent event) {
        System.out.println("Lähettää postipaketin");
    }

    @FXML
    private void addNewParcelAction(ActionEvent event) {
        try {
            Stage stage2 = new Stage();
            System.out.println("Lisää uuden postipaketin");
            stage2.setTitle("Paketti:D");
            Parent root2 = FXMLLoader.load(getClass().getResource("pakettipohja.fxml"));
            
            Scene scene = new Scene(root2);
            
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //readParcels();
        
    }
    
    public void readParcels() {
        
        int i = 0;
        String[] temp;
        String print;
        String statement = "Select * FROM PakettiInfo;";
        //sql = Sqlite.getInstance();
        ArrayList<String[]> results = sql.getParcelData(statement);
        
        while(results.size() > i) {
            
            temp = results.get(i);
            print = temp[0] + ", " + temp[2] + " -> " + temp[3];
            chooseParcel.getItems().add(print);
            
        }
        
    }
    
}
