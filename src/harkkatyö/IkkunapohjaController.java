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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button removeParcel;
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
        System.out.println(chooseParcel.getValue());
        
        
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
            
            webViewer.getEngine().executeScript("document.goToLocation('" + address + "','" + info + "','orange')");
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
        webViewer.getEngine().executeScript("document.deletePaths()");
    }

    @FXML
    private void sendParcelAction(ActionEvent event) {
        
        String temp;
        int parcelClass;
        int parcelID;
        double distance;
        ArrayList<String> coordinates = new ArrayList();
        sql = Sqlite.getInstance();
        System.out.println("Lähettää postipaketin");
        
        temp = chooseParcel.getValue();
        String[] split = temp.split("->");
        System.out.println(Arrays.toString(split));
        temp = split[1];
        split = split[0].split(":");
        System.out.println(Arrays.toString(split) + " " + temp);
        
        parcelID = Integer.parseInt(split[0].trim());
        
        String statement = "SELECT * FROM PakettiInfo WHERE "
                + "pakettiID = " + parcelID + ";";
        System.out.println(statement);
        ArrayList<String[]> results = sql.getParcelData(statement);
        
        temp = results.get(0)[4];
        parcelClass = Integer.parseInt(results.get(0)[2]);
        System.out.println(parcelClass);
        
        statement = "SELECT * FROM Sijainti WHERE "
                + "Nimi = '" + results.get(0)[3] + "';";
        //System.out.println(statement);
        
        results = sql.getCoordinateData(statement);
        coordinates.add(results.get(0)[1]);
        coordinates.add(results.get(0)[0]);
        
        statement = "SELECT * FROM Sijainti WHERE "
                + "Nimi = '" + temp + "';";
        //System.out.println(statement);
        
        results = sql.getCoordinateData(statement);
        coordinates.add(results.get(0)[1]);
        coordinates.add(results.get(0)[0]);
        
        System.out.println(coordinates.toString());
        
        distance = (double)webViewer.getEngine().executeScript("document.createPath(" + coordinates 
                + ",'blue'," + parcelClass + ")");
        
        statement = "DELETE FROM Paketti "
                + "WHERE pakettiID = " + parcelID + ";";
        try {
            sql.deleteData(statement);
        } catch (SQLException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chooseParcel.setPromptText("Valitse paketti");
        
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
        
    }
    
    public void readParcels() {
        
        chooseParcel.getItems().clear();
        int i = 0;
        String[] temp;
        String print;
        String statement = "Select * FROM PakettiInfo;";
        //sql = Sqlite.getInstance();
        ArrayList<String[]> results = sql.getParcelData(statement);
        
        while(results.size() > i) {
            
            temp = results.get(i);
            print = temp[0] + ": " + temp[1] + ": " + temp[3] + " -> " + temp[4];
            chooseParcel.getItems().add(print);
            i++;
            
        }
        
    }

    @FXML
    private void refreshParcels(MouseEvent event) {
        
        System.out.println("Päivittää listan paketeista");
        readParcels();
        
    }

    @FXML
    private void removeParcelAction(ActionEvent event) {
        
        Parcel p = Parcel.getInstance();
        
        String temp = chooseParcel.getValue();
        String[] split = temp.split("->");
        System.out.println(Arrays.toString(split));
        temp = split[1];
        split = split[0].split(":");
        System.out.println(Arrays.toString(split) + " " + temp);
        
        int parcelID = Integer.parseInt(split[0].trim());
        
        int check = p.removeParcel(parcelID);
        
        if (check == 0) {
            
            System.out.println("Virhe tietokannassa!");
            
        }
        
    }
    
}
