
/*
 * Harrison Haas
 * This code asks for some information about the user.
 * It then creates a greeting and ID card for the user.
 */
import java.util.Scanner;

public class PersonalGreeter {

	public static void main(String[] args) {
		// These two finals will come into play later
		final int DAYS_PER_YEAR = 365;
		final double CM_PER_INCH = 2.54;

		// This section is all the questions and gathering all the info
		Scanner sc = new Scanner(System.in);

		System.out.println("What is your full name?");
		String fullName = sc.nextLine().toUpperCase();

		System.out.println("What is your preferred name?");
		String preferredName = sc.nextLine().toUpperCase();

		System.out.println("How old are you, in years of course?");
		int ageYears = sc.nextInt();

		System.out.println("How tall are you in centimeters?");
		double heightCm = sc.nextDouble();

		System.out.println("What's your favorite number? Feel free to choose one with decimals.");
		double favNumber = sc.nextDouble();

		System.out.print("Do you like football?\n(Y/N): ");
		boolean likesFootball = sc.next().equalsIgnoreCase("Y");

		// This section converts some info we gathered earlier into new values
		// The next two lines convert an integer AgeDaysInt into a double AgeDaysDouble
		int ageDaysInt = ageYears * DAYS_PER_YEAR;
		double ageDaysDouble = ageDaysInt;
		/*
		 * Truncating rounds down to the nearest integer and drops the decimals Rounding
		 * takes the decimals into consideration when deciding to go to the higher or
		 * lower integer The next line of code converts two doubles into a int by
		 * truncating
		 */
		int heightIn = (int) (heightCm / CM_PER_INCH);
		char firstInitial = preferredName.charAt(0);

		// The next 4 lines of code is a quick one line greeting
		System.out.print("\n\nHello " + preferredName);
		System.out.print("! \nAge: " + ageYears);
		System.out.print("\nFavorite Number: " + favNumber);
		System.out.print("\nFirst Intial: " + firstInitial);

		// The next 7 lines of code form the formatted ID card
		System.out.printf("\n\n\n%30s ID Card\n", fullName);
		System.out.printf("-------------------------------------------------------\n");
		System.out.printf("Full Name: %s\nPreferred Name: %s\n", fullName, preferredName);
		System.out.printf("Age(Years): %d\nAge(Days): %.2f\n", ageYears, ageDaysDouble);
		System.out.printf("Height(Cm): %.2f\nHeight(In): %d\n", heightCm, heightIn);
		System.out.printf("Likes Football: %s\n", likesFootball ? "Yes" : "No");
		System.out.printf("-------------------------------------------------------\n");

		sc.close();

	}
}
