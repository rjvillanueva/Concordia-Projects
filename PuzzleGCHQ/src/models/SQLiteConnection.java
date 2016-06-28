package models;

import java.sql.*;
import javax.swing.*;

public class SQLiteConnection {
	
	public static Connection dbConnector()
	{
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			Connection connection = DriverManager.getConnection("jdbc:sqlite:..\\Puzzles.sqlite");
			return connection;
		
		} catch(Exception e)
		{
		
			JOptionPane.showMessageDialog(null, e);
			return null;
		
		}
	}
}
