/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author camilla
 */
public class PakettipohjaController implements Initializable {

    @FXML
    private Button cancelParcelAction;
    @FXML
    private Button makeParcelAction;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
