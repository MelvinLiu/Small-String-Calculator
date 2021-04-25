//The gate keeper's goal is to perform a sanity check on user's string input
//Check all corner cases before going through the main calculator

package calculator.coreCalculator;

import java.util.HashSet;
import java.util.Stack;

/*
____________________________CLASS INFO___________________________________________________
|*Class Name          - GateKeeper.java													|	
|*Class Purpose       - to perform a sanity check on user's input string				|
|*Class Dependency    - created by CalculatorCentral.java class							|
|*					  - return the sanity check result back to CalculatorCentral class	|															
|*APIs/Libraries used - java.util.HashSet and Stack										|												
*****************************************************************************************

____________________________CONSTRUCTOR / METHODS INFO_______________________________________________
|*1. sanityCheck(String)												       						|
|*		Purpose				- to perform a sanity check on user's string input						|					
|*							- to perform 5 types (9 cases) of checks (case details are below.)		|					
|*		Dependency		  	- called by CalculatorCentral class										|																		
|*					  		- return calculation result back to CalculatorCentral class		     	|					
|*		Visibility	  	    - public																|					
|*		Intake parameters   - inputString from CalculatorCentral class								|														
|*		Return type			- sanity check results (either an error message or a pass stamp)	    |          
|*																									|
|*2. isAOperator(char)												       							|							
|*		Purpose				- to check if a character is an operator								|												
|*		Dependency		  	- called by sanityCheck method											|										
|*		Visibility	  	    - private																|											
|*		Intake parameters   - one character from sanityCheck method									|																	
|*		Return type			- boolean	                                   							|
*****************************************************************************************************

___________Gate Keeper Sanity Check Cases________________________________________________
|Case #1: If the input string is null / empty 											|
|Case #2 - Unacceptable symbols (including letters, [], {}, @)							|
|Case #3 - Operators related cases														|
|	    #3.1 - If the first char is not '-' ('-' at index 0 is accepted.)				|
|	    #3.2 - If there are more than two operators in a row							|
| 	    #3.3 - If there are two operators in a row and the second operator is not a '-' |
|       #3.4 - If the starting 2 char are both '-'										|
|Case #4 - Two decimal points next to each other										|
|Case #5 - Valid Parentheses															|
|       #5.1 - Check for any empty '()'													|
|       #5.2 - Parentheses must be in pair												|
*****************************************************************************************
*/

public class GateKeeper {
	
	public String sanityCheck(String inputString) {
		
		//Preparing works for sanity check - 
		//Remove all spaces
		inputString = inputString.replaceAll("\\s", "");
		//Error messages
		String emptyInput = "The text field is empty. Please enter your input.";
		String invalidMsg = "Invalid input. Please try again.";
		String syntaxErrorMsg = "Syntax error. Please try again.";
		String decimalErrorMsg = "Extra decimal point. Please try again.";
		String parenthesesErrorMsg = "Parentheses error. Please try again.";
		String illegalInput = "Illegal Input. Please try again.";
		//Create a set for all acceptable characters for O(1) lookup
		HashSet<Character> acceptableCharactersSet = new HashSet<>();
		char[] acceptableCharacters = {'0','1','2','3','4','5','6','7','8','9','0','+','-','*','/','(',')','.'};
		for (int i = 0; i < acceptableCharacters.length; i++) {
			acceptableCharactersSet.add(acceptableCharacters[i]);
		}
		//Create a stack to valid parentheses balance
		Stack<Character>stackParentheses = new Stack<>();
		
		//Case #1: If the input string is null / empty -
		if (inputString.length() == 0 || inputString == null) {
			return emptyInput;
		}
		
		try {
			//Main sanity check loop
			//Case #2 - Unacceptable symbols (including letters, [], {}, @)
			//Case #3 - Operators related cases
			//	   #3.1 - If the first char is not '-' ('-' at index 0 is accepted.)
			//	   #3.2 - If there are more than two operators in a row
			//	   #3.3 - If there are two operators in a row and the second operator is not a '-' 
			//     #3.4 - If the starting 2 char are both '-'
			//Case #4 - Decimal related
			//Case #5 - Valid Parentheses
			//     #5.1 - Check for any empty '()'
			//     #5.2 - Parentheses must be in pair
			
			for (int index = 0; index < inputString.length(); index++) {
				
				//Case #2 - Unacceptable symbols (including letters)
				if(!acceptableCharactersSet.contains(inputString.charAt(index))) {
					//System.out.println("huh");
					return invalidMsg;
				}
				
				//Case #3 - Operators related cases
				//     #3.1 - If the first char is not '-' ('-' at index 0 is accepted.)
				if(inputString.charAt(0) == '+' || inputString.charAt(0) == '*' || inputString.charAt(0) == '/') {
					return syntaxErrorMsg;
				}
				
				//	   #3.2 - If there are more than two operators in a row
				if(isAOperator(inputString.charAt(index)) && isAOperator(inputString.charAt(index+1)) && isAOperator(inputString.charAt(index+2))) {
					return syntaxErrorMsg;
				}
				
				//	   #3.3 - If there are two operators in a row and the second operator is not a '-' 
				if(isAOperator(inputString.charAt(index)) && isAOperator(inputString.charAt(index+1)) && inputString.charAt(index+1) != '-' && !isAOperator(inputString.charAt(index+2))){
					return syntaxErrorMsg;
				}
				
				//     #3.4 - If the starting 2 char are both '-'
				if(inputString.charAt(0) == '-' && inputString.charAt(1) == '-' ) {
					return syntaxErrorMsg;
				}
				
				//Case #4: Decimal related
				if(inputString.charAt(index)=='.' && inputString.charAt(index+1)=='.'){
					return decimalErrorMsg;
				}
				
				//Case #5: Valid Parentheses
				//     #5.1 - Check for any empty '()'
				if(inputString.charAt(index)=='(' && inputString.charAt(index+1)==')'){
					return parenthesesErrorMsg;
				}
				//     #5.2 - Parentheses must be in pair
				if(inputString.charAt(index) == '(') {
					stackParentheses.push(inputString.charAt(index));
				}
				else if(inputString.charAt(index) == ')' && !stackParentheses.isEmpty()) {
					stackParentheses.pop();
				}
				else if(inputString.charAt(index) == ')' && stackParentheses.isEmpty())
					return parenthesesErrorMsg;
			}
			
			//Case #5 (countinue, out of the while loop): 
			//Valid Parentheses - final check is the stack has any '(' left.
			if(!stackParentheses.isEmpty()) {
				return parenthesesErrorMsg;
			}
			//hardcoded pass stamp
			return "PassSanityCheck";
	}	
		//catch any other cases
		catch (Exception e){
			return illegalInput;
		}
	}

	//a private helper method to check if a char is an operator.
	//only used in sanityCheck method
	private boolean isAOperator(char eachCharacter) {
		if(eachCharacter == '/' || eachCharacter == '*' || eachCharacter == '+' || eachCharacter == '-') {
			return true;
		}
		return false;
	}
}