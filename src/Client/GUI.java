package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class GUI {
	public static  String path="";
	public static String testName="";
	public static boolean flag=false;
	private JFrame frmPerfmonAutomation;
	private JTextField textField;
	private JTextField textField_1;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void GUIInvoke() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmPerfmonAutomation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	public GUI(String path){
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPerfmonAutomation = new JFrame();
		frmPerfmonAutomation.setTitle("PerfMon Automation");
		frmPerfmonAutomation.setBounds(100, 100, 450, 300);
		frmPerfmonAutomation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerfmonAutomation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 20);
		frmPerfmonAutomation.getContentPane().add(panel);
		
		JLabel lblWelcomePerformance = new JLabel("Welcome , Performance Tester");
		panel.add(lblWelcomePerformance);
		
		JLabel lblTestName = new JLabel("Test Name");
		lblTestName.setBounds(31, 44, 66, 14);
		frmPerfmonAutomation.getContentPane().add(lblTestName);
		
		textField = new JTextField();
		textField.setBounds(201, 38, 126, 20);
		frmPerfmonAutomation.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblOutputLogDirectory = new JLabel("OutPut Log Directory");
		lblOutputLogDirectory.setBounds(31, 75, 126, 14);
		frmPerfmonAutomation.getContentPane().add(lblOutputLogDirectory);
		
		textField_1 = new JTextField();
		textField_1.setBounds(201, 69, 145, 20);
		frmPerfmonAutomation.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel Output = new JLabel("");
		Output.setBounds(31, 167, 360, 83);
		frmPerfmonAutomation.getContentPane().add(Output);
		
		JButton btnStartTest = new JButton("Start Test");
		btnStartTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testName=textField.getText();
				path=textField_1.getText();
				Output.setText("Sucess Performance Tester Test is on Way"+'\n'+"OutputLogPath :"+path+'\n');
			}
		});
		btnStartTest.setAction(action);
		btnStartTest.setBounds(31, 118, 94, 23);
		frmPerfmonAutomation.getContentPane().add(btnStartTest);
		
		JButton btnStopTest = new JButton("Stop Test");
		btnStopTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			flag=true;
			System.exit(0);
			}
		});
		btnStopTest.setAction(action_1);
		btnStopTest.setBounds(176, 118, 89, 23);
		frmPerfmonAutomation.getContentPane().add(btnStopTest);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Start Test");
			putValue(SHORT_DESCRIPTION, "Starts The Test");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Stop Test");
			putValue(SHORT_DESCRIPTION, "Stops The Test");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
