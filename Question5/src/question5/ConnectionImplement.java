/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question5;

import java.util.*;
import java.sql.*;

public class ConnectionImplement implements ConnectInterface{

    @Override
    public void insert(int mID, String mName, String mSurname, int mAge, int mCellNumber, String  mDegree) throws Exception {
        
        String HostName = "localhost";
        String Db = "registrants";
        String UserName = "root";
        String Password = "Captain@1945!";
        String URL = "jdbc:mysql://"+HostName+":3308/"+Db;
        
        try{
            Connection dbCon = DriverManager.getConnection(URL,UserName,Password);
            Statement dbSt = dbCon.createStatement();
            String Query1 = "INSERT INTO students (idnumber,name,surname,age,cellnumber,degree)"
                    + " VALUES('"+mID+"','"+mName+"','"+mSurname+"','"+mAge+"','"+mCellNumber+"','"+mDegree+"')";
            dbSt.executeUpdate(Query1); 
        }
        catch(SQLException ex) {
            System.out.println("Not connecting");
        }
        
            
    }
    
}
