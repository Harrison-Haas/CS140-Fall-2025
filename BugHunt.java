/*
 * Harrison Haas
 * Syntax Errors:
 * missing semi-colons
 * . instead of spaces and vice versa
 * trying to store a double as an int
 * Logic Errors:
 * not allowing for caps or trimming when reading inputs
 * using == instead of .equals() for a string
 * grade if / else if / else statement backwards
 * I added in .equalsIgnoreCase() and .trim() to fix the first logic error
 * I changed around the if/else if/else statement to make sense
 */
import java.util.Scanner;

public class BugHunt {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Choose mode [grade/temp]: ");
		String mode = sc.nextLine().trim();
		
		if (mode.equalsIgnoreCase("grade")) {
			runGrade(sc);
		} else if (mode.equalsIgnoreCase("temp")) {
			runTemp(sc);
		} else {
			System.out.println("Unrecognized mode: " + mode);
		}
		sc.close();
	}

	public static void runGrade(Scanner sc) {
		System.out.print("Enter numeric score 0-100: ");
		String scoreInput = null;
		int score = 0;
	
		try {
		    scoreInput = sc.nextLine().trim();
		    score = Integer.parseInt(scoreInput);
		} catch (NumberFormatException e) {
		    System.out.println("Invalid number format!");
		}
		gradePrint(scoreInput, score);
	}
	public static void gradePrint(String scoreInput, int score) {
		if (score > 100 || score < 0) {
			System.out.println("Value out of range [0,100]:" + score);
		} else if (score <= 100 && score >= 0) {
			if (score >= 90) {
				System.out.println("Letter grade: A");
			} else if (score >= 80) {
				System.out.println("Letter grade: B");
			} else if (score >= 70) {
				System.out.println("Letter grade: C");
			} else if (score >= 60) {
				System.out.println("Letter grade: D");
			} else {
				System.out.println("Letter grade: F");
			}
		} else {}
	}

	public static void runTemp(Scanner sc) {
		System.out.print("Enter Fahrenheit temperature: ");
		
		String tempInput = null;
		double f = 0.0;
		boolean error = false;
		try {
			tempInput = sc.nextLine().trim();
			f = Double.parseDouble(tempInput);

		} catch (NumberFormatException e) {
			error = true;
			System.out.println("Invalid number format: " + tempInput);
		}
		
		tempPrint(error, f, tempInput);
		}
	
	public static void tempPrint(boolean error, double f, String tempInput) {
		
		if (error) {
			
		} else {
			
			double c = (f - 32) / 1.8;
			System.out.println("Celsius: " + String.format("%.2f", c));
			
			if (f > 32 && f < 212) {
			System.out.println("Phase: liquid");
			} else if (f <= 32) {
			System.out.println("Phase: solid");
			} else {
			System.out.println("Phase: gas");
			}
		}
	
	}
}
