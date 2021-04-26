package calculator.coreCalculator;
import java.util.ArrayList;
import java.util.List;
/*
//CLASS INFO
*Class Name - MainCalculatorStringHandler.java
*Class Purpose - to break string into a list (infix notation)
*Class Dependency - created by CalculatorCentral.java class; receive strings that passed Gate Keeper class; return the indix notation array back to CalculatorCentral class
*APIs/Libraries used - java.util.ArrayList
*Interface used - java.util.List

//CONSTRUCTOR / METHODS INFO
*1. inputStringToList(String)
*Purpose - to break string into a list (infix notation)	
*Dependency - called by CalculatorCentral class and return list back to CalculatorCentral class
*Visibility	- public
*Intake parameters - inputString from CalculatorCentral class (NOTE: string passed Gate Keeper.
*Return type - indix notation in List format
*
*2. isAOperator(int)
*3. isARightParenthesis(int)
*4. isANumber (int)
*5. isADecimal(int)
*Purpose - to check if a character is an operator/ right parenthesis / number / decimal	
*Dependency	- called by inputStringToList method
*Visibility - private
*Intake parameters - index
*Return type - boolean	                                   					

//StringBuffer Append Cases
Case #1 - If met an number, keep appending the rest of numbers and decimal point
Case #2 - If met a decimal point, and its at index == 0 or its index - 1 is an operator. Jump over the decimal point. Append the rest of the number. Once done, give the decimal point back -> num = num / 10^length() 
Case #3 - If it is a operator or parenthesis except '-', directly add it to the return array
Case #4 - Distingish negative sign from the minus sign
Case #4.1 '-' must be a minus sign if the char before it is a number or a ')';
Case#4.2 The rest must be negative sign. 

*/
public class MainCalculatorStringHandler {
	
	private String inputString; 
	
    public List<String> inputStringToList(String inputStringFromCalculatorCentral){

    	inputString = inputStringFromCalculatorCentral.replaceAll(" ","");
    	int index = 0;
        StringBuffer num = new StringBuffer();
        List<String> returnList = new ArrayList<>();
        
        while (index < inputString.length()){

            //case #1 - If met an number
            if(isANumber(index)){
               //keep apending the rest of numbers or decimal into the string buffer
               //stop when the next index is not a number or index - out of while loop
               while (index != inputString.length() && ((isANumber(index)) || isADecimal(index))){
                      num.append(inputString.charAt(index));
                      index++;
                	 }
               //add the appended number to return list
               returnList.add(num.toString());
               //reset StringBuffer num back to zero for next number
               num.setLength(0);
            }
            
            //case #2 - If met a decimal point, and its at index == 0 or its index - 1 is an operator
            //example .32 -> 0.32
            else if ((isADecimal(index) && index == 0) || (isADecimal(index) && index > 0 && isAOperator(index - 1))){
                //jump over the decimal
            	index = index + 1;
                //append the number
                while (index != inputString.length() && ((isANumber(index)) || isADecimal(index))){
                    num.append(inputString.charAt(index));
                    index++;
                }
                //Now we have 32 and need to turn it into 0.32
                //0.32 = 32 / 10^length = 32 / 10^2 = 32 / 100
                //.32(raw input) -> 0.32
                String tempNumInString = num.toString();
                Double tempDouble = Double.valueOf(tempNumInString) / Math.pow(10, tempNumInString.length());
                String finalNuminString = Double.toString(tempDouble);
                returnList.add(finalNuminString);
                //reset StringBuffer num back to zero for next number
                num.setLength(0);
            }
                       
            //Case #3 - If char is +, *, /, (, or ), directly add it to the array.
            else if(inputString.charAt(index) != 45) {
            	returnList.add(String.valueOf(inputString.charAt(index)));
                index++;
            }
            
            //Case #4 - If char is '-'
            //At this point, we will only have '-' left. Let us distinguish the negative sign from the minus symbol
            else {
            	   //'-' must be a minus sign if the char before it is a number or a ')'
            	if (index >= 1 && (isANumber(index - 1) || isARightParenthesis(index - 1))) {
            		
	                returnList.add(String.valueOf(inputString.charAt(index)));
	                index++;
	            }
	                //The rest must be negative sign
	            else{
	            	//jump over the negative sign
	                index = index + 1;
	                while (index != inputString.length() && ((isANumber(index)) || isADecimal(index))){
	                    num.append(inputString.charAt(index));
	                    index++;
	                }
	                
	                // convert it back to negative value 
	               	String tempStr = num.toString();
	                Double tempDouble = -Double.valueOf(tempStr);
	                String finalStr = Double.toString(tempDouble);
	                	
	                returnList.add(finalStr);
	                    
	                //reset StringBuffer num back to zero for next number
	                num.setLength(0);
	            	}
            }
        }
        return returnList;
    }
    
	//some private helper methods to check if statements.
	//only used in sanityCheck method
    private boolean isARightParenthesis(int index) {
    	if (inputString.charAt(index) == 41) {
    		return true;
    	}
    	return false;
    }
    
    private boolean isANumber (int index) {
    	if (inputString.charAt(index)>= 48 && inputString.charAt(index)<=57) {
    		return true;
    	}
    	return false;
    }
    
    private boolean isADecimal (int index) {
    	if (inputString.charAt(index)== 46) {
    		return true;
    	}
    	return false;
    }

	private boolean isAOperator(int index) {
		if(inputString.charAt(index) == '/' || inputString.charAt(index) == '*' || inputString.charAt(index) == '+' || inputString.charAt(index) == '-') {
			return true;
		}
		return false;
	}
}