import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Home extends JFrame implements ActionListener
{
	JDesktopPane desktop=new JDesktopPane();

	JMenuBar mainMenuBar = new JMenuBar();

	JMenu sysMenu=new JMenu("System");


	JMenuItem logOutItem=new JMenuItem("Logout");
	JMenuItem exitItem=new JMenuItem("Exit");

	/////////////////////////////////////////////

	JMenu entryMenu=new JMenu("Data Entry");

	JMenuItem expertInfo=new JMenuItem(" Fisheries Expert");
	JMenuItem symptomInfo=new JMenuItem("Symptom");
	JMenuItem diseaseInfo=new JMenuItem(" Disease");
	JMenuItem remedyInfo=new JMenuItem(" Remedy");
	JMenuItem prescriptionInfo=new JMenuItem(" Prescription");


    ////////////////////////////////////

	JMenu editMenu=new JMenu("About Us");

	JMenuItem authorInfo=new JMenuItem("Introduction to Author");

	///////////////////////////////////////////

	JMenu reportMenu=new JMenu("Report");

	JMenuItem printoutItem=new JMenuItem("Print");


	JMenu helpMenu=new JMenu("Help");

	JMenuItem softwareItem=new JMenuItem(" About this software");
	JMenuItem topicsItem=new JMenuItem(" Help topics");


	public Home()
	{
		this.setTitle("Monitoring & Detecting System of Disease for Fisheries");

    	Toolkit tool=Toolkit.getDefaultToolkit();
    	Dimension size=tool.getScreenSize();


    	/////////////////////////////


    	mainMenuBar.add(sysMenu);
		mainMenuBar.add(entryMenu);
		mainMenuBar.add(editMenu);
		mainMenuBar.add(reportMenu);
		mainMenuBar.add(helpMenu);


		/////////////////////////////////////


		sysMenu.add(logOutItem);
		sysMenu.addSeparator();
		sysMenu.add(exitItem);


		////////////////////////////////////

		entryMenu.add(expertInfo);
		entryMenu.addSeparator();
		entryMenu.add(symptomInfo);
		entryMenu.addSeparator();
		entryMenu.add(diseaseInfo);
		entryMenu.addSeparator();
		entryMenu.add(remedyInfo);
		entryMenu.addSeparator();
		entryMenu.add(prescriptionInfo);


		//////////////////////////////////

		editMenu.add(authorInfo);



		////////////////////////////////////

		reportMenu.add(printoutItem);


		/////////////////////////////////////


		helpMenu.add(softwareItem);
		helpMenu.addSeparator();
		helpMenu.add(topicsItem);


		/////////////////////////////////////


		exitItem.addActionListener(this);

		expertInfo.addActionListener(this);
		symptomInfo.addActionListener(this);
		diseaseInfo.addActionListener(this);
		remedyInfo.addActionListener(this);
		prescriptionInfo.addActionListener(this);


		this.setJMenuBar(mainMenuBar);

		desktop.setBackground(Color.pink);
		this.getContentPane().add(desktop, BorderLayout.CENTER);


	    this.setSize(size);
	    this.setVisible(true);

	}

	public static void main(String args[])
	{
		Home F1=new Home();
	}

	public void actionPerformed(ActionEvent ae)
	{

		 if(ae.getSource()==expertInfo)
		{
			Fisheries_Expert Expert=new Fisheries_Expert();
			desktop.add(Expert);
		}

		else if(ae.getSource()==symptomInfo)
		{
			Symptom sym=new Symptom();
			desktop.add(sym);
		}

		else if(ae.getSource()==diseaseInfo)
		{
			Disease dis=new Disease();
			desktop.add(dis);
		}

		else if(ae.getSource()==remedyInfo)
		{
			Remedy R=new Remedy();
			desktop.add(R);
		}
		else if(ae.getSource()==prescriptionInfo)
		{
			 Prescription Pre= new Prescription();
			desktop.add(Pre);
		}


		else if(ae.getSource()==exitItem)
		{
			System.exit(0);
		}


	}

}