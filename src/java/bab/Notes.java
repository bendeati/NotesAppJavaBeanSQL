/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bab;


import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author bendeati
 */
public class Notes {
    
    private int id;
    private int userID;
    private String title;
    private String content;
    private Date date1;
    private Date date2;
    
    private final Kapcsolat kapcs;
    private String uzenet;
    private Statement st;
    private ResultSet rs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getUzenet() {
        return uzenet;
    }

    
    
    public Notes() {
        this.kapcs = new Kapcsolat();
    }
    
    public void allNotes(int uID)
    {
        
        try 
        { 
           if(kapcs.connOK())
            {
                st = kapcs.getStm();
                rs = st.executeQuery("select * from jegyzet WHERE userID=" + uID);
                uzenet = "Adatok lekérése rendben!";
            }
        } 
        catch (SQLException e)
        { 
            uzenet = "Hibás adatlekérés! " + e;
        }
    }
    
    public ResultSet listaLeker() {  
      return rs;  
    }
    
    public void insertNote(int userID)
    {
        try 
        { 
            if(kapcs.connOK())
            {
                //DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                //Date datum = new Date();
                String modDatum = currentDate();
                String query = "INSERT INTO jegyzet (title,content,userID,date1,date2) values('"
                    +title+"','"+content+"',"+userID+",'"+ modDatum +"','"+modDatum+"')";
                //létrehozás és módosítás dátuma a létrehozáskor megegyezik...
                st = kapcs.getStm();
                st.executeQuery("SET NAMES 'UTF8'");
                st.executeQuery("SET CHARACTER SET 'UTF8'");
                st.executeUpdate(query); 
                uzenet = "A jegyzet létrehozása sikeres!";
            }
            
        }
        catch (SQLException e)
        {
            uzenet = "Sikertelen adatrögzítés!" + e.getMessage();
        }
    }
    
    public void modAdatokListaz(int modId)
    {
        try 
        {
            st = kapcs.getStm();
            rs = st.executeQuery("SELECT * FROM jegyzet WHERE id="+modId);
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
    }
    
    public void modosit(int modId)
    {
        
        try 
        { 
            String query = "UPDATE jegyzet SET "
                    + "title = '"+title+"',"
                    + "content = '"+content+"',"
                    + "date2 = '"+currentDate()+"' WHERE id="+modId;
            st.executeUpdate(query); 
            System.out.println("A módosítás sikeres!");
        }
        catch (SQLException e)
        {
            System.out.println("Hibás rögzítés:" + e.getMessage());
        }
    }
    
    public boolean torles(int modId)
    {
        boolean siker = false;
        try 
        { 
            String query = "DELETE FROM jegyzet WHERE id="+modId;
            st.executeUpdate(query); 
            siker = true;
        }
        catch (SQLException e)
        {
            System.out.println("Hiba a mátrixban:" + e.getMessage());
        }
        return siker;
    }
    
    private String currentDate()
    {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date datum = new Date();
        return f.format(datum);
    }

}
