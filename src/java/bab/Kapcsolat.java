/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bendeati
 */
public class Kapcsolat {
    private Connection con; 
    private Statement stm; 
    private String uzenet;

    public Kapcsolat() {
    }

    public Statement getStm() {
        return stm;
    }

    public Connection getCon() {
        return con;
    }
    
        
    public boolean connOK()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes?useUnicode=yes&characterEncoding=UTF-8","root","root"); 
            stm = con.createStatement(); 
            return true;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            uzenet = e.toString();
            return false;
        }
    }
    
    public String uzenet()
    {
        if(connOK())
            return "Kapcsolódás rendben"; 
        else 
            return "Kapcsolódási HIBA <br />" + uzenet;
    }
}
