/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> selectGoalMachine;
    @FXML
    private ComboBox<String> selectStart;
    @FXML
    private ComboBox<String> selectStartMachine;
    @FXML
    private ComboBox<String> selectGoal;
    @FXML
    private ComboBox<String> selectItem;
    @FXML
    private RadioButton selectClassThree;
    @FXML
    private RadioButton selectClassTwo;
    @FXML
    private Button classInfoAction;
    @FXML
    private Button cancelParcel;
    @FXML
    private Button makeParcel;
    @FXML
    private CheckBox breakable;
    @FXML
    private RadioButton selectClassOne;
    @FXML
    private ToggleGroup classSelection;
    @FXML
    private Button createItem;
    @FXML
    private Label infoText;
    
    private Sqlite sql = Sqlite.getInstance();
    private ArrayList<String[]> results = new ArrayList();
    private int parcelClass;
    private String isBreakable = "False";
    private String startmachine;
    private String endmachine;
    private String startcity;
    private String endcity;
    @FXML
    private Button removeItem;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        readItems();
        
    }    

    @FXML
    private void cancelParcelAction(ActionEvent event) {
        
        Stage scene = (Stage) cancelParcel.getScene().getWindow();
        scene.close();
        
    }

    @FXML
    private void makeParcelAction(ActionEvent event) {
        
        Parcel p = Parcel.getInstance();
        int itemID = selectItem.getItems().indexOf(selectItem.getValue()) + 1;
        /*String statement = "INSERT INTO Paketti(tuoteID, Luokka, Lähtö, Maali) "
                + "VALUES (" + itemID + "," + parcelClass 
                + ",'" + startmachine + "','" + endmachine + "');";
        sql = Sqlite.getInstance();
        sql.addData(statement);*/
        int check = p.createParcel(itemID, parcelClass, startmachine, endmachine);
        if(check == 0) {
            infoText.setText("Paketin luomisessa tapahtui virhe!\n"
                    + "Tarkista onko esine sopiva valittuun toimitusluokkaan.");
        }
        else if(check == 2) {
            infoText.setText("Virhe tietojärjestelmässä!");
        }
        else if(startmachine.equals(endmachine)) {
            infoText.setText("Paketin luomisessa tapahtui virhe!\n"
                    + "Tarkista lähtö- ja pääteautomaatti.");
        }
        else {
            Stage scene = (Stage) makeParcel.getScene().getWindow();
            scene.close();
        }
        
    }

    @FXML
    private void selectGoalMachineAction(ActionEvent event) {
        
        endmachine = selectGoalMachine.getValue();
        
    }

    @FXML
    private void selectStartAction(ActionEvent event) {
        
        startcity = selectStart.getValue();
        int i = 0;
        String statement = "SELECT * FROM Automaatit "
                + "WHERE kaupunki ='" + startcity + "';";
        ArrayList<String[]> machines = sql.getAddressData(statement);
        
        while(machines.size() > i) {
            
            selectStartMachine.getItems().add(machines.get(i)[3]);
            i++;
            
        }
        
    }

    @FXML
    private void selectStartMachineAction(ActionEvent event) {
        
        startmachine = selectStartMachine.getValue();
        
    }

    @FXML
    private void selectGoalAction(ActionEvent event) {
        
        endcity = selectGoal.getValue();
        int i = 0;
        String statement = "SELECT * FROM Automaatit "
                + "WHERE kaupunki ='" + endcity + "';";
        ArrayList<String[]> machines = sql.getAddressData(statement);
        
        while(machines.size() > i) {
            
            selectGoalMachine.getItems().add(machines.get(i)[3]);
            i++;
            
        }
        
    }

    @FXML
    private void selectItemAction(ActionEvent event) {
        
    }

    @FXML
    private void breakableAction(ActionEvent event) {
        
        if (breakable.isSelected() == false) {
            
            isBreakable = "False";
            System.out.println("Esine on rikkoutuva: " + isBreakable);
            
        }
        
        else {
            
            isBreakable = "True";
            System.out.println("Esine on rikkoutuva: " + isBreakable);
            
        }
        
    }

    @FXML
    private void selectClassThreeAction(ActionEvent event) {
        
        parcelClass = 3;
        System.out.println("Tämänhetkinen pakettiluokka: " + parcelClass);
        
    }
    
    @FXML
    private void selectClassTwoAction(ActionEvent event) {
        
        parcelClass = 2;
        System.out.println("Tämänhetkinen pakettiluokka: " + parcelClass);
        
    }

    @FXML
    private void selectClassOneAction(ActionEvent event) {
        
        parcelClass = 1;
        System.out.println("Tämänhetkinen pakettiluokka: " + parcelClass);
        
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
            sql.closeDatabase();
        }
        
    }

    @FXML
    private void createItemAction(ActionEvent event) {
        
        Item item = Item.getInstance();
        int check;
        int itemID = (selectItem.getItems().size() + 1);
        
        check = item.createItem(itemID, isBreakable, setItemName.getText(), 
                setItemSize.getText(), setItemWeight.getText());
        
        if(check == 0) {
            
            infoText.setText("Esineen luomisessa tapahtui virhe!\n"
                    + "Tarkasta syötetyt tiedot.");
        }
        
        else {
            
            infoText.setText("Luotiin esine: " + setItemName.getText());
            
        }
        
    }
    
    public void readItems() {
        
        selectItem.getItems().clear();
        int i = 0;
        String[] temp = new String[7];
        String statement = "SELECT * FROM Tuotetiedot;";
        System.out.println(statement);
        results = sql.getItemData(statement);
        
        while(results.size() > i) {
            
            temp = results.get(i);
            selectItem.getItems().add(temp[0] + ": " + temp[1]);
            i++;
            
        }
        
        statement = "SELECT kaupunki FROM Automaatit;";
        ArrayList<String> citys = sql.getCityData(statement);
        i = 0;
        while(citys.size() > i) {
            
            if(i == 0) {
                
                selectStart.getItems().add(citys.get(i));
                selectGoal.getItems().add(citys.get(i));
            
            }
            
            else if(selectStart.getItems().get(selectStart.getItems().size()-1).equals(citys.get(i))) {
                //continue
                
            }
            
            else {
                
                selectStart.getItems().add(citys.get(i));
                selectGoal.getItems().add(citys.get(i));
                
            }
            
            i++;
            
        }
        
    }

    @FXML
    private void getItemsAction(MouseEvent event) {
        
        readItems();
        
    }

    @FXML
    private void removeItemAction(ActionEvent event) {
        
        Item item = Item.getInstance();
        int id;
        int check;
        String temp = selectItem.getValue();
        String[] split = temp.split(":");
        id = Integer.parseInt(split[0].trim());
        check = item.removeItem(id);
        
        if (check == 0) {
            
            infoText.setText("Virhe esineen poistamisessa! \n"
                    + "Yritä uudelleen.");
        }
        
        else {
            
            infoText.setText("Poistettiin esine: " + split[1].trim());
            
        }
        
    }
    
}
