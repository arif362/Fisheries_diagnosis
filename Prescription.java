import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;


import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

import java.util.*;


public class Prescription extends JInternalFrame implements ActionListener, ItemListener
{

	JLabel lblTitle = new JLabel();
	JLabel lblPrescriptionNo = new JLabel();
	JTextField txtPrescriptionNo = new JTextField();
	JLabel lblExpertName = new JLabel();
	JComboBox cmbExpertName = new JComboBox();
	JLabel lblDisease = new JLabel();
	JComboBox cmbDisease = new JComboBox();
	JLabel lblSymptom = new JLabel();
	JComboBox cmbSymptom = new JComboBox();
	JLabel lblRemedy = new JLabel();
	JTextField txtRemedy = new JTextField();
	JLabel lblPrescriptionDate = new JLabel();
	JTextField txtPrescriptionDate = new JTextField();
	JButton btnSave = new JButton();
	JButton btnRefresh = new JButton();
	JButton btnExit = new JButton();
	String dOj;
	JButton btnJourDate = new JButton("...");

	JPanel pnlPrint=new JPanel();

	String symptomID="";
	String diseaseID="";
	String fair;

	String lastId="";
	String finalId;


	DbUtil util=new DbUtil();
	ResultSet rs;


  public static void main(String[] args)
  {
    Prescription Pre= new Prescription();
  }

  public Prescription()
  {
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	Dimension size=toolkit.getScreenSize();

	BorderLayout border=new BorderLayout();

    lblTitle.setFont(new java.awt.Font("Dialog", 1, 18));
    lblTitle.setText("Prescription");
    lblTitle.setBounds(new Rectangle(150, 18, 130, 22));
    this.getContentPane().setBackground(Color.pink);
    this.getContentPane().setLayout(null);
    lblPrescriptionNo.setFont(new java.awt.Font("Dialog", 1, 12));
    lblPrescriptionNo.setText("Presc.No");
    lblPrescriptionNo.setBounds(new Rectangle(43, 85, 81, 17));
    txtPrescriptionNo.setBounds(new Rectangle(172, 86, 146, 21));
    lblExpertName.setFont(new java.awt.Font("Dialog", 1, 12));
    lblExpertName.setText("Expert Name");
    lblExpertName.setBounds(new Rectangle(43, 130, 84, 23));
    lblDisease.setFont(new java.awt.Font("Dialog", 1, 12));
    lblDisease.setText("Disease");
    lblSymptom.setBounds(new Rectangle(43, 167, 95, 21));
    lblSymptom.setFont(new java.awt.Font("Dialog", 1, 12));
    lblSymptom.setText("Symptom");
    lblDisease.setBounds(new Rectangle(43, 205, 79, 17));
    lblRemedy.setFont(new java.awt.Font("Dialog", 1, 12));
    lblRemedy.setText("Remedy(Medicine)");
    lblRemedy.setBounds(new Rectangle(43, 248, 64, 19));
    txtRemedy.setBounds(new Rectangle(172, 246, 146, 21));
    lblPrescriptionDate.setFont(new java.awt.Font("Dialog", 1, 12));
    lblPrescriptionDate.setText("Prescription Date");
    lblPrescriptionDate.setBounds(new Rectangle(40, 285, 100, 18));
    txtPrescriptionDate.setBounds(new Rectangle(172, 286, 146, 21));
    txtPrescriptionDate.setEditable(false);
    btnSave.setFont(new java.awt.Font("Dialog", 1, 12));
    btnSave.setText("Save");
    btnSave.setBounds(new Rectangle(28, 373, 109, 20));

    pnlPrint.setBounds(new Rectangle(400,10,600,400));
	pnlPrint.setLayout(border);

    btnSave.addActionListener(this);
    btnRefresh.addActionListener(this);
    btnExit.addActionListener(this);

	txtRemedy.setEditable(false);

    btnRefresh.setFont(new java.awt.Font("Dialog", 1, 12));
    btnRefresh.setText("Refresh");
    btnRefresh.setBounds(new Rectangle(153, 373, 118, 20));

    btnExit.setFont(new java.awt.Font("Dialog", 1, 12));
    btnExit.setText("Exit");
    btnExit.setBounds(new Rectangle(293, 373, 101, 19));
    cmbExpertName.setBounds(new Rectangle(172, 132, 146, 21));

    cmbSymptom.setBounds(new Rectangle(172, 169, 146, 21));
	setExpertName();
	setSymptom();
	setDisease();
	setFair();

	cmbSymptom.addItemListener(this);
	cmbDisease.addItemListener(this);
    cmbDisease.setBounds(new Rectangle(172, 209, 146, 21));
    this.add(btnJourDate);
    btnJourDate.setBounds(new Rectangle(328, 286, 40, 21));
	btnJourDate.addActionListener(new java.awt.event.ActionListener() {
	  public void actionPerformed(ActionEvent e) {
            SimpleDateFormat sdf=new SimpleDateFormat();
            sdf.applyPattern("dd-MMM-yyyy");
            ShowCalendar sc=new ShowCalendar((int)btnJourDate.getLocationOnScreen().getX(),(int)btnJourDate.getLocationOnScreen().getY(),btnJourDate.getWidth());
            dOj=sdf.format(sc.calendar.getDate());
            txtPrescriptionDate.setText(dOj);
	  }
	});

	setPrescriptionNo();
	this.setTitle("Prescription Center");
    this.getContentPane().add(lblTitle, null);
    this.getContentPane().add(lblPrescriptionNo, null);
    this.getContentPane().add(lblExpertName, null);
    this.getContentPane().add(lblDisease, null);
    this.getContentPane().add(lblSymptom, null);
    this.getContentPane().add(lblRemedy, null);
    this.getContentPane().add(txtPrescriptionNo, null);
    this.getContentPane().add(txtRemedy, null);
    this.getContentPane().add(lblPrescriptionDate, null);
    this.getContentPane().add(txtPrescriptionDate, null);
    this.getContentPane().add(btnSave, null);
    this.getContentPane().add(btnRefresh, null);
    this.getContentPane().add(btnExit, null);
    this.getContentPane().add(cmbExpertName, null);
    this.getContentPane().add(cmbDisease, null);
    this.getContentPane().add(cmbSymptom, null);
    this.getContentPane().add(cmbDisease, null);
    this.setBounds(10,80, size.width-20,size.height-200);

    this.getContentPane().add(pnlPrint);


    this.setVisible(true);
  }

