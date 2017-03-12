import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


public class Remedy  extends JInternalFrame implements ActionListener, ItemListener
{

	JFrame frame=new JFrame();

	JLabel lblInfo=new JLabel("Remedy Information");

	JLabel lblid=new JLabel("REMEDY ID");
	JLabel lblpiid=new JLabel("SELECT DISEASE");
	JLabel lbldes=new JLabel("SELECT SYMPTOM");
	JLabel lblamount=new JLabel("REMEDY(Medicine) ");


	JTextField txtId=new JTextField(20);
	JComboBox cmbPick=new JComboBox();
	JComboBox cmbDest=new JComboBox();
	JTextField txtAmount=new JTextField(20);

	JButton btnSave=new JButton("Save");
	JButton btnRefresh=new JButton("Refresh");
	JButton btnExit=new JButton("Exit");
	DbUtil util=new DbUtil();

	String lastId="";
	String finalId;
	ResultSet rs;
	String finalPickId;
	String finalDestId;


	public Remedy()
	{
		Container c=this.getContentPane();
		c.setLayout(null);

		this.add(lblInfo);

		this.add(lblid);
		this.add(lblpiid);
		this.add(lbldes);
		this.add(lblamount);


		this.add(txtId);
		this.add(cmbPick);
		this.add(cmbDest);
		this.add(txtAmount);

		this.add(btnSave);
		this.add(btnRefresh);
		this.add(btnExit);


		lblInfo.setFont(new Font("sansserif",Font.BOLD,20));

		lblInfo.setBounds(160,20,250,20);


		lblid.setBounds(20,80,150,20);
		lbldes.setBounds(20,110,150,20);
		lblpiid.setBounds(20,140,200,20);
		lblamount.setBounds(20,170,200,20);



		txtId.setBounds(200,80,150,20);
		cmbDest.setBounds(200,110,150,20);
		cmbPick.setBounds(200,140,150,20);
		txtAmount.setBounds(200,170,150,20);


		btnSave.setBounds(60,230,100,20);
		btnRefresh.setBounds(170,230,100,20);
		btnExit.setBounds(280,230,100,20);



		btnRefresh.addActionListener(this);
		btnExit.addActionListener(this);

		btnSave.addActionListener(this);
		cmbDest.addActionListener(this);

//--------------------------Code to add data to Combo box from database-------------------------------------
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Symptom_name from Symptom");
			while(rs.next())
			{
				cmbDest.addItem(rs.getString(1));
			}

		}

		catch(Exception e)
		{

		}

		setPickupPointName();
//----------------------------------------------------------------------------------------------------------


		setChargeId();
		cmbDest.addItemListener(this);

		this.setTitle("Remedy");
		this.setBounds(300,150,500,350);
		this.setVisible(true);
	}

	public void setPickupPointName()
	{
		String dest="";
		cmbPick.removeAllItems();
		try
		{
			rs=util.stmt.executeQuery("select Symptom_id from Symptom where Symptom_name='"+cmbDest.getSelectedItem()+"'");
			while(rs.next())
			{
				dest=rs.getString(1);
			}
			//System.out.println("Dest " +dest);
			rs=util.stmt.executeQuery("select Disease_name from Disease where Symptom_id='"+dest+"' ");
			while(rs.next())
			{
				cmbPick.addItem(rs.getString(1));
				//System.out.println(rs.getString(1));
			}

		}

		catch(Exception e)
		{

		}

	}

	public void setChargeId()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Remedy_id from Remedy");
			while(rs.next())
			{
				lastId=rs.getString(1);
			}

		}

		catch(Exception e)
		{
			System.out.println(e);
		}




		if(lastId.equals(""))
		{
			finalId="R-1";
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
			String cid=txtId.getText();

			String disName=cmbPick.getSelectedItem().toString();

			String symName=cmbDest.getSelectedItem().toString();

			String cAmount=txtAmount.getText();

			try
			{
				rs=util.stmt.executeQuery("select Disease_id from Disease where Disease_name='"+disName+"' ");
				while(rs.next())
				{
					finalPickId=rs.getString(1);
				}
				rs=util.stmt.executeQuery("select Symptom_id from Symptom where Symptom_name='"+symName+"'");
				while(rs.next())
				{
					finalDestId=rs.getString(1);
				}

				util.stmt.executeUpdate("insert into Remedy values('"+cid+"','"+finalPickId+"','"+finalDestId+"','"+cAmount+"')");
				setNull();
				JOptionPane.showMessageDialog(this,"Remedy Added Successfully");
				setChargeId();

			}

			catch(SQLException sqle)
			{
				JOptionPane.showMessageDialog(this,"Data Could not Saved");
			}

		}


	   	else if(ae.getSource()==btnRefresh)
	   	{
			setNull();

	   	}
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==cmbDest)
		{
			setPickupPointName();
		}

	}


	public void setNull()
	{
			txtId.setText("");
			cmbPick.setSelectedIndex(0);
			cmbDest.setSelectedIndex(0);
			txtAmount.setText("");

			txtId.requestFocus();

	}



}


