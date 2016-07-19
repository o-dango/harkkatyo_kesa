/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    
    public void addData() {
        
        System.out.println("Adding data~");
        
    }
    
    public void deleteData() {
        
        System.out.println("Deleting data~");
        
    }
  
    public void closeDatabase() throws SQLException {
        
        c.close();
        
    }
}
