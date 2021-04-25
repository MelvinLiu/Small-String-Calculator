package calculator.coreCalculator;

import java.util.List;
import java.util.Stack;

/*
____________________________________CLASS INFO___________________________________________________
|*Class Name          - MainCalculatorCalculation.java											|	
|*Class Purpose       - to calculate the result based on RPN from MainCalculatorRPNConversion	|
|*Class Dependency    - created by CalculatorCentral.java class									|
|*					  - receive RPN List processed by MainCalculatorRPNConversion				|
|*					  - return the final result back to CalculatorCentral class					|															
|*APIs/Libraries used - java.util.Stack															|
|*Interface used	  - java.util.List															|												
*************************************************************************************************

Main Algorithm - 
If currnt token is an operator, pop two numbers from the stack, compute the result and push the result back to the stack.
If it is a number, push it into the stack. 
The last element in the stack is the final result.

*Algorithm Sources - 
*https://www.geeksforgeeks.org/evaluate-the-value-of-an-arithmetic-expression-in-reverse-polish-notation-in-java/

____________________________CONSTRUCTOR / METHODS INFO_______________________________________________________
|*1. calculateRPN(List)												       									|
|*		Purpose				- to calculate the final result											  		|									
|*		Dependency		  	- called by CalculatorCentral class												|
|*							- receive RPN List processed by MainCalculatorRPNConversion						|																			
|*					  		- return final result back to CalculatorCentral class	     			   		|					
|*		Visibility	  	    - public																		|					
|*		Intake parameters   - RPN List processed by MainCalculatorRPNConversion								|														
|*		Return type			- Double    																	|
|*																											|          
|*2. isANumber (String)																						|						
|*		Purpose				- to check if a character is a number											|												
|*		Dependency		  	- called by convertInfixToRPN method											|										
|*		Visibility	  	    - private																		|											
|*		Intake parameters   - String 																		|																	
|*		Return type			- boolean	                                   									|
*************************************************************************************************************
*/
//if currnt token is an operator, pop two numbers from the stack, compute the result and push the result back to the stack.
//if it is a number, push it into the stack. 

public class MainCalculatorCalculation {
	
	public Double calculateRPN(List<String> tokens) {
		
        Stack<Double> calculateRPNStack=new Stack<>();
        
        int n = tokens.size();
        
        for(int i = 0;i<n;i++){
        	
    		//if it is a number, push it into the stack. 
            if(isANumber(tokens.get(i))){
            	calculateRPNStack.push(Double.parseDouble(tokens.get(i)));
            }
    		//if currnt token is an operator, pop two numbers from the stack, compute the result and push the result back to the stack.
            else{
            	Double num2= calculateRPNStack.pop();
            	Double num1= calculateRPNStack.pop();
                switch (tokens.get(i)){
                    case "-": calculateRPNStack.push(num1-num2); break;
                    case "+": calculateRPNStack.push(num1+num2); break;
                    case "/": calculateRPNStack.push(num1/num2); break;
                    case "*": calculateRPNStack.push(num1*num2); break;
                }
            }
        }
        Double finalResult = calculateRPNStack.pop();
        //At the end, pop the final value from the stack. This is the result of this calculation.
        //Pass it back to CalculatorCentral class. 
        return finalResult;
    }

    private boolean isANumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}