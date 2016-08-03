/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author camilla
 */
public class Parcel{
    
    static private Parcel p = null;
    public Sqlite sql;
    
    private  Parcel() {
        
         sql = Sqlite.getInstance();
        
    }
    
    static public Parcel getInstance() {
        
        if(p == null) {
            
            p = new Parcel();
            
        }
        
        return p;
        
    }
    
    public int createParcel(int itemID, int parcelClass, String startmachine, String endmachine) {
        
        String statement = "SELECT * FROM Tuotetiedot "
                + "WHERE tuoteID = " + itemID + ";";
        ArrayList<String[]> results = sql.getItemData(statement);
        String[] temp = results.get(0);
        int d = Integer.parseInt(temp[3]);
        int w = Integer.parseInt(temp[4]);
        int h = Integer.parseInt(temp[5]);
        double g = Double.parseDouble(temp[6]);
        
        try {
            
            if(parcelClass == 1) {

                if(temp[2].equals("True")) {

                    return 0;

                }

                else if(d > 200 || w > 200 || h > 200 || g > 20) {

                    return 0;

                }

                else {

                    statement = "INSERT INTO Paketti(tuoteID, Luokka, Lähtö, Maali) "
                    + "VALUES(" + itemID + "," + parcelClass + ",'"
                            + startmachine + "','" + endmachine + "');";

                    sql.addData(statement);
                    return 1;


                }


            }

            else if(parcelClass == 2) {

                if(d > 100 || w > 100 || h > 100 || g > 20) {

                    return 0;

                }

                else {

                    statement = "INSERT INTO Paketti(tuoteID, Luokka, Lähtö, Maali) "
                    + "VALUES(" + itemID + "," + parcelClass + ",'"
                            + startmachine + "','" + endmachine + "');";

                    sql.addData(statement);
                    return 1;


                }


            }

            if(parcelClass == 3) {

                if(temp[2].equals("True")) {

                    return 0;

                }

                else {

                    statement = "INSERT INTO Paketti(tuoteID, Luokka, Lähtö, Maali) "
                    + "VALUES(" + itemID + "," + parcelClass + ",'"
                            + startmachine + "','" + endmachine + "');";

                    sql.addData(statement);
                    return 1;


                }


            }
            
        } catch(Exception ex) {
        
        return 2;
        
        }
        
        return 2;
        
    }
    
    public int removeParcel(int parcelID) {
        
        try {
            String statement = "DELETE FROM Paketti "
                    + "WHERE pakettiID = " + parcelID + ";";

            sql.deleteData(statement);
        
            return 1;
        
        } catch (SQLException | NumberFormatException ex) {
            //Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        }
        
    }
    
}
