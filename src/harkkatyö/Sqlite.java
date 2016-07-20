/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public void getData(String statement) {
        
        System.out.println("Getting data~");
        
    }
  
    public void closeDatabase() throws SQLException {
        
        c.close();
        
    }
}
