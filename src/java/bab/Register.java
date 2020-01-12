/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bendeati
 */
public class Register {
    private String username;
    private String password;
    private String fullname;
    
    private boolean registerOK;
    
    private final Kapcsolat kapcs;
    private String uzenet;
    private Statement st;
    private ResultSet rs;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUzenet() {
        return uzenet;
    }

    
    
    public Register() {
        this.kapcs = new Kapcsolat();
    }
    
    public boolean isUserInDatabase()
    {
        try 
        {
            if(kapcs.connOK())
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from felhasznalo WHERE username LIKE '"+username+"'");
                if(rs.next())
                {
                    uzenet = "Létező felhasználó!";
                    return false;
                }
                else
                {
                    return true;
                }
            }
        } 
        catch (SQLException e) 
        {
            uzenet = "Hiba a lekérdezéskor" + e;
        }
        return true;
    }
    
    public void userInsert()
    {
        try 
        { 
            if(kapcs.connOK() && isUserInDatabase())
            {
                String query = "INSERT INTO felhasznalo(username,password,fullname) values('"
                    +username+"','"+password+"','"+fullname+"')";
                st = kapcs.getStm();
                st.executeUpdate(query); 
                uzenet = "A regisztráció sikeres! Jelentkezzen be!";
                registerOK = true;
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen adatrögzítés!" + e.getMessage();
            registerOK = false;
        }
        
    }
}
