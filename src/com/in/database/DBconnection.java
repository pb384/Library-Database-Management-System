package com.in.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class DBconnection {
	public static Connection con=null;
	
public static Statement getConnection(){
	PreparedStatement ps=null;
	Statement stmt =null;
	if(con == null){
	 try
	  {
	   Class.forName("org.sqlite.JDBC");
	   SQLiteConfig config = new SQLiteConfig();  
       config.enforceForeignKeys(true);  
	   con=DriverManager.getConnection("jdbc:sqlite:/Users/Saraff/Desktop/Fall2016/DBMS/Project/Deliverable3/LibraryDB.db",config.toProperties());
	  }
	 catch (Exception e)
	 {
	  e.printStackTrace();
	 }
	}
	try
	 {
	
	   stmt=con.createStatement();
	 }
	  catch (SQLException e)
		 {
		  
		  e.printStackTrace();
		 }
	
	 return stmt;
}
}
