package calculator.coreCalculator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
____________________________________CLASS INFO___________________________________________________
|*Class Name          - MainCalculatorRPNConversion.java										|	
|*Class Purpose       - to convert the list (infix notation) into reverse polish notation (RPN)	|
|*Class Dependency    - created by CalculatorCentral.java class									|
|*					  - receive ArrayList processed by MainCalculatorStringHandler				|
|*					  - return the RPN array back to CalculatorCentral class					|															
|*APIs/Libraries used - java.util.HashMap, LinkedList, Stack									|
|*Interface used	  - java.util.List, Map														|												
*************************************************************************************************

*Algorithm Sources - 
*https://en.wikipedia.org/wiki/Reverse_Polish_notation
*https://www.geeksforgeeks.org/arithmetic-expression-evalution/

____________________________CONSTRUCTOR / METHODS INFO_______________________________________________________
|*1. convertInfixToRPN(List)												       							|
|*		Purpose				- to convert the list (infix notation) into reverse polish notation (RPN)  		|									
|*		Dependency		  	- called by CalculatorCentral class												|
|*							- receive ArrayList processed by MainCalculatorStringHandler					|																			
|*					  		- return RPN list (LinkedList) back to CalculatorCentral class	        		|					
|*		Visibility	  	    - public																		|					
|*		Intake parameters   - infix array processed by MainCalculatorStringHandler							|														
|*		Return type			- Linked List in RPN format	    												|
|*																											|          
|*2. isANumber (String)																						|						
|*		Purpose				- to check if a character is a number											|												
|*		Dependency		  	- called by convertInfixToRPN method											|										
|*		Visibility	  	    - private																		|											
|*		Intake parameters   - String 																		|																	
|*		Return type			- boolean	                                   									|
*************************************************************************************************************
*/

public class MainCalculatorRPNConversion {
	 
	   public List<String> convertInfixToRPN(List<String> infixArrayFromStringHandler) {
	       
	       Stack<String> RPNConversionStack = new Stack<>();
	       
	       //Use hashMap to track each token's priority and also for O(1) lookup time
	 	   Map<String, Integer> priorityMap = new HashMap<>();
		        priorityMap.put("/", 5);
		        priorityMap.put("*", 5);
		        priorityMap.put("+", 4);
		        priorityMap.put("-", 4);
		        priorityMap.put("(", 0);
		   
		   //This is the return list. Here I chose to use linked list for O(1) adding time. It will be more efficient than using arraylist.     
		   List<String> RPNConvertedList = new LinkedList<>();

	        for (String token : infixArrayFromStringHandler) {
	        	
	        	//if it is a ( or )
	            if ("(".equals(token)) {
	            	RPNConversionStack.push(token);
	                continue;
	            }
	 
	            if (")".equals(token)) {
	                while (!"(".equals(RPNConversionStack.peek())) {
	                	RPNConvertedList.add(RPNConversionStack.pop());
	                }
	                RPNConversionStack.pop();
	                continue;
	            }
	            // If it is an operator
	            if (priorityMap.containsKey(token)) {
	                while (!RPNConversionStack.empty() && priorityMap.get(token) <= priorityMap.get(RPNConversionStack.peek())) {
	                	RPNConvertedList.add(RPNConversionStack.pop());
	                }
	                RPNConversionStack.push(token);
	                continue;
	            }
	            
	            //If it is a number, directly push into the RPN converted list
	            if (isANumber(token)) {
	            	RPNConvertedList.add(token);
	                continue;
	            }
	 
	            throw new IllegalArgumentException("Invalid input");
	        }
	        
	        //Add the rest of tokens to the RPNConvertedList
	        while (!RPNConversionStack.isEmpty()) {
	        	RPNConvertedList.add(RPNConversionStack.pop());
	        }
	 
	        return RPNConvertedList;
	    }
	   
		private boolean isANumber(String str) {
		        try{
		            Double.valueOf(str);
		            return true;
		        } catch(Exception e){
		            return false;
		        }
		}
}