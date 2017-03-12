import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.sql.*;




public class Symptom  extends JInternalFrame implements ActionListener
{
	JFrame frame=new JFrame();


	JLabel lblHeading=new JLabel("Symptom");



	JLabel lblId=new JLabel("Symptom ID");
	JLabel lblName=new JLabel("Symptom Name");
	JLabel lblDes=new JLabel("Descriptions");


	JTextField txtId=new JTextField(20);
	JTextField txtName=new JTextField(20);
	JTextField txtDes=new JTextField(20);





	JButton btnSave=new JButton("Save");
	JButton btnReferesh=new JButton("Referesh");
	JButton btnExit=new JButton("Exit");

	DbUtil util=new DbUtil();

	String lastId="";
	String finalId;




 	public Symptom()
 	{

 		Container c=this.getContentPane();
		c.setLayout(null);

		this.add(lblHeading);
		this.add(lblId);
		this.add(lblName);
		this.add(lblDes);

		this.add(txtId);
		this.add(txtName);
		this.add(txtDes);


		this.add(btnSave);
		this.add(btnReferesh);
		this.add(btnExit);

		lblHeading.setFont(new Font("sansserif", Font.BOLD, 20));


		lblHeading.setBounds(250,20,200,20);


		lblId.setBounds(100,70,200,20);
		lblName.setBounds(100,100,200,20);
		lblDes.setBounds(100,130,200,20);



		txtId.setBounds(250,70,200,20);
	    txtName.setBounds(250,100,200,20);
	    txtDes.setBounds(250,130,200,20);



		btnSave.setBounds(150,200,100,20);
		btnReferesh.setBounds(260,200,100,20);
		btnExit.setBounds(370,200,100,20);



		btnSave.addActionListener(this);
		btnReferesh.addActionListener(this);
		btnExit.addActionListener(this);

		setSymptomId();
		this.setTitle("Symptoms");
		this.setBounds(300,120,600,350);
		this.setVisible(true);


	}

	public void setSymptomId()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Symptom_id from Symptom");
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
			finalId="STM-1";
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
			if(ae.getSource()==btnExit)
			{
				this.dispose();
			}
			else if(ae.getSource()==btnSave)
			{

					String id=txtId.getText();

					String SymName=txtName.getText();

					String symDesc=txtDes.getText();
					try
					{
							util.stmt.executeUpdate("insert into Symptom values('"+id+"','"+SymName+"','"+symDesc+"')");
							setNull();
							JOptionPane.showMessageDialog(this,"Symptom Added Successfully");
							setSymptomId();

					}

					catch(SQLException sqle)

					{

							JOptionPane.showMessageDialog(this,"Data Could not Saved");
					}


			}
		    else if(ae.getSource()==btnReferesh)
		   {
				setNull();

	        }
     }



 	public void setNull()
	{
			txtId.setText("");
			txtName.setText("");
			txtDes.setText("");
			txtId.requestFocus();

	}
}