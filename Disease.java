import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Disease extends JInternalFrame implements ActionListener
{

	JLabel lblPicInfo=new JLabel("DISEASE INFORMATION ");



	JButton btnsave=new JButton("Save");

	JButton btnRef=new JButton("Refresh ");
	JButton btnclose=new JButton("Close");


	JTextField txtId=new JTextField();
	JLabel lblId =new JLabel("DISEASE  ID");

	JTextField txtName=new JTextField();
	JLabel lblName=new JLabel("DISEASE NAME");

	JComboBox cmbDest=new JComboBox();
	JLabel lblDest=new JLabel("SELECT SYMPTOM");


	JTextField txtDes=new JTextField();
	JLabel lblDes=new JLabel("DISEASE DESCRIPTION");

	DbUtil util=new DbUtil();
	String lastId="";
	String finalId;



	public Disease ()
	{
			Container c=this.getContentPane();


			c.setLayout(null);

			c.add(btnsave);
			c.add(btnRef);
			c.add(btnclose);

			c.add(lblPicInfo);



			c.add(txtId);
			c.add(lblId);

			c.add(txtName);
			c.add(lblName);

			c.add(lblDest);
			c.add(cmbDest);


			c.add(txtDes);
			c.add(lblDes);



			lblPicInfo.setFont(new Font("sansserif",Font.BOLD,20));

			lblPicInfo.setBounds(120,40,350,20);


			lblId.setBounds(20,100,150,20);
			txtId.setBounds(180,100,150,20);

			lblDest.setBounds(20,130,150,20);
			cmbDest.setBounds(180,130,150,20);


			lblName.setBounds(20,160,150,20);
			txtName.setBounds(180,160,150,20);


			lblDes.setBounds(20,190,150,20);
			txtDes.setBounds(180,190,150,20);



			btnsave.setBounds(40,240,130,20);
			btnRef.setBounds(180,240,130,20);
			btnclose.setBounds(320,240,130,20);



			btnsave.addActionListener(this);
			btnRef.addActionListener(this);
			btnclose.addActionListener(this);

//--------------------------Code to add data to Combo box from database-------------------------------------
			try
			{
				ResultSet rs=util.stmt.executeQuery("select SYMPTOM_NAME from Symptom");
				while(rs.next())
				{
					cmbDest.addItem(rs.getString(1));
				}

			}

			catch(Exception e)
			{
				//JOptionPane.showMessageDailog(this,"Unable to load SYMPTOMs");
			}
//----------------------------------------------------------------------------------------------------------
			setDiseaseId();
			this.setBounds(200,100,500,400);
			this.setTitle("Disease");
			this.setVisible(true);


	}

	public void setDiseaseId()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Disease_id from Disease");
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
			finalId="PIC-1";
		}

		else
		{

			StringTokenizer token=new StringTokenizer(lastId,"-");
			String fixed=token.nextToken();
			int var=Integer.parseInt(token.nextToken());
			var++;

			finalId=fixed+"-"+var;
		}

		txtId.setText(finalId);
		txtId.setEditable(false);

	}




	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}

		else if(ae.getSource()==btnRef)
		{
			setNull();
		}

		else if(ae.getSource()==btnsave)
		{

			String id=txtId.getText();

			String diseaseName=txtName.getText();

			String diseaseDes=txtDes.getText();

			String destId="";


			try
			{

				ResultSet rs=util.stmt.executeQuery("select SYMPTOM_ID from Symptom where SYMPTOM_NAME='"+cmbDest.getSelectedItem()+"'");
				while(rs.next())
				{
					destId=rs.getString(1);
				}

				util.stmt.executeUpdate("insert into Disease values('"+id+"','"+diseaseName+"','"+destId+"','"+diseaseDes+"')");
				setNull();
				JOptionPane.showMessageDialog(this,"Disease information  Added Successfully");
				setDiseaseId();
			}

			catch(SQLException sqle)
			{
				JOptionPane.showMessageDialog(this,"Data Could not Saved");
			}

		}
	}

	public void setNull()

	{
		txtId.setText("");
		txtName.setText("");
		txtDes.setText("");
	}


	public static void main(String args[])
	{
		Disease bd=new Disease();
	}
}