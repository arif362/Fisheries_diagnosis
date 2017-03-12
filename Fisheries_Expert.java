
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


public class Fisheries_Expert  extends JInternalFrame implements ActionListener
{

	JFrame frame=new JFrame();

	JLabel lblInfo=new JLabel("Expert Information");

	JLabel lblid=new JLabel("Expert ID");
	JLabel lblfirstnam=new JLabel("EXPERT FIRST NAME");
	JLabel lbllastnam=new JLabel("EXPERT LAST NAME ");
	JLabel lblphone=new JLabel("EXPERT HOME PHONE");
	JLabel lblmobile=new JLabel("EXPERT MOBILE PHONE");
	JLabel lbladd=new JLabel("EXPERT ADDRESS");

	JTextField txtid=new JTextField(20);
	JTextField txtfirstnam=new JTextField(20);
	JTextField txtlastnam=new JTextField(20);
	JTextField txtphone=new JTextField(20);
	JTextField txtmobile=new JTextField(20);

	JTextArea taAddress=new JTextArea(10,10);
	JScrollPane sp=new JScrollPane(taAddress);

	JButton btnSave=new JButton("Save");
	JButton btnRefresh=new JButton("Refresh");
	JButton btnExit=new JButton("Exit");

	DbUtil util=new DbUtil();

	String lastId="";
	String finalId;





	public Fisheries_Expert()
	{
		Container c=this.getContentPane();
		c.setLayout(null);

		this.add(lblInfo);

		this.add(lblid);
		this.add(lblfirstnam);
		this.add(lbllastnam);
		this.add(lblphone);
		this.add(lblmobile);
		this.add(lbladd);

		this.add(txtid);
		this.add(txtfirstnam);
		this.add(txtlastnam);
		this.add(txtphone);
		this.add(txtmobile);
		this.add(sp);

		this.add(btnSave);
		this.add(btnRefresh);
		this.add(btnExit);


		lblInfo.setFont(new Font("sansserif",Font.BOLD,20));

		lblInfo.setBounds(160,20,250,20);


		lblid.setBounds(20,60,150,20);
		lblfirstnam.setBounds(20,90,150,20);
		lbllastnam.setBounds(20,120,150,20);
		lblphone.setBounds(20,150,150,20);
		lblmobile.setBounds(20,180,150,20);
		lbladd.setBounds(20,210,150,20);

		txtid.setBounds(180,60,150,20);
		txtfirstnam.setBounds(180,90,150,20);
		txtlastnam.setBounds(180,120,150,20);
		txtphone.setBounds(180,150,150,20);
		txtmobile.setBounds(180,180,150,20);

		sp.setBounds(180,210,150,60);


		btnSave.setBounds(60,290,100,20);
		btnRefresh.setBounds(170,290,100,20);
		btnExit.setBounds(280,290,100,20);




		btnRefresh.addActionListener(this);
		btnExit.addActionListener(this);
		btnSave.addActionListener(this);

		setExpertId();

		this.setTitle("Fisheries Expert");
		this.setBounds(300,150,500,350);
		this.setVisible(true);
	}



	public void setExpertId()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Expert_id from Fisheries_Expert");
			while(rs.next())
			{
				lastId=rs.getString(1);
				//System.out.println(lastId);
			}

		}

		catch(Exception e)
		{
			System.out.println(e);
		}




		if(lastId.equals(""))
		{
			finalId="F.E-1";
		}

		else
		{

			StringTokenizer token=new StringTokenizer(lastId,"-");
			String fixed=token.nextToken();
			int var=Integer.parseInt(token.nextToken());
			var++;

			finalId=fixed+"-"+var;
		}

		txtid.setText(finalId);
		txtid.setEditable(false);
		//System.out.println(finalId);

	}






	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnExit)
		{
			this.dispose();
		}



		else if(ae.getSource()==btnSave)
		{

			String id=txtid.getText();

			String fName=txtfirstnam.getText();

			String lName=txtlastnam.getText();

			String phone=txtphone.getText();

			String mob=txtmobile.getText();

			String add1=taAddress.getText();

			try
			{
				util.stmt.executeUpdate("insert into Fisheries_Expert values('"+id+"','"+fName+"','"+lName+"','"+add1+"','"+phone+"','"+mob+"')");
				util.conn.commit();
				setNull();
				JOptionPane.showMessageDialog(this,"Expert information Added Successfully");
				setExpertId();


			}

			catch(SQLException sqle)

			{

				JOptionPane.showMessageDialog(this,sqle);
			}

		}
		else if(ae.getSource()==btnRefresh)
		{
				setNull();
		}


	}
	public void setNull()
	{
		txtid.setText("");
		txtfirstnam.setText("");
		txtlastnam.setText("");
		txtphone.setText("");
		txtmobile.setText("");
		taAddress.setText("");
		txtid.requestFocus();

	}
}



