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
//            names = new webReader(content); tietokantaan kirjoittamista varten
            webViewer.getEngine().load(getClass().getResource("index.html").toExternalForm());
            
        } catch (IOException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String statement = "SELECT * FROM Postiautomaatti;";
        ArrayList results = sql.getCityData(statement);
        String temp;
        
        System.out.println(results);
        int i = 0;
        
        while(results.size() > i) {
            
            temp = results.get(i).toString().toUpperCase();
            System.out.println(temp);
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
            System.out.println(i);
            
        }
        
    }    

    @FXML
    private void getParcelAction(ActionEvent event) {
        System.out.println("Valitsee paketin");
        
    }

    @FXML
    private void addToMapAction(ActionEvent event) {
        String address = "Hiihtomäentie 6 C 30, 50160 Mikkeli";
        String info = "Täällä on koti :D";
        webViewer.getEngine().executeScript("document.goToLocation('" + address + "','" + info + "','blue')");
    }

    @FXML
    private void chooseMachineAction(ActionEvent event) {
        System.out.println("Valitsee postimaatin");
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
    }
    
}
