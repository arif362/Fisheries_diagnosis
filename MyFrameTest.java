
import java.awt.*;

import javax.swing.*;

public class MyFrameTest extends JInternalFrame
//extends JInternalFrame
//implements ActionListener
//extends JFrame
{
	//JFrame frame=new JFrame();

	Frame f=new Frame();
	FileDialog fidi=new FileDialog(f,"choose!",FileDialog.LOAD);


	public MyFrameTest()
	{
		fidi.setVisible(true);
		System.out.println(fidi.getFile());



//		frame.setLocation(220,100);
//		frame.setSize(600,600);
//		frame.setVisible(false);

	}
	public static void main(String args[])
	{
		MyFrameTest frametest=new MyFrameTest();
	}
}