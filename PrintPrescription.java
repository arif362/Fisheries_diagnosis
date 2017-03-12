import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.io.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.view.*;

public class PrintPrescription
{
	DbUtil util=new DbUtil();
	Vector allPrescription=new Vector();
	JRViewer viewer;

	public PrintPrescription(String prsNo)
	{
		getAllNames();

		Object allPrescriptionArray[]=allPrescription.toArray();
		//String name=(String) JOptionPane.showInputDialog(null,"Please select Prescription No","All Prescriptions",JOptionPane.QUESTION_MESSAGE,null,allPrescriptionArray,allPrescriptionArray[0]);

		HashMap param=new HashMap();
		param.put("Prescription_no",prsNo);

		try
		{
			JasperPrint print = JasperFillManager.fillReport("Prescription.jasper", param, util.conn);
			viewer=new JRViewer(print);
		}

		catch(JRException jre)
		{
			jre.printStackTrace();
		}
	}

	public void getAllNames()
	{
		try
		{
			ResultSet rs=util.stmt.executeQuery("select Prescription_no from Prescription");
			while(rs.next())
			{
				allPrescription.addElement(rs.getString(1));
			}
		}

		catch(SQLException sqle){

		}

	}

}