  public void setNull()
  {
    //txtPrescriptionNo.setText("");
    //txtRemedy.setText("");
    //txtPrescriptionDate.setText("");
  }

  public void actionPerformed(ActionEvent ae)
  {
	  if(ae.getSource()==btnExit)
	  {
		 this.dispose();
	  }

	  else if(ae.getSource()==btnSave)
	  {
		    util=new DbUtil();
		    String expertID="";
  			try
  			{
				rs=util.stmt.executeQuery("select expert_id from Fisheries_Expert where expert_first_name='"+String.valueOf(cmbExpertName.getSelectedItem())+"'");
				while(rs.next())
				{
					expertID=rs.getString(1);

				}

				//System.out.println(diseaseID);
				//System.out.println(symptomID);
				//System.out.println(expertID);

				String sql="insert into Prescription values('"+txtPrescriptionNo.getText()+"','"+expertID+"','"+diseaseID+"','"+symptomID+"','"+fair+"','"+dOj+"')";
				//System.out.println(sql);
				util.stmt.executeUpdate(sql);
				//util.stmt.close();
				//util.conn.close();
				PrintPrescription print=new PrintPrescription(txtPrescriptionNo.getText());
				pnlPrint.add(print.viewer,BorderLayout.CENTER);
				pnlPrint.revalidate();
			 	//setNull();
			 	setPrescriptionNo();

  			}

  			catch(SQLException ex)
  			{
				System.out.println(ex);
  			}



  	  }

	else if(ae.getSource()==btnRefresh)
	{
		setNull();
	}
  }

	public void setFair()
	{
		String selectedFrom=String.valueOf(cmbDisease.getSelectedItem());
		String selectedTo=String.valueOf(cmbSymptom.getSelectedItem());
		util=new DbUtil();
		try{
			rs=util.stmt.executeQuery("select Symptom_id from Symptom where Symptom_name='"+selectedTo+"'");
			while(rs.next())
			{
				diseaseID=rs.getString(1);
			}

		}

		catch(Exception ex){}
//===========================================================================
		try{
			rs=util.stmt.executeQuery("select Disease_id from Disease where Disease_name='"+selectedFrom+"'");
			while(rs.next())
			{
				symptomID=rs.getString(1);

			}

		}

		catch(Exception ex){}
//===========================================================================

		try
		{
			rs=util.stmt.executeQuery("select Remedy from Remedy where Disease_id='"+symptomID+"' AND Symptom_id='"+diseaseID+"'");
			while(rs.next())
			{
				fair=rs.getString(1);

			}
			txtRemedy.setText(String.valueOf(fair));


		}

		catch(Exception ex){}

	}

//=================================================================================
	public void setPrescriptionNo()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Prescription_no from Prescription");
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
			finalId="PRES.-1";
		}

		else
		{

			StringTokenizer token=new StringTokenizer(lastId,"-");
			String fixed=token.nextToken();
			int var=Integer.parseInt(token.nextToken());
			var++;

			finalId=fixed+"-"+var;
		}

		txtPrescriptionNo.setText(finalId);
		txtPrescriptionNo.setEditable(false);

	}

	public void setExpertName()
	{
    // -------- get data to combo from database--------------------------------------------

		try
		{
			rs=util.stmt.executeQuery("select expert_first_name from Fisheries_Expert");
			while(rs.next())
			{
				cmbExpertName.addItem(rs.getString(1));
			}
		}

		catch(SQLException sqle)
		{

		}
	//==============================================================================================

	}

	public void setSymptom()
	{
		try
		{
			rs=util.stmt.executeQuery("select Symptom_name from Symptom");
			while(rs.next())
			{
				cmbSymptom.addItem(rs.getString(1));
			}

		}

		catch(Exception e)
		{

		}

	}
//=========================================================================================

	public void setDisease()
	{
		String dis="";
		cmbDisease.removeAllItems();
		try
		{
			rs=util.stmt.executeQuery("select Symptom_id from Symptom where Symptom_name='"+cmbSymptom.getSelectedItem()+"'");
			while(rs.next())
			{
				dis=rs.getString(1);
			}

			rs=util.stmt.executeQuery("select Disease_name from Disease where Symptom_id='"+dis+"' ");
			while(rs.next())
			{
				cmbDisease.addItem(rs.getString(1));
			}

		}

		catch(Exception e)
		{

		}

	}


	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==cmbSymptom)
		{
			setDisease();
		}

		else if(ie.getSource()==cmbDisease)
		{
			setFair();
		}

	}

}



//String query = "INSERT INTO entryTbl(name, joinedDate, ..etc.,) values ('abc', TO_TIMESTAMP('"+dateString+"', 'YYYY/MM/DD HH:MI:SS TZH:TZM'), etc.,)";