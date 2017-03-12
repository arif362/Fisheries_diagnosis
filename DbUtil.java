import java.sql.*;

import javax.swing.*;

public class DbUtil
{
		Statement stmt;
		Connection conn;
		public DbUtil()
		{
			try
			{
				//JOptionPane.showMessageDialog(null,"Entered in Try block of DBUtil");

				 Class.forName("oracle.jdbc.driver.OracleDriver");// Loading the class

				//JOptionPane.showMessageDialog(null,"Oracle Driver Manager Addaed successfully");

				 // Create a connection through the DriverManager
				 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");

				 //JOptionPane.showMessageDialog(null,"User name and password is correct. go ahead");

				 stmt = conn.createStatement(); // create a statement
			}

			catch(Exception cnfe)
			{
				JOptionPane.showMessageDialog(null,"Could not Connect Database");
			}

		}
}