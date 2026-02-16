
/**
 * Helps a user play a game of cribbage by determining the optimal cards to discard or calculating their hand or crib
 * 
 * @author Harrison Haas
 */
import java.util.Scanner;

public class ModeSelector {

	/**
	 * Prompts the user for an input and runs the proper method corresponding to the
	 * user's selected mode
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("--------------------------------------------------------------------------");
			modeInformation();
			String choice = sc.nextLine().trim();

			if (choice.equals("1")) {
				discardMode(sc);
			} else if (choice.equals("2")) {
				handMode(sc);
			} else if (choice.equals("3")) {
				cribMode(sc);
			} else if (choice.equals("0")) {
				System.out.println("Exited");
				sc.close();
				return;
			} else {
				System.out.println("Invalid choice, Enter a value [0,3]");
			}
		}
	}

	/**
	 * Gathers an array of Cards with a length of five then calculates the cribbage
	 * score of those five cards as a crib and prints out the score with the steps
	 * of scoring
	 * 
	 * @param sc A scanner
	 */
	public static void cribMode(Scanner sc) {
		System.out.println("Crib Calculator");
		System.out.println("Enter the four cards in the crib and then the cut card");
		Card[] inputs = cardInputs(sc, 5);
		if (possibleHand(inputs)) {
			inputs.toString();
			System.out.println("Score:" + CribScorer.calculation(inputs));
		}
	}

	/**
	 * Gathers an array of Cards with a length of five then calculates the cribbage
	 * score of those five cards as a hand and prints out the score with the steps
	 * of scoring
	 * 
	 * @param sc A scanner
	 */
	public static void handMode(Scanner sc) {
		System.out.println("Hand Calculator");
		System.out.println("Enter the four cards in your hand and then the cut card");
		Card[] inputs = cardInputs(sc, 5);
		if (possibleHand(inputs)) {
			inputs.toString();
			System.out.println("Score:" + HandScorer.calculation(inputs, true));
		}
	}

	/**
	 * Gathers an array of Cards with a length of six then determines the optimal
	 * four card hand from those six and prints it out as well as the estimated
	 * score
	 * 
	 * @param sc A scanner
	 */
	public static void discardMode(Scanner sc) {
		System.out.println("Discard Calculator");
		Card[] inputs = cardInputs(sc, 6);
		if (possibleHand(inputs)) {
			Card[] hand = DiscardCalculator.calculation(inputs);
			for (int i = 0; i < hand.length - 1; i++) {
				System.out.println(hand[i].toString());
			}
			System.out.println();
			System.out.println("Estimated Score:\n" + HandScorer.calculation(hand, false));
		}
	}

	/**
	 * Checks if an inputted array of Cards would be a possible cribbage hand of
	 * length 5 or 6
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if the hand is possible, false if not
	 */
	public static boolean possibleHand(Card[] a) {
		if (invalidCardArray(a)) {
			System.out.println("One of the entered cards is not a valid card");
			return false;
		} else if (repeatCard(a)) {
			System.out.println("The entered hand contains a repeated card");
			return false;
		} else if (fiveOfOneValue(a)) {
			System.out.println("The entered hand contains more than four of one value");
			return false;
		} else if (a.length == 5 && numOfCutCards(a) != 1) {
			System.out.println("Exactly one Cut Card required for Score Calculation");
			return false;
		} else if (a.length == 6 && numOfCutCards(a) != 0) {
			System.out.println("No Cut cards allowed for Discard Calculation");
			return false;
		}
		return true;

	}

