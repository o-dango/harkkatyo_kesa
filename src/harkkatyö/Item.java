/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.sql.SQLException;

/**
 *
 * @author camilla
 */
public class Item {
    
    static private Item i = null;
    Sqlite sql;
    
    private Item() {
        
        sql = Sqlite.getInstance();
        
    }
    
    static public Item getInstance() {
        
        if(i == null) {
            
            i = new Item();
            
        }
        
        return i;
        
    }
    
    public int createItem(int itemID, String breakable, String name, String size, String weight) {
        
        try {
            
            String statement1 = "INSERT INTO Esine(tuoteID, Särkyvä, Nimi) "
                    + "VALUES(" + itemID + ",'" + breakable + "','" + name + "');";

            String[] split = new String[3];
            split = size.split("\\*");

            String statement2 = "INSERT INTO Koko(Korkeus, Leveys, Syvyys, Paino, tuoteID) "
                    + "VALUES(" + Integer.parseInt(split[0]) + "," + Integer.parseInt(split[1]) 
                    + "," + Integer.parseInt(split[2]) + "," + Double.parseDouble(weight)
                    + "," + itemID + ");";

            sql.addData(statement1);
            sql.addData(statement2);
        
        return 1;
        
        } catch (SQLException | NumberFormatException ex) {
            
            System.out.println(ex.getMessage());
            return 0;
            
        }
        
    }
    
    public int removeItem(int itemID) {
        
        try {
            String statement = "DELETE FROM Esine "
                    + "WHERE tuoteID = " + itemID + ";";

            sql.deleteData(statement);

            statement = "DELETE FROM Koko "
                    + "WHERE tuoteID = " + itemID + ";";

            sql.addData(statement);
        
            return 1;
        
        } catch (SQLException | NumberFormatException ex) {
            return 0;
            
        }
        
    }
    
}
