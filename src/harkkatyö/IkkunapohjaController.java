/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author camilla
 */
public class IkkunapohjaController implements Initializable {

    @FXML
    private WebView webViewer;
    @FXML
    private ComboBox<?> chooseParcel;
    @FXML
    private Button addToMap;
    @FXML
    private ComboBox<?> chooseMachine;
    @FXML
    private Button clearRoutes;
    @FXML
    private Button sendParcel;
    @FXML
    private Button addNewParcel;
    
    private readWeb rw;
    private webReader names;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            rw = new readWeb("http://smartpost.ee/fi_apt.xml");
            String content = rw.getContent();
            names = new webReader(content);
            webViewer.getEngine().load(getClass().getResource("index.html").toExternalForm());
            
        } catch (IOException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int i = 0;
        while(i < names.getMap().size()) {
            System.out.println((String) names.getMap().keySet().toArray()[i]);
            i++;
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
        System.out.println("Lisää uuden postipaketin");
    }
    
}