	/**
	 * Tests if an array of Cards contains any repeat cards
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there is a repeat card, false if not
	 */
	public static boolean repeatCard(Card[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int n = i + 1; n < a.length; n++) {
				if (a[i].getValue() == a[n].getValue() && a[i].getSuit() == a[n].getSuit()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Tests if an array of Cards contains the same value more than four times
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there is four or more of one card, false if not
	 */
	public static boolean fiveOfOneValue(Card[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int count = 1;
			for (int n = i + 1; n < a.length; n++) {
				if (a[n].getValue() == a[i].getValue()) {
					count++;
					if (count > 4) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * Checks an array of Cards for any cards that have invalid values, suits, or
	 * types
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if their is an invalid card, false if not
	 */
	public static boolean invalidCardArray(Card[] a) {
		for (Card c : a) {
			int value = c.getValue();
			int suit = c.getSuit();
			String type = c.getType();
			if (value == -1 || suit == -1 || type == null) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Counts the number of Cards with a type of "Cut" in an array of Cards
	 * 
	 * @param a The array of Cards to be considered
	 * @return The number of cards with type "Cut"
	 */
	public static int numOfCutCards(Card[] a) {
		int count = 0;
		for (Card c : a) {
			if (c.getType().equals("Cut")) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Prints out information about the possible modes that can be selected
	 */
	public static void modeInformation() {
		System.out.println("\nSelect Mode:");
		System.out.println("[1]: Determines the optimal cards to discard");
		System.out.println("[2]: Calculates the score of a hand");
		System.out.println("[3]: Calculates the score of a crib");
		System.out.println("[0]: Exits");
		System.out.println("Choice:");
	}

	/**
	 * Gathers information about an array of Cards from user input
	 * 
	 * @param sc     A scanner
	 * @param length The desired length of the array of Cards
	 * @return The array of Cards filled with the user's input
	 */
	public static Card[] cardInputs(Scanner sc, int length) {
		inputTables();
		Card[] output = new Card[length];

		for (int i = 0; i < length; i++) {
			output[i] = new Card(null, 0, 0);
			int currentCard = i + 1;

			System.out.println("Enter Card " + currentCard + "'s Value:");
			output[i].setValue(cardValue(sc));

			System.out.println("Enter Card " + currentCard + "'s Suit:");
			output[i].setSuit(cardSuit(sc));

			System.out.println("If Card " + currentCard + " is in your hand, enter 0");
			System.out.println("If Card " + currentCard + " is the cut Card, enter 1");
			output[i].setType(cardType(sc));
		}
		return output;
	}

	/**
	 * Gathers the value of a user's card as an integer
	 * 
	 * @param sc A scanner
	 * @return The integer representation of the value
	 */
	public static int cardValue(Scanner sc) {
		String value = sc.nextLine().trim();
		int ace = 1;
		int king = 13;
		for (int i = ace; i <= king; i++) {
			if (value.equals("" + i)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gathers the suit of a user's card as an integer
	 * 
	 * @param sc A scanner
	 * @return The integer representation of the suit
	 */
	public static int cardSuit(Scanner sc) {
		String suit = sc.nextLine().trim();
		int hearts = 1;
		int spades = 4;
		for (int i = hearts; i <= spades; i++) {
			if (suit.equals("" + i)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gathers the type of the user's card as a String
	 * 
	 * @param sc A scanner
	 * @return The type of the user's card
	 */
	public static String cardType(Scanner sc) {
		String type = sc.nextLine().trim();
		int hand = 0;
		int cut = 1;
		if (type.equals("" + hand)) {
			return "Hand";
		} else if (type.equals("" + cut)) {
			return "Cut";
		} else {
			return null;
		}
	}

	/**
	 * Prints out two tables that inform the user what values to input based on
	 * their cards
	 */
	public static void inputTables() {
		String[] cardValue = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
				"Queen", "King" };
		int[] valueToInput = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

		String[] suitName = { "Hearts", "Diamonds", "Clubs", "Spades" };
		int[] suitToInput = { 1, 2, 3, 4 };

		int linesOfGap = cardValue.length - suitName.length - 2;
		System.out.println("|-----------------------------|");
		System.out.println("| Card Value : Value to Input |");
		for (int i = 0; i < cardValue.length; i++) {
			if (i < linesOfGap) {
				System.out.printf("| %-10s : %8d\t      |\n", cardValue[i], valueToInput[i]);
			} else if (i == linesOfGap) {
				System.out.printf("| %-10s : %8d\t      |\t|---------------------------|\n", cardValue[i],
						valueToInput[i]);
			} else if (i == linesOfGap + 1) {
				System.out.printf("| %-10s : %8d\t      |\t| Suit     : Value to Input |\n", cardValue[i],
						valueToInput[i]);
			} else {
				System.out.printf("| %-10s : %8d\t      |", cardValue[i], valueToInput[i]);
				System.out.printf("\t| %-8s : %7d\t    |%n", suitName[i - linesOfGap - 2],
						suitToInput[i - linesOfGap - 2]);
			}
		}
		System.out.println("|-----------------------------|\t|---------------------------|");
	}

}
