
/**
 * This code runs three functions using Arrays:
 * 1. Tests whether two strings are anagrams or not
 * 2. Compresses a string with repeat variables
 * 3. Multiplies two matrices together
 * @author Harrison Haas
 * 
 */

import java.util.Arrays;

public class Assignment09 {
	/**
	 * Main method that calls all other methods through test cases
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Test Case 1
		System.out.println("Test Case 1:");
		System.out.println("Input: \"Tom Marvolo Riddle\", \"I am Lord Voldemort\"");
		System.out.println("Output: " + isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort"));
		System.out.println();
		// Test Case 2
		System.out.println("Test Case 2:");
		System.out.println("Input: \"Tom \", \"I am Lord Voldemort\"");
		System.out.println("Output: " + isAnagram("Tom ", "I am Lord Voldemort"));
		System.out.println();
		// Test Case 3
		System.out.println("Test Case 3:");
		System.out.println("Input: \"aabcccccaaa\"");
		System.out.println("Output: " + compress("aabcccccaaa"));
		System.out.println();
		// Test Case 4
		System.out.println("Test Case 4:");
		System.out.println("Input: \"a2b1c5a3\"");
		System.out.println("Output: " + decompress("a2b1c5a3"));
		System.out.println();
		// Test Case 5
		System.out.println("Test Case 5:");
		double[][] matrixA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] matrixB = { { 1, 4, 9 }, { 16, 25, 36 }, { 49, 64, 81 } };
		System.out.println("Input:");
		printMatrix(matrixA);
		System.out.println();
		printMatrix(matrixB);
		System.out.println();
		double[][] matrixC = matrixMultiply(matrixA, matrixB);
		System.out.println("Output:");
		printMatrix(matrixC);

	}

	/**
	 * Compares two strings and if they are anagrams, returns true
	 * 
	 * @param firstString  The first string to be compared
	 * @param secondString The second string to be compared
	 * @return true if the two strings are anagrams, false if otherwise
	 */
	public static boolean isAnagram(String firstString, String secondString) {
		// Makes all characters in each string lowercase
		String firstLowerCase = firstString.toLowerCase();
		String secondLowerCase = secondString.toLowerCase();
		// Creates each array of integers
		int[] firstArr = createIntArray(firstLowerCase);
		int[] secondArr = createIntArray(secondLowerCase);
		// Removes spaces from each array of integers
		int spaceValue = 32; // space is stored as 32 in memory
		int[] firstNoSpaces = removeValue(firstArr, spaceValue);
		int[] secondNoSpaces = removeValue(secondArr, spaceValue);
		// order each array of integers from smallest value to largest
		Arrays.sort(firstNoSpaces);
		Arrays.sort(secondNoSpaces);
		// Compare the arrays of integers
		boolean result = compareArrays(firstNoSpaces, secondNoSpaces);
		return result;
	}

	/**
	 * Compares each array of integers and if they are identical returns true
	 * 
	 * @param first  The first array of integers to be compared
	 * @param second The second array of integers to be compared
	 * @return true if the two strings are anagrams, false if otherwise
	 */
	public static boolean compareArrays(int[] first, int[] second) {
		for (int i = 0; i < first.length; i++) {
			if (first[i] != second[i]) {
				return false; // If any of the integers aren't the same, return false
			}
		}
		return true;
	}

	/**
	 * Converts a string to an array of integers with the Unicode values
	 * 
	 * @param input The String that is to be turned into an array
	 * @return An array of integers with each value coressponding to the lowercase
	 *         char of the inputted String
	 */
	public static int[] createIntArray(String input) {
		int[] output = new int[input.length()];
		for (int i = 0; i < output.length; i++) {
			output[i] = input.charAt(i);
		}
		return output;
	}

	/**
	 * Converts a string to an array of characters
	 * 
	 * @param input The String that is to be turned all lowercase
	 * @return An array of the characters in the inputted String
	 */
	public static char[] createCharArray(String input) {
		char[] output = new char[input.length()];
		for (int i = 0; i < output.length; i++) {
			output[i] = input.charAt(i);
		}
		return output;
	}

	/**
	 * Removes all of one value of an array of integers and returns it
	 * 
	 * @param input         The array of integers to remove a value from
	 * @param valueToRemove The unicode value to be removed from the array of
	 *                      integers
	 * @return The array of integers without any of the specified Unicode values
	 */
	public static int[] removeValue(int[] input, int valueToRemove) {
		int count = 0;
		for (int val : input) {
			if (val == valueToRemove) {
				count++;
			}
		}
		int[] output = new int[input.length - count];
		int index = 0;
		for (int val : input) {
			if (val != valueToRemove) {
				output[index] = val;
				index++;
			}
		}
		return output;
	}

	/**
	 * Compresses a String using the counts of repeated Characters
	 * 
	 * @param decompressedInput The String to be compressed
	 * @return The compressed version of the inputted String
	 */
	public static String compress(String decompressedInput) {
		// create the array of ints corresponding to the inputted string
		int[] arr = createIntArray(decompressedInput);
		// create a helper array of all the repeated variables
		int[] countAt = countRepeatedVariables(arr);
		// returns the compressed output as a String
		return compressionOutput(arr, countAt, "");
	}

	/**
	 * Creates a helper array of integers that are the amount of times each value in
	 * the input appears consecutively
	 * 
	 * @param input An array of integers that represent the unicode value of a
	 *              String
	 * @return An array of integers that represent the amount of times each value
	 *         appears consecutively
	 */
	public static int[] countRepeatedVariables(int[] input) {
		int count = 0;
		int[] output = new int[input.length];
		for (int n = 0; n < input.length; n++) {
			if (n == 0) {
				count = 1;
			} else if (input[n] == input[n - 1]) {
				count++;
			} else {
				count = 1;
			}
			output[n] = count;
		}
		return output;
	}

