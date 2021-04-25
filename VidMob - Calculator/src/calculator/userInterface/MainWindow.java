package calculator.userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import calculator.coreCalculator.CalculatorCentral;

/*
____________________________CLASS INFO___________________________________________________
|*Class Name          - MainWindow.java													|
|*Class Purpose       - to create the main window 										|
|*					  - to receive user input and display the calculation result		|
|*Class Dependency    - created by Welcome Window class									|
|*				      - creates CalculatorCentral class object							|
|*					  - received calculation result from CalculatorCentral object class |
|*APIs/Libraries used - javax.swing and java.awt										|
|*Implements		  - ActionListener interface										|		
*****************************************************************************************

____________________________CONSTRUCTOR / METHODS INFO__________________________________
|*1. MainWindow (Constructor)													       |
|*		Purpose		      - to build main window and set window's visibility to true   |
|*		Dependency		  - called by WelcomeWindow.class							   |		
|*		Visibility	  	  - public													   |
|*		Intake parameters - n/a						                                   |
|*																					   |
|*2. buildMainWindow()																   |			
|*		Purpose		      - to build main window 									   |
|*						  - to take user input from text field						   |
|*						  - to display the calculation result						   |
|*		Dependency		  - called by MainWindow constructor						   |
|*						  - calls actionPerformed(e) method							   |
|*		Visibility	  	  - private													   |
|*		Intake parameters - n/a						                                   |
|*		Return type  	  - void					                                   |
|*																					   |
|*3. actionPerformed(ActionEvent e)													   |			
|*		Purpose		      - implemented from ActionListener interface				   |
|*						  - to create CalculatorCentral object and pass input string   |
|*						  - to receive calculation result from CalculatorCentral object|
|*						  - to clear input field and result field when clicking 'clear'|
|*		Dependency		  - called by buildWelcomePage() method						   |
|*						  - calls CalculatorCentral constructor						   |
|*		Visibility	  	  - public 													   |
|*		Intake parameters - ActionEvent e			                                   |
|*		Return type  	  - void					                                   |
****************************************************************************************
*/

public class MainWindow implements ActionListener{
	
	//all private variables to build elements on the main window
	private JFrame calWindowFrame;
	private JPanel calWindowPanel;
	private JPanel headerPanel;
	private JTextPane headerText;
	private JPanel footerPanel;
	private JTextPane instructionText;
	private JTextPane inputText;	
	private JTextField input;
	private JButton calButton;
	private JButton resetButton;
	private JTextPane resultText;		
	private JTextArea resultDisplayArea;
	
	//constructor
	public MainWindow(){
		buildMainWindow();
		calWindowFrame.setVisible(true);
	}
	
	private void buildMainWindow() {
		//Build main window frame
		calWindowFrame = new JFrame();
		calWindowFrame.setTitle("VidMob Calculator");
		calWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calWindowFrame.setBounds(100, 100, 800, 500);
		calWindowFrame.setResizable(false);

		calWindowPanel = new JPanel();
		calWindowPanel.setBackground(Color.WHITE);
		calWindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		calWindowPanel.setLayout(null);
		calWindowFrame.setContentPane(calWindowPanel);

		headerPanel = new JPanel();
		headerPanel.setBackground(Color.BLACK);
		headerPanel.setBounds(0, 80, 800, 5);
		headerPanel.setLayout(null);
		calWindowPanel.add(headerPanel);
        
		footerPanel = new JPanel();
		footerPanel.setBackground(Color.BLACK);
		footerPanel.setBounds(0, 450, 800, 5);
		footerPanel.setLayout(null);
		calWindowPanel.add(footerPanel);

		headerText = new JTextPane();
		headerText.setName("Title");
		headerText.setEditable(false);
		headerText.setFont(new Font("Arial", Font.ITALIC, 23));
		headerText.setText("VidMob Calculator");
		headerText.setBounds(300, 20, 800, 50);
		calWindowPanel.add(headerText);
		
		instructionText = new JTextPane();
		instructionText.setName("Input");
		instructionText.setEditable(false);
		instructionText.setFont(new Font("Arial", Font.ITALIC, 12));
		instructionText.setText("Instructions: "
				+ "\n 1. Take string as input. "
				+ "\n 2. Support positive, negative, and decimal numbers. "
				+ "\n 3. Support +, -, *, and / operations, with standard mathematical order of operations."
				+ "\n 4. Support parentheses with multiple nesting levels."
				+ "\n Example: (4-2) / 3.5");
		instructionText.setBounds(35, 85, 800, 100);
		calWindowPanel.add(instructionText);
		
		inputText = new JTextPane();
		inputText.setName("Input");
		inputText.setEditable(false);
		inputText.setFont(new Font("Arial", Font.BOLD, 20));
		inputText.setText("Enter your input here:");
		inputText.setBounds(100, 200, 130, 50);
		calWindowPanel.add(inputText);
		
		input = new JTextField();
		input.setName("User String");
		input.setBackground(Color.WHITE);
		input.setLocation(240, 210);
		input.setFont(new Font("Arial", Font.BOLD, 18));
		input.setSize(501, 40);	
		input.setVisible(true);
		input.setEditable(true);
		calWindowPanel.add(input);
		calWindowFrame.add(input);
		
		calButton = new JButton("Calculate");
		calButton.setBounds(490, 250, 250, 40);;
		calButton.setBackground(Color.GREEN);
		calButton.setFont(new Font("Calculate", Font.BOLD, 18));
		calButton.setVisible(true);;
		calButton.addActionListener(this);
		calWindowPanel.add(calButton);
		calWindowFrame.add(calButton);
		
		resetButton = new JButton("Clear");
		resetButton.setBounds(240, 250, 250, 40);;
		resetButton.setBackground(Color.LIGHT_GRAY);
		resetButton.setFont(new Font("Clear", Font.BOLD, 18));
		resetButton.setVisible(true);;
		resetButton.addActionListener(this);
		calWindowPanel.add(resetButton);
		calWindowFrame.add(resetButton);
		
		resultText = new JTextPane();
		resultText.setName("Input");
		resultText.setEditable(false);
		resultText.setFont(new Font("Arial", Font.BOLD, 20));
		resultText.setText("Result = ");
		resultText.setBounds(120, 347, 130, 50);
		calWindowPanel.add(resultText);
		calWindowFrame.add(resultText);
		
		resultDisplayArea = new JTextArea();
		resultDisplayArea.setName("Final Result");
		resultDisplayArea.setEditable(false);
		resultDisplayArea.setFont(new Font("Arial", Font.BOLD, 20));
		resultDisplayArea.setBounds(250, 350, 800, 50);
		calWindowPanel.add(resultDisplayArea);
		calWindowFrame.add(resultDisplayArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//action listner for the calculate button
		if(e.getSource() == calButton) {
			
			//Grab user input from input, the JTextField and pass it to CalculatorCentral class for processing
			CalculatorCentral newCalculator = new CalculatorCentral(input.getText());
			
			//return string from the CalculatorCentral class by calling its processingCentral() method.
			//display the result on the resultDisplayArea
			String calculationResult = newCalculator.processingCentral();
			
	    	resultDisplayArea.setText(null);
	    	resultDisplayArea.append(calculationResult);
	    	calWindowPanel.revalidate();
		}
		//action listner for the resetButton to clear the input field
		else {
			input.setText("");
			resultDisplayArea.setText("");
		}
	}
}