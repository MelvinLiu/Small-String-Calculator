package calculator.coreCalculator;

import java.util.List;
import calculator.coreCalculator.GateKeeper;
import calculator.coreCalculator.MainCalculatorCalculation;
import calculator.coreCalculator.MainCalculatorRPNConversion;
import calculator.coreCalculator.MainCalculatorStringHandler;

/*
____________________________CLASS INFO_______________________________________________________________________________________________________
|*Class Name          - CalculatorCentral.java																								|		
|*Class Purpose       - be the center of passing variables amongst calculation related classes												|
|*Class Dependency    - created by MainWindow class and recieve user input from MainWindow Class' method 									|	
|*					  - creates GateKeeper class to validate user input																		|
|*					  - creates MainCalculatorStringHandler class to break string into a list (infix notation)								|
|*					  - creates MainCalculatorRPNConversion class to convert the list (infix notation) into reverse polish notation (RPN)	|
|*					  - creates MainCalculatorCalculation class to calculate the result														|
|*					  - return calculation result back to MainWindow class																	|
|*APIs/Libraries used - java.util.List																										|
*********************************************************************************************************************************************

____________________________CONSTRUCTOR / METHODS INFO___________________________________________________________________________________________
|*1. CalculatorCentral (Constructor)												       														|
|*		Purpose				- create CalculatorCentral and receive input string from MainWindow class											| 
|*		Dependency		  	- called by MainWindow class																						|
|*		Visibility	  	    - public																											|
|*		Intake parameters   - String inputStringFromMainWindow																					|
|*																																				|
|*2. processingCentral()												       																	|
|*		Purpose				- be the center of passing variables amongst calculation related classes											|
|*							- implements various 'Watch Towers" to monitor intermediate results 												|
|*		Dependency		  	- called by MainWindow class																						|
|*							- creates GateKeeper class to validate user input																	|
|*							- creates MainCalculatorStringHandler class to break string into a list (infix notation)							|	
|*							- creates MainCalculatorRPNConversion class to convert the list (infix notation) into reverse polish notation (RPN)	|
|*					  		- creates MainCalculatorCalculation class to calculate the result													|
|*					  		- return calculation result back to MainWindow class																|
|*		Visibility	  	    - public																											|
|*		Intake parameters   - n/a																												|
|*		Return type			- String finalResultReadyToDisplay or String errorMessageReadyToDisplay	                                   			|
*************************************************************************************************************************************************
*/

public class CalculatorCentral {

	private String inputString;
	
	//Constructor - to receive user input from the main window
	public CalculatorCentral(String inputStringFromMainWindow){
		inputString = inputStringFromMainWindow;
	}
	
	public String processingCentral() {
		
		//This method will be the center of passing variables amongst classes
		//1. GateKeeper class is to validate user input;
		//2. MainCalculatorStringHandler class is to break string into a list (infix notation, our normal way of writing a notation), where it needs to identify integers, math symbols, and parentheses;
		//3. MainCalculatorRPNConversion class is to convert the list from step 2 into reverse polish notation (RPN);
		//4. MainCalculatorCalculation class is to take RPN from step 3 and calculate the result. The result will come back to this class (CalculatorCentral) and ready to display.
		
		//Run the sanity check on the input string
		GateKeeper newGateKeeper = new GateKeeper();
		String sanityCheckResultFromGateKeeper = newGateKeeper.sanityCheck(inputString);
		
		if(sanityCheckResultFromGateKeeper.equals("PassSanityCheck")) {
			//Clean string passed gate keeper
			//Rename it to 'passedInputString'
			String passedInputString = inputString;
			
			//Step #1 - process the string and turn it into a list
	    	MainCalculatorStringHandler stringHandler = new MainCalculatorStringHandler();
	    	List<String> listFromStringHandler = stringHandler.inputStringToList(passedInputString);
	    	System.out.println("Watch Tower - listFromStringHandler " + listFromStringHandler);
	    	
			//Step #2 - convert the list into reversed polish notation
	    	MainCalculatorRPNConversion rpnConversion = new MainCalculatorRPNConversion();
	    	List<String> listFromRPNConversion = rpnConversion.convertInfixToRPN(listFromStringHandler);
	    	System.out.println("Watch Tower - ListFromRPNConversion " + listFromRPNConversion);
	    	
			//Step #3 - using stacks to perform calculation on the reversed polish notation
	    	MainCalculatorCalculation calculation = new MainCalculatorCalculation();
	    	Double finalResultInDouble = calculation.calculateRPN(listFromRPNConversion);
	    	String finalResultReadyToDisplay = Double.toString(finalResultInDouble);
	    	System.out.println("Watch Tower - finalResultReadyToDisplay " + finalResultReadyToDisplay);
	    	
	    	return finalResultReadyToDisplay;
		}
		else {
			//Other strings that didnt pass the sanity check at gate keeper
			//Their error messages are ready to display.
			String errorMessageReadyToDisplay = sanityCheckResultFromGateKeeper;
			System.out.println("Watch Tower - errorMessageReadyToDisplay" + errorMessageReadyToDisplay);
	    	return errorMessageReadyToDisplay;
		}
	}
}
