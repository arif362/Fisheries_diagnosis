
import java.awt.*;
import javax.swing.*;



public class SplashScreen extends JWindow{
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel lblPic = new JLabel();
  JLabel lblStatus = new JLabel();



  public SplashScreen()
  {

	Toolkit toolkit=Toolkit.getDefaultToolkit();
	Dimension size=toolkit.getScreenSize();

	this.getContentPane().setBackground(Color.pink);
	this.getContentPane().setLayout(null);

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(lblPic, BorderLayout.CENTER);
    ImageIcon image=new ImageIcon("./img/Golda.jpg");
	lblPic.setIcon(image);

    this.getContentPane().add(lblStatus, BorderLayout.SOUTH);
    this.setBounds(size.width/2-150,size.height/2-200,400,400);


    this.setVisible(true);
    timer();
  }

   public void timer()
  {
	Thread time=new Thread();
	try
	{
		lblStatus.setText("Connecting with Database................");
		DbUtil util=new DbUtil();
		time.sleep(1000);
		lblStatus.setText("Database Connected.............");

		time.sleep(1000);
		lblStatus.setText("Updating Databse.............");

		time.sleep(1000);
		lblStatus.setText("Database Updated...");

		time.sleep(1000);
		lblStatus.setText("Preparing Login Interface...........");

		time.sleep(1000);




		//Login login = new Login();
		LogInSystem li=new LogInSystem();

		this.dispose();
	}

	catch(InterruptedException ie)
	{

	}
  }

  public static void main(String[] args)
  {
    SplashScreen splashScreen = new SplashScreen();
  }
}