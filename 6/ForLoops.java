/*
Name: Harrison Haas
Description: Demonstrates various operations using for loops:
- Mode A: (Counting & Summation) Range statistics
- Mode B: (Ractorials & Products) Controlled growth
- Mode C: (Searching) Primes in a range
- Mode D: (Nested Loops) Text shapes
Reflection: 
- Off-by-One Errors:
- Most often occur when the bounds are set ncorrectly
- Using <= instead of < or starting at 0 instead of 1
- Debugging Approaches: 
- Everytime you add something to the loop, run it and predict what will happen before you do
- If it doesn't do what it is expected to, look at what you added and find the problem
*/

import java.util.Scanner;

public class ForLoops {

	public static void main(String[] args) {
		// Display introduction and prompt user for mode selection
		introduction();
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter desired mode: A, B, C, or D.");
		String mode = sc.nextLine().trim();

		// Execute the selected mode based on user input
		if (mode.equalsIgnoreCase("A")) {
			rangeStats(sc); // Mode A: Counting & Summation
		} else if (mode.equalsIgnoreCase("B")) {
			controlledGrowth(sc); // Mode B: Factorials & Products
		} else if (mode.equalsIgnoreCase("C")) {
			primesInARange(sc); // Mode C: Searching
		} else if (mode.equalsIgnoreCase("D")) {
			textShapes(sc); // Mode D: Nested Loops
		} else {
			// Handle invalid input
			System.out.println("Enter an accepted input.");
			System.out.println("Invalid Input:" + mode);
		}
		sc.close();
	}

	public static void introduction() {
		// Explain each mode to the user
		System.out.println("Mode A will give statistics about the range between two integers.");
		System.out.println("Mode B will compute the factiorial of a number less than 10");
		System.out.println("Mode C will list all prime numbers between two integers");
		System.out.println(
				"Mode D will print a solid rectangle, a hollow rectangle, and a hollow right isoscles triangle");
	}

	// Prompt user for a number within a specified range; exit if invalid
	public static int boundedInput(Scanner sc, int lower, int upper) {
		System.out.println("Enter a number in the range: [" + lower + "," + upper + "]:");
		String input = null;
		int number = -1;
		try {
			input = sc.nextLine().trim();
			number = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Handle non-integer input
			System.out.println("Enter a number");
			System.out.println("Invalid input:" + input);
			System.exit(0);
		}
		// Validate range
		if (number < lower || number > upper) {
			System.out.printf("Enter a number within the range [%d,%d]\n", lower, upper);
			System.out.println("Invalid input: " + number);
			System.exit(0);
		}
		return number;
	}

	// Prompt user for any integer; exit if invalid
	public static int unboundedInput(Scanner sc) {
		System.out.println("Enter an integer:");
		String input = null;
		int number = -1;
		try {
			input = sc.nextLine().trim();
			number = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Enter a number");
			System.out.println("Invalid input:" + input);
			System.exit(0);
		}
		return number;
	}

	// Mode A: Display range statistics
	public static void rangeStats(Scanner sc) {

		int firstNumber = boundedInput(sc, 1, 10);
		int secondNumber = boundedInput(sc, 1, 10);
		int lo = Math.min(firstNumber, secondNumber); // Lower bound
		int hi = Math.max(firstNumber, secondNumber); // Upper bound
		/*
		 * Tradeoff: Having seperate methods for evens and odds improves clarity,
		 * however, they are so similar that having them in the same method could be
		 * more efficient
		 */
		System.out.println(rangeOutput(lo, hi));
	}

	// Compute sum of integers in range
	public static int rangeSum(int lo, int hi) {
		int sum = 0;
		for (int i = lo; i <= hi; i++) {
			sum += i;
		}
		return sum;
	}

	// Count even numbers in range
	public static int rangeEvens(int lo, int hi) {
		int evens = 0;
		for (int i = lo; i <= hi; i++) {
			if (i % 2 == 0)
				evens++;
		}
		return evens;
	}

	// Count odd numbers in range
	public static int rangeOdds(int lo, int hi) {
		int odds = 0;
		for (int i = lo; i <= hi; i++) {
			if (i % 2 != 0)
				odds++;
		}
		return odds;
	}

