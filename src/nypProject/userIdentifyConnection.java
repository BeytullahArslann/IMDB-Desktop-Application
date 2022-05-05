
package nypProject;

import java.sql.*;
import javax.swing.*;


public class userIdentifyConnection {
    
    Connection connection = null;
    public static Connection connectUser(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nyp_scheme?useSSL=false&serverTimezone=UTC","root","12345");
            return connection;
            
        }catch(Exception e){
            return null;
        }
    }
}