	/**
	 * Compresses an inputted array of integers representing unicode characters
	 * 
	 * @param input   An array of integers that each are the unicode value of
	 *                characters in a string
	 * @param countAt The amount of times a character repeats itself in a row in the
	 *                other inputted array
	 * @return The array compressed and converted into a String
	 */
	public static String compressionOutput(int[] input, int[] countAt, String output) {
		for (int i = 0; i < countAt.length; i++) {
			if (i < input.length - 1) {
				if (countAt[i] == 1) {
					output += (char) input[i];
				}
				if (countAt[i + 1] == 1) {
					output += countAt[i];
				}
			} else {
				if (countAt[i] > 1) {
					output += countAt[i];
				} else {
					output += (char) input[i] + "" + countAt[i];
				}
			}
		}
		return output;
	}

	/**
	 * Finds the maximum value in an array of integers
	 * 
	 * @param input An array of integers
	 * @return The maximum value in the inputted array of integers
	 */
	public static int maxValue(int[] input) {
		int max = input[0];
		for (int val : input) {
			if (val > max) {
				max = val;
			}
		}
		return max;
	}

	/**
	 * decompresses an inputted string of mixed letters and numbers starting with a
	 * letter
	 * 
	 * @param input The string to be decompressed
	 * @return The decompressed string
	 */
	public static String decompress(String input) {
		String output = "";

		for (int i = 0; i < input.length(); i++) {
			if (isLetter((int) input.charAt(i))) {
				String repeatedNumber = "";
				int n = i + 1;
				// count the number after the current letter
				while (n < input.length() && isNumber((int) input.charAt(n))) {
					repeatedNumber += input.charAt(n);
					n++;
				}
				// change the string format of the number of times to repeat to an int
				int repeatCount = numberStringToInt(repeatedNumber);
				// add the current letter to the output the amount of the number after it times
				for (int d = 0; d < repeatCount; d++) {
					output += input.charAt(i);
				}
			}
		}

		return output;
	}

	/**
	 * Tests if an inputted integer corresponds to a letter in unicode
	 * 
	 * @param input The integer to be tested
	 * @return true if the integer corresponds to a letter's unicode, false if not
	 */
	public static boolean isLetter(int input) {
		// Relevent unicode values
		int beforeCapA = 64;
		int afterCapZ = 91;
		int beforeLowCaseA = 96;
		int afterLowCaseZ = 123;
		// if the inputted integer corresponds to a unicode letter return true
		if ((input > beforeCapA && input < afterCapZ) || (input > beforeLowCaseA && input < afterLowCaseZ)) {
			return true;
		}
		return false; // if not corresponding to a unicode letter, return false
	}

	/**
	 * Tests if an inputted integer corresponds to a number in unicode
	 * 
	 * @param input the integer to be tested
	 * @return true if the integer corresponds to a number's unicode, false if not
	 */
	public static boolean isNumber(int input) {
		// Relevant unicode values
		int zero = 48;
		int nine = 57;
		// If the inputted integer corresponds to a unicode number return true
		if (input >= zero && input <= nine) {
			return true;
		}
		return false; // if not corresponding to a unicode number, return false
	}

	/**
	 * converts any number in String form to the same number in integer form
	 * 
	 * @param inputStringForm The number in string form that will be converted to an
	 *                        integer
	 * @return The inputted string as an integer
	 */
	public static int numberStringToInt(String inputStringForm) {
		// create a helper array of integers
		int[] arr = new int[inputStringForm.length()];
		// subtract 48 from each of the unicode values and assign it to each index
		// the unicode for zero is 48, for one is 49, and so on until nine
		int zeroUnicode = 48;
		for (int i = 0; i < inputStringForm.length(); i++) {
			arr[i] = inputStringForm.charAt(i) - zeroUnicode;
		}
		//
		int output = 0; // the output integer
		int places = 0; // the place value the integer will be in
		// sets places equal to the amount of indices away from the last index
		for (int i = 0; i < arr.length; i++) {
			places = (arr.length - i);
			// multiplies each variable by the corresponding place value
			for (int n = 1; n < places; n++) {
				arr[i] *= 10;
			}
		}
		// adds each value of the array, turning it into an int
		for (int val : arr) {
			output += val;
		}
		return output;
	}

	/**
	 * Multiplies two Matrices together
	 * 
	 * @param matrixA The first matrix
	 * @param matrixB The second matrix
	 * @return the product of the two matrices
	 */
	public static double[][] matrixMultiply(double[][] matrixA, double[][] matrixB) {
		// Store values that repeat
		int rowsA = matrixA.length;
		int colsA = matrixA[0].length;
		int colsB = matrixB[0].length;

		// creates the output matrix that has the same rows as the first and same
		// columns as the second
		double[][] output = new double[rowsA][colsB];

		for (int row = 0; row < rowsA; row++) { // goes through each row
			for (int col = 0; col < output[row].length; col++) { // goes through each column in a row
				for (int i = 0; i < colsA; i++) {// goes through each element in the current row of matrixA and column
													// of matrixB
					output[row][col] += (matrixA[row][i] * matrixB[i][col]);// assigns the correct value to the current
																			// element
				}
			}
		}
		return output;
	}

	/**
	 * Prints an inputted two dimensional array as a matrix
	 * 
	 * @param input the two dimensional array
	 */
	public static void printMatrix(double[][] input) {
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input[row].length; col++) {
				System.out.print(input[row][col] + " ");
			}
			System.out.println();
		}
	}
}
