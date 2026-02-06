/*
 * Harrison Haas
 * This code asks for two numbers, and based on user inputs will either calculate or compare the two numbers
 * Either mode will consider the two numbers based on the operator or comparator provided by the user
 * The calculating option will either round to 3 digits or leave the number floating based on the user inputs
 * The comparining option will state whether the equation is true or false
 */
import java.util.Scanner;
public class Calculator {

	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
	/*
	 * Propmts the user to select between two modes: calc or compare, then prompts methods for either one
	 * If the user enters something else, they will be told to restart
	 */	
		
		System.out.println("Mode: (Calc/Compare)?");
		String mode = sc.nextLine().trim();
		
		boolean isCalc = mode.equalsIgnoreCase ("Calc");
		boolean isCompare = mode.equalsIgnoreCase("Compare");
		
		if (isCalc || isCompare) {
			if (isCalc) {
				calcMode();
			} else {
				compareMode();
			}
		} else {
			System.out.println("Please restart and enter 'Calc' or 'Compare'.");
		}
	
	}

	public static double firstNumber() {
		//Prompts the user to enter the first number in the desired equation
		
	
		System.out.println("Enter first number:");
		double firstNumber = sc.nextDouble();
		
		
		return firstNumber;	
	}

	public static double secondNumber() {
		//Prompts the user to enter the first number in the desired equation
		
		
		System.out.println("Enter second number");
		double secondNumber = sc.nextDouble();
		
		return secondNumber;
	}
	
	public static void calcMode() {
		/*
		 * Runs methods firstNumber, secondNumber, and operator and assigns each to a value as they run.
		 * It then runs decimalPlaces if operator returns a value other than "Error".
		 */
		double numOne = firstNumber();
		double numTwo = secondNumber();
		String op = operator();
		/*
		 * I used this if statement to guard against user input errors. 
		 * If method operator() returns "Error" the code will print out a restart staement and end.
		 * If the method returns anything else the code will continue along. 
		 * If I put the restart statement inside of operator() instead of in this if statement,
		 * the code would contine prompting the user after telling them to restart. 
		 */
		if (op == "Error") {
			System.out.println("Please restart and enter an accepted value.");
		} else {
		decimalPlaces(numOne, numTwo, op);
		}
	}
		public static String operator() {
		/*
		 * Prompts the user to choose an operator, (accpets the word or the symbol) then stores it
		 * If the user enters something that doesn't work, the method will return "Error" which promts the user to restart (in calcMode)
		 */
		
		System.out.println("Enter operator (Symbols are preferred):");
		String operatorInput = sc.nextLine().trim();
		
		boolean plus = operatorInput.equalsIgnoreCase("plus");
		boolean plusSymbol = operatorInput.equals("+");
		
		boolean minus = operatorInput.equalsIgnoreCase("minus");
		boolean minusSymbol = operatorInput.equals("-");
		
		boolean times = operatorInput.equalsIgnoreCase("times");
		boolean timesSymbol = operatorInput.equals("*");
		
		boolean divide = operatorInput.equalsIgnoreCase("divide");
		boolean divideSymbol = operatorInput.equals("/");
		
		String operatorResult = null;
		
		if (plus || plusSymbol) {
			operatorResult = "Addition";
		} else if (minus || minusSymbol) {
			operatorResult = "Subtraction";
		} else if (times || timesSymbol) {
			operatorResult = "Multiplication";
		} else if (divide || divideSymbol) {
			operatorResult = "Division";
		} else {
			operatorResult = "Error";
		}
		
		return operatorResult;
		
		}
		public static void decimalPlaces(double numOne, double numTwo, String op) {
		 // Prompts the user to select how many decimal places they want rounded to: as many as possible or 3
		
		System.out.print("Answer unrounded or to the three decimal places?\n(U/T):");
		String decimalPlaces = sc.nextLine().trim();
		
		boolean unrounded = decimalPlaces.equalsIgnoreCase("U");
		boolean thousandths = decimalPlaces.equalsIgnoreCase("T");
		
		if (unrounded || thousandths) {
			if (unrounded) {
				unroundedCalc(numOne, numTwo, op); 
			} else {
				thousandthsCalc(numOne, numTwo, op);
			}
		} else {
			System.out.println("Please restart and enter '0' or '3'.");
		}
		
		}
		public static void unroundedCalc(double numOne, double numTwo, String op) {
		//Calculates the result of the two chosen numbers with the chosen operator and prints the equation, no rounding
		if (op == "Addition") {
			double answer = numOne + numTwo;
			System.out.println(numOne + " + " + numTwo + " = " + answer);
		} else if (op == "Subtraction") {
			double answer = numOne - numTwo;
			System.out.println(numOne + " - " + numTwo + " = " + answer);
		} else if (op == "Multiplication") {
			double answer = numOne * numTwo;
			System.out.println(numOne + " * " + numTwo + " = " + answer);
		} else if (op == "Division") {
			if (numTwo == 0) {
				System.out.println("ERROR: DIVISION BY 0");
			} else {
				double answer = numOne / numTwo;
				System.out.println(numOne + " / " + numTwo + " = " + answer);
			}
			
		}else {
			System.out.println("How did you do this?!!!");
		}
		
		}
		public static void thousandthsCalc(double numOne, double numTwo, String op) {
		/*
		 * Calculates the result of the two chosen numbers with the chosen operator and prints the equation
		 * the answer will be to 3 decimal places
		 */
		if (op == "Addition") {
			double answer = numOne + numTwo;
			System.out.printf("%f + %f = %.3f", numOne, numTwo, answer);
		} else if (op == "Subtraction") {
			double answer = numOne - numTwo;
			System.out.printf("%f - %f = %.3f", numOne, numTwo, answer);
		} else if (op == "Multiplication") {
			double answer = numOne * numTwo;
			System.out.printf("%f * %f = %.3f", numOne, numTwo, answer);
		} else if (op == "Division") {
			double answer = numOne / numTwo;
			System.out.printf("%f / %f = %.3f", numOne, numTwo, answer);
		} else {
			System.out.println("How did you do this?!!");
		}
		
		}
		
	public static void compareMode() {
		/*
		 * Stores firstNumber, secondNumber, and comparator as values when they run,
		 * then runs compareResult if the comparator entered does not reuturn an error
		 */
		double numOne = firstNumber();
		double numTwo = secondNumber();
		String comp = comparator();
		if (comp == "Error") {
			System.out.println("Please restart and enter an accepted value from the list above.");
		} else {
		compareResult(numOne, numTwo, comp);
	}
	}
		public static String comparator() {
			/*
			 * Promts the user to enter a comparator, then stores it into 6 booleans
			 * Converts the 6 booleans into one string
			 * If the user inputs an unseported comparator, this method will return "Error"
			 */
		
		System.out.println("Enter comparator (<, <=, >, >=, ==, != ):");
		String comparatorInput = sc.nextLine().trim();
		
		boolean lessSymbol = comparatorInput.equalsIgnoreCase("<");
		boolean lessEqualsSymbol = comparatorInput.equalsIgnoreCase("<=");
		
		boolean greaterSymbol = comparatorInput.equalsIgnoreCase(">");
		boolean greaterEqualsSymbol = comparatorInput.equalsIgnoreCase(">=");
	
		boolean isSymbol = comparatorInput.equalsIgnoreCase("==");
		boolean notSymbol = comparatorInput.equalsIgnoreCase("!=");
		
		String comparatorResult = null;
		
		if (lessSymbol) {
			comparatorResult = "LessThan";
		} else if (lessEqualsSymbol) {
			comparatorResult = "LessOrEquals";
		} else if (greaterSymbol) {
			comparatorResult = "GreaterThan";
		} else if (greaterEqualsSymbol) {
			comparatorResult = "GreaterOrEquals";
		} else if (isSymbol) {
			comparatorResult = "IsEquals";
		} else if (notSymbol) {
			comparatorResult = "NotEquals";
		} else {
			comparatorResult = "Error";
		}
		
		return comparatorResult;
		}
		public static void compareResult(double numOne, double numTwo, String comp) {
		/*
		 * Prints out the user inputted values and states if it is true or false
		 */
		if (comp == "LessThan") {
			boolean lessThan = numOne < numTwo;
			System.out.printf("%f < %f is %b",numOne,numTwo,lessThan);
		} else if (comp == "LessOrEquals") {
			boolean lessOrEquals = numOne <= numTwo;
			System.out.printf("%f <= %f is %b",numOne,numTwo,lessOrEquals);
		} else if (comp == "GreaterThan") {
			boolean greaterThan = numOne > numTwo;
			System.out.printf("%f > %f is %b",numOne,numTwo,greaterThan);
		} else if (comp == "GreaterOrEquals") {
			boolean greaterOrEquals = numOne >= numTwo;
			System.out.printf("%f >= %f is %b",numOne,numTwo,greaterOrEquals);
		} else if (comp == "IsEquals") {
			boolean isEquals = numOne == numTwo;
			System.out.printf("%f == %f is %b",numOne,numTwo,isEquals);
		} else if (comp == "NotEquals") {
			boolean notEquals = numOne < numTwo;
			System.out.printf("%f != %f is %b",numOne,numTwo,notEquals);
		} else {
			System.out.println("How did you do this?!!!");
		}
		}
	
}



