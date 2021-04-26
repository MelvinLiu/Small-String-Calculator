package calculator.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculator.coreCalculator.CalculatorCentral;

/*
//CLASS INFO
*Class Name - VidMobCalculatorTesting.java
*Class Purpose - Unit testing

//TEST CASES LOOKUP
Test Case #1 - #7: Provided by the instruction
Test Case #8 - Empty input
Test Case #9 - Math symbol only input
Test Case #10 - Inbalance Parentheses
Test Case #11 - Balanced Parentheses but no numbers
Test Case #12 - Illegal Symbols Inputs
Test Case #13 - Multiple nesting notation with decimal and negative numbers
Test Case #14 - Inbalanced parentheses in multiple nesting notation with decimal and negative numbers
Test Case #15 - Decimal only
Test Case #16 - Decimal error
*/

class VidMobCalculatorTesting {

	@Test
	void test() {
		
		//Test Case #1 - #7 - Provided by the instruction
		CalculatorCentral testCase1 = new CalculatorCentral("1 + 2");
		String testCase1Result = testCase1.processingCentral();
		assertEquals("3.0", testCase1Result);
		
		CalculatorCentral testCase2 = new CalculatorCentral("4*5/2");
		String testCase2Result = testCase2.processingCentral();
		assertEquals("10.0", testCase2Result);
		
		CalculatorCentral testCase3 = new CalculatorCentral("-5+-8--11*2");
		String testCase3Result = testCase3.processingCentral();
		assertEquals("9.0", testCase3Result);
		
		CalculatorCentral testCase4 = new CalculatorCentral("-.32                  /.5");
		String testCase4Result = testCase4.processingCentral();
		assertEquals("-0.64", testCase4Result);
		
		CalculatorCentral testCase5 = new CalculatorCentral("(4-2)*3.5");
		String testCase5Result = testCase5.processingCentral();
		assertEquals("7.0", testCase5Result);
		
		CalculatorCentral testCase6 = new CalculatorCentral("2+-+-4");
		String testCase6Result = testCase6.processingCentral();
		assertEquals("Syntax error. Please try again.", testCase6Result);
		
		CalculatorCentral testCase7 = new CalculatorCentral("19 + cinnamon");
		String testCase7Result = testCase7.processingCentral();
		assertEquals("Invalid input. Please try again.", testCase7Result);

		//Test Case 8 - Empty input
		CalculatorCentral testCase8 = new CalculatorCentral("");
		String testCase8Result = testCase8.processingCentral();
		assertEquals("The text field is empty. Please enter your input.", testCase8Result);
		
		//Test Case #9 - Math symbol only input
		CalculatorCentral testCase9 = new CalculatorCentral("----+++");
		String testCase9Result = testCase9.processingCentral();
		assertEquals("Syntax error. Please try again.", testCase9Result);
		
		//Test Case #10 - Inbalance Parentheses
		CalculatorCentral testCase10 = new CalculatorCentral("9+0)");
		String testCase10Result = testCase10.processingCentral();
		assertEquals("Parentheses error. Please try again.", testCase10Result);
		
		//Test Case #11 - Balanced Parentheses but no numbers
		CalculatorCentral testCase11 = new CalculatorCentral("()(()()()()()()()()()()");
		String testCase11Result = testCase11.processingCentral();
		assertEquals("Parentheses error. Please try again.", testCase11Result);
		
		//Test Case #12 - Illegal Symbols Inputs
		CalculatorCentral testCase12 = new CalculatorCentral("@@@@@@@@@@@@@@@@1233+3665{}{}{}{{{}}}}}");
		String testCase12Result = testCase12.processingCentral();
		assertEquals("Invalid input. Please try again.", testCase12Result);
		
		//Test Case #13 - Complex nesting with decimal and negative numbers
		CalculatorCentral testCase13 = new CalculatorCentral("-4*((9+1)-100)+39*871*((9.10293*-1.987+((8+69*.36566))*56))/100000");
		String testCase13Result = testCase13.processingCentral();
		assertEquals("985.9884491079921", testCase13Result);
		
		//Test Case #14 - Inbalanced parentheses in multiple nesting notation with decimal and negative numbers
		CalculatorCentral testCase14 = new CalculatorCentral("-4*((9+1)-100)+39*871*((9.10293*-1.987+((8+69*.36566))*56)/100000");
		String testCase14Result = testCase14.processingCentral();
		assertEquals("Parentheses error. Please try again.", testCase14Result);
		
		//Test Case #15 - Decimal only
		CalculatorCentral testCase15 = new CalculatorCentral(".32  / -.5 / .1 / .9 /    .19 * .30");
		String testCase15Result = testCase15.processingCentral();
		assertEquals("-11.228070175438596", testCase15Result);
		
		//Test Case #15 - Decimal error
		CalculatorCentral testCase16 = new CalculatorCentral("0..32/.30");
		String testCase16Result = testCase16.processingCentral();
		assertEquals("Extra decimal point. Please try again.", testCase16Result);
	}
}
