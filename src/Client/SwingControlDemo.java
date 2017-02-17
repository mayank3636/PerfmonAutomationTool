package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * @author M1030090,Mayank Upadhyaya
 *
 */
public class SwingControlDemo {
   public static  String path="";
   public static String testName="";
   public static boolean flag=false;
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   SwingControlDemo(String abc){
	   
   }

   public SwingControlDemo(){
      prepareGUI();
   }
   public String  Launchmain(){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showTextFieldDemo();
      while(path==""){
    	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {
  			System.out.println("ThreadException");
  		}  
      }
	return path;
     
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Client Tool Perfmon Automation");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(5, 5));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);
   }
   private void showTextFieldDemo(){
      headerLabel.setText("Welcome , User"); 
      JLabel  namelabel= new JLabel("Output Log Directory: ", JLabel.CENTER);
      JLabel TestName= new JLabel("Test Name",JLabel.LEFT);
      
      final JTextField userText = new JTextField(6);
      final JTextField TestText = new JTextField(6);
      JButton loginButton = new JButton("Start");
      JButton StopTest= new JButton("Stop Test");
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {    
        	 path=userText.getText();
            String data = "Username " + userText.getText();
            statusLabel.setText(data);        
         //path+=",";
         //path+=
            testName=TestText.getText();
         }
      }); 
      StopTest.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {    
         	 
        	  flag=true;
             statusLabel.setText("");        
          }
       });
    
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(StopTest);
      controlPanel.add(loginButton);
      controlPanel.add(TestName);
      controlPanel.add(TestText);
     
      
     // controlPanel.add(StopTest);
      mainFrame.setVisible(true);
	
   }
}