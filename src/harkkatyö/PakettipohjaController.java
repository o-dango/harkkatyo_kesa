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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camilla
 */
public class PakettipohjaController implements Initializable {

    @FXML
    private TextField setItemName;
    @FXML
    private TextField setItemWeight;
    @FXML
    private TextField setItemSize;
    @FXML
    private ComboBox<?> selectGoalMachine;
    @FXML
    private ComboBox<?> selectStart;
    @FXML
    private ComboBox<?> selectStartMachine;
    @FXML
    private ComboBox<?> selectGoal;
    @FXML
    private ComboBox<?> selectItem;
    @FXML
    private RadioButton selectClassThree;
    @FXML
    private RadioButton selectClassTwo;
    @FXML
    private RadioButton selectCalssOne;
    @FXML
    private Button classInfoAction;
    @FXML
    private Button cancelParcel;
    @FXML
    private Button makeParcel;
    @FXML
    private CheckBox breakable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelParcelAction(ActionEvent event) {
    }

    @FXML
    private void makeParcelAction(ActionEvent event) {
    }

    @FXML
    private void selectGoalMachineAction(ActionEvent event) {
    }

    @FXML
    private void selectStartAction(ActionEvent event) {
    }

    @FXML
    private void selectStartMachineAction(ActionEvent event) {
    }

    @FXML
    private void selectGoalAction(ActionEvent event) {
    }

    @FXML
    private void selectItemAction(ActionEvent event) {
    }

    @FXML
    private void breakableAction(ActionEvent event) {
    }

    @FXML
    private void selectClassThreeAction(ActionEvent event) {
    }

    @FXML
    private void selectCalssTwoAction(ActionEvent event) {
    }

    @FXML
    private void selectCalssOneAction(ActionEvent event) {
    }

    @FXML
    private void classInfoAction(ActionEvent event) {
        try {
            Stage stage3 = new Stage();
            System.out.println("Näyttää postitusinfon");
            stage3.setTitle("info:D");
            Parent root3 = FXMLLoader.load(getClass().getResource("infopohja.fxml"));
            
            Scene scene = new Scene(root3);
            
            stage3.setScene(scene);
            stage3.show();
        } catch (IOException ex) {
            Logger.getLogger(IkkunapohjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
