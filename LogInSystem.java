import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class LogInSystem extends JFrame implements ActionListener
{

	JLabel lblUserName = new JLabel("USER NAME");
	JTextField txtUserName = new JTextField(20);

	JLabel lblPassward = new JLabel("PASSWARD");
	JPasswordField txtPassward = new JPasswordField();

	JButton btnLogin = new JButton("LOG IN");

	JButton btnCancel = new JButton("CANCEL");

	DbUtil util=new DbUtil();
	boolean flag=false;

	public LogInSystem()
	{
		Toolkit tool=Toolkit.getDefaultToolkit();



		this.getContentPane().setBackground(Color.pink);
    	this.getContentPane().setLayout(null);


		this.getContentPane().add(lblUserName);
		this.getContentPane().add(txtUserName);

		this.getContentPane().add(lblPassward);
		this.getContentPane().add(txtPassward);

		this.getContentPane().add(btnLogin);
		this.getContentPane().add(btnCancel);


		lblUserName.setBounds(50,80,120,20);
		txtUserName.setBounds(180,80,150,20);


		lblPassward.setBounds(50,110,120,20);
		txtPassward.setBounds(180,110,150,20);


		btnLogin.setBounds(130,160,100,20);
		btnCancel.setBounds(250,160,100,20);


		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
		txtPassward.addActionListener(this);

		this.setBackground(Color.green);

		this.setTitle("LOG IN SYSTEM");
		this.setBounds(300,100,500,300);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnCancel)
		{
			this.dispose();
		}

		if(ae.getSource()==btnLogin || ae.getSource()==txtPassward)
		{
			Vector vecUser=new Vector();
			Vector vecPass=new Vector();
			String user=txtUserName.getText();
			String pass=txtPassward.getText();

			try
			{

				ResultSet rs=util.stmt.executeQuery("Select USER_NAME, PASSWORD from Login");
				while(rs.next())
				{
					vecUser.addElement(rs.getString(1));
					vecPass.addElement(rs.getString(2));
				}
			}

			catch(Exception e)
			{
				System.out.println(e);
			}

			for(int i=0;i<vecUser.size();i++)
			{
				if(vecUser.elementAt(i).equals(user) && vecPass.elementAt(i).equals(pass))
				{
					//System.out.println("Log in successful");
					flag=true;
				}

			}

			if(flag==true)
			{
				Home F1=new Home();
				this.dispose();
			}

			else

			JOptionPane.showMessageDialog(null, "Invalid username or Password");



		}


	}


	public static void main(String args[])
	{
		LogInSystem li=new LogInSystem();
	}
}