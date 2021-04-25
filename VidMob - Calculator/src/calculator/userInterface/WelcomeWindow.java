package calculator.userInterface;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
____________________________CLASS INFO___________________________________________________
|*Class Name          - WelcomeWindow.java												|
|*Class Purpose       - to create the welcome window and direct user to the main page.	|
|*Class Dependency    - created by AppRunner class										|
|				      - creates MainWindow class										|
|*APIs/Libraries used - javax.swing and java.awt										|
|*Implements		  - ActionListener interface										|		
*****************************************************************************************

____________________________CONSTRUCTOR / METHODS INFO__________________________________
|*1. WelcomeWindow (Constructor)													   |
|*		Purpose		      - to build welcome window and set window's visibility to true|
|*		Dependency		  - called by AppRunner.class								   |		
|*		Visibility	  	  - public													   |
|*		Intake parameters - n/a						                                   |
|																					   |
|*2. buildWelcomePage()																   |			
|*		Purpose		      - to build welcome window 								   |
|*		Dependency		  - called by WelcomeWindow	constructor						   |
|*						  - calls actionPerformed(e) method							   |
|*		Visibility	  	  - private													   |
|*		Intake parameters - n/a						                                   |
|*		Return type  	  - void					                                   |
|																					   |
|*3. actionPerformed(ActionEvent e)													   |			
|*		Purpose		      - to create MainWindow object and direct user to main page   |
|*						  - implemented from ActionListener interface				   |	
|*		Dependency		  - called by buildWelcomePage() method						   |
|*						  - calls MainWindow constructor							   |
|*		Visibility	  	  - public 													   |
|*		Intake parameters - ActionEvent e			                                   |
|*		Return type  	  - void					                                   |
****************************************************************************************
*/

public class WelcomeWindow implements ActionListener{
	
	//all private variables to build elements on the welcome window
	private JFrame welcomeWindowFrame;
	private JPanel welcomeWindowPanel;
	private JPanel imagePanel;
	private JLabel logoImageLabel;
	private JLabel symbolImageLabel;
	private JTextPane titleText;
	private JTextPane authorText; 
	private JButton startButton;
	
	//Constructor
	public WelcomeWindow() {
		buildWelcomePage();
		welcomeWindowFrame.setVisible(true);
	}
	
	//private method to build the welcome page
	private void buildWelcomePage() {
		// Build welcome window frame
		welcomeWindowFrame = new JFrame();
		welcomeWindowFrame.setTitle("VidMob Calculator");
		welcomeWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeWindowFrame.setBounds(100, 100, 800, 500);
		welcomeWindowFrame.setResizable(false);
		
		// Create window panel on top of the welcome window 
		welcomeWindowPanel = new JPanel();
		welcomeWindowPanel.setBackground(Color.WHITE);
		welcomeWindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		welcomeWindowPanel.setLayout(null);
        welcomeWindowFrame.setContentPane(welcomeWindowPanel);
		
        // Create Panel for image, set the background color
        imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBounds(500, 0, 400, 500);
        imagePanel.setLayout(null);
        welcomeWindowPanel.add(imagePanel);

        // Put in images in
        symbolImageLabel = new JLabel("");
        symbolImageLabel.setIcon(new ImageIcon((WelcomeWindow.class.getResource("/calculator/images/picture2_symbols.jpg"))));
        symbolImageLabel.setBounds(30, 0, 300, 650);
        imagePanel.add(symbolImageLabel);
        
        logoImageLabel = new JLabel();
        logoImageLabel.setIcon(new ImageIcon((WelcomeWindow.class.getResource("/calculator/images/picture1_Logo.jpg"))));
        logoImageLabel.setBounds(0, 10, 300, 250);
        imagePanel.add(logoImageLabel);

        // Show project title
        titleText = new JTextPane();
        titleText.setName("Title");
        titleText.setEditable(false);
        titleText.setFont(new Font("Arial", Font.ITALIC, 35));
        titleText.setText("Welcome!"
        				+ "\nVidMob Calculator");
        titleText.setBounds(50, 50, 300, 100);
        welcomeWindowPanel.add(titleText);
     
        // Show the author
        authorText= new JTextPane();
        authorText.setName("Author");
        authorText.setEditable(false);
        authorText.setFont(new Font("Arial", Font.ITALIC, 20));
        authorText.setText("Developed in Java"
        				  +"\nby Yucheng 'Melvin' Liu"
        				  +"\n"
        				  +"\nv1.0 (April 2021)");
        authorText.setBounds(50, 200, 300, 100);
        welcomeWindowPanel.add(authorText);
  
        // Create start button
        startButton = new JButton();
        startButton.setText("Click Here to Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setBounds(100, 350, 250, 42);
        welcomeWindowPanel.add(startButton);
        startButton.addActionListener(this);
	}
	
	//Action Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		//When user clicks the "Click Here to Start" button, it will start the mainWindow and close the welcome window.
        MainWindow mainWindow = new MainWindow();
        //hide the welcome window
        welcomeWindowFrame.setVisible(false);	
	}
}
