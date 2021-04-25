package calculator;

import calculator.userInterface.WelcomeWindow;

/*
_______________________CLASS INFO________________________
|*Class Name          - AppRunner.java					|
|*Class Purpose       - to start the program			|
|*Class Dependency    - creates WelcomeWindow class		|
|*APIs/Libraries used - n/a								|	
*********************************************************	

________________CONSTRUCTOR / METHODS INFO________________________
|*1. Main														 |
|*		Purpose		      - to create a new WelcomeWindow Object |
|*		Dependency		  - calls and create WelcomeWindow object|
|*		Visibility	  	  - public								 |
|*		Intake parameters - String[] args						 |
|*		Return type 	  - void								 |
******************************************************************
*/

public class AppRunner {

    public static void main(String[] args) {
    	WelcomeWindow startTheProgram = new WelcomeWindow();
	}
}