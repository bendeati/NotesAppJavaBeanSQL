/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bendeati
 */
public class Login {
    private int id;
    private String username;
    private String password;
    
    private final Kapcsolat kapcs;
    private String uzenet;
    private Statement st;
    private ResultSet rs;
    private Connection con;
       
    private boolean loggedIn;

    public Login() {
        this.loggedIn = false;
        kapcs = new Kapcsolat();
    }

    //Getterek és Setterek
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    
    public boolean login(String url)
    {
        try{
         // open a connection
          Class.forName("com.mysql.jdbc.Driver");
          if(kapcs.connOK())
          {
            con = kapcs.getCon();
            st = kapcs.getStm();
            rs = st.executeQuery("select * from felhasznalo WHERE username LIKE '"+username+"' AND password LIKE '"+password+"'");

            if(rs.next())
            {
             username = rs.getString("username");
             password = rs.getString("password");
             id = rs.getInt("id");
             loggedIn = true;
            }
            else
              loggedIn = false;
            st.close();
            con.close();
          }
         }
        catch(ClassNotFoundException | SQLException e)
        {
          loggedIn = false; 
        } 
       
        return loggedIn;
   }
  
   public void logOut()
   {
     loggedIn = false;
   }

}