	// Compute arithmetic mean of range
	public static double rangeAMean(int lo, int hi) {
		double count = 0;
		int sum = 0;
		for (int i = lo; i <= hi; i++) {
			sum += i;
			count++;
		}
		return sum / count;
	}

	// Compute geometric mean of range
	public static double rangeGMean(int lo, int hi) {
		double count = 0;
		int prod = 1;
		for (int i = lo; i <= hi; i++) {
			prod *= i;
			count++;
		}
		return Math.pow(prod, 1 / count);
	}

	// Combine all range stats into a formatted string
	public static String rangeOutput(int lo, int hi) {
		String sumOutput = "\nSum of integers in range [" + lo + "," + hi + "]:" + rangeSum(lo, hi) + "\n";
		String evensOutput = "# of even integers in range [" + lo + "," + hi + "]:" + rangeEvens(lo, hi) + "\n";
		String oddsOutput = "# of odd integers in range [" + lo + "," + hi + "]:" + rangeOdds(lo, hi) + "\n";
		String aMeanOutput = "Arithmetic mean of range [" + lo + "," + hi + "]:" + rangeAMean(lo, hi) + "\n";
		String gMeanOutput = "Geometric mean of range [" + lo + "," + hi + "]:" + rangeGMean(lo, hi);
		return sumOutput + evensOutput + oddsOutput + aMeanOutput + gMeanOutput;
	}

	// Mode B: Compute factorial of a number
	public static void controlledGrowth(Scanner sc) {
		int n = boundedInput(sc, 1, 10);
		// n is capped at 10 because factorials get big enough to break int & even long
		// very quickly
		System.out.println(factorialCalc(n));

	}

	// Compute the factorial of a number
	public static String factorialCalc(int n) {
		int f = 1;
		for (int i = 1; i <= n; i++) {
			f *= i;
			if (i <n) {
				System.out.println(f);
			}
		}
		String result = "Final result: " + n + "! = " + f;
		return result;
	}

	// Mode C: Display primes in a range
	public static void primesInARange(Scanner sc) {
		int firstNumber = unboundedInput(sc);
		int secondNumber = unboundedInput(sc);
		int lo = Math.min(firstNumber, secondNumber);
		int hi = Math.max(firstNumber, secondNumber);
		System.out.println(primeOutput(lo, hi));
	}

	// Generate list of primes in range as a string
	public static String primeOutput(int lo, int hi) {
		String result = "";
		for (int i = lo; i <= hi; i++) {
			if (i < 2)
				continue; // Skip numbers less than 2
			if (primeTester(i)) {
				result += i + " ";
			}
		}
		return result.isEmpty() ? "No prime numbers in range [" + lo + "," + hi + "]" : result.trim();
	}

	// Checks if a number is prime by testing divisibility up to its square root.
	// Returns true if prime, false otherwise
	public static boolean primeTester(int i) {
		for (int d = 2; d <= Math.sqrt(i); d++) {
			if (i % d == 0) {
				return false;
			}
		}
		return true;
	}

	// Mode D: Display text-based shapes
	public static void textShapes(Scanner sc) {
		System.out.println("Enter the desired rows as the first integer, and desired columns as the second.");
		System.out.println("The first integer will be the height and width of the right isocleles triangle");
		int rows = unboundedInput(sc);
		int cols = unboundedInput(sc);
		solidRectangle(rows, cols);
		hollowRectangle(rows, cols);
		triangle(rows);
	}

	// Print solid rectangle
	public static void solidRectangle(int rows, int cols) {
		System.out.println();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				System.out.print("+");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Print hollow rectangle
	public static void hollowRectangle(int rows, int cols) {
		System.out.println();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (r == 0 || c == 0 || c == cols - 1 || r == rows - 1) {
					System.out.print("+");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// Print hollow right isosceles triangle
	public static void triangle(int rows) {
		System.out.println();
		for (int h = 0; h < rows; h++) {
			if (h == 0) {
				System.out.println("*");
			} else if (h == rows - 1) {
				for (int w = 0; w < rows; w++) {
					System.out.print("*");
				}
				System.out.println();
			} else {
				System.out.print("*");
				for (int i = 0; i < h - 1; i++) {
					System.out.print(" ");
				}
				System.out.println("*");
			}
		}
		System.out.println();
	}
}
