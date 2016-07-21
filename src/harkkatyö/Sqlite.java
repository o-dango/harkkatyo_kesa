/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camilla
 */
public class Sqlite {
    
    static private Sqlite sql = null;
    private Connection c = null;
    
    private Sqlite() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tietokanta_2.sqlite3");
            c.setAutoCommit(false);
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    
    static public Sqlite getInstance() {
        
        if(sql == null) {
            sql = new Sqlite();
        }
        
        return sql;
    }
    
    public void addData(String statement) {
        
        Statement stmt = null;
        
        try {
            
            c = DriverManager.getConnection("jdbc:sqlite:tietokanta_2.sqlite3");
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            System.out.println(statement);
            stmt.executeUpdate(statement);
            
            stmt.close();
            c.commit();
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteData(String statement) {
        
        System.out.println("Deleting data~");
        
    }
    
    public ArrayList getCityData(String statement) {
        
        System.out.println("Getting data~");
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList table = new ArrayList<>();
        String temp;
        
        try {
            
            c = DriverManager.getConnection("jdbc:sqlite:tietokanta_2.sqlite3");
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            System.out.println(statement);
            rs = stmt.executeQuery(statement);
            int i = 1;
            while(rs.next()) {
                
                table.add(rs.getString("kaupunki"));
                i++;
            
            }
            
            rs.close();
            stmt.close();
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return table;
        
    }
    
    public ArrayList getCoordinateData(String statement) {
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList table = new ArrayList<>();
        
        try {
            
            c = DriverManager.getConnection("jdbc:sqlite:tietokanta_2.sqlite3");
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            System.out.println(statement);
            rs = stmt.executeQuery(statement);
            while(rs.next()) {
                
                String[] temp = new String[2];
                
                temp[0] = rs.getString("pituus");
                temp[1] = rs.getString("leveys");
                table.add(temp);
            
            }
            
            rs.close();
            stmt.close();
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return table;
        
    }
    
    public ArrayList getAddressData(String statement) {
        
        System.out.println("Getting data~");
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList table = new ArrayList<>();
        //String[] temp = new String[5];
        
        try {
            
            c = DriverManager.getConnection("jdbc:sqlite:tietokanta_2.sqlite3");
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            System.out.println(statement);
            rs = stmt.executeQuery(statement);
            //int i = 0;
            while(rs.next()) {
                
                String[] temp = new String[5];
                
                temp[0] = rs.getString("katu");
                temp[1] = rs.getString("postinro");
                temp[2] = rs.getString("kaupunki");
                temp[3] = rs.getString("nimi");
                temp[4] = rs.getString("saatavilla");
                table.add(temp);
                //i++;
            
            }
            
            rs.close();
            stmt.close();
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return table;
        
    }
  
    public void closeDatabase() throws SQLException {
        
        c.close();
        
    }
}
