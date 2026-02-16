/**
 * Calculates the score of a Crib in Cribbage
 * 
 * @author Harrison Haas
 */

public class CribScorer {

	/**
	 * Calculates the cribbage score of an inputted array of Cards if it were a crib
	 * 
	 * @param cards the array of Cards to be considered
	 * @return The score of cards
	 */
	public static int calculation(Card[] cards) {
		Card[] unordered = cards; // Gather the cards the user has and store them in an array of Card Classes
		Card[] ordered = orderedCards(unordered); // Order the Cards in the array by their value
		Card[] faceTen = faceTen(ordered); // Replace values of 11, 12, or 13 with 10

		Scoring crib = new Scoring(true);

		fifteens(faceTen, crib);// Find the amount of 15's
		runsAndPairs(ordered, crib); // Find the runs and pairs
		flush(unordered, crib); // Check for flushes using the unordered
		knobs(unordered, crib); // Check for knobs using the unordered

		return crib.finalScore(); // Return the final score

	}

	/**
	 * Checks an array of Cards for up to five sequential values, pairs within
	 * sequential values, and pairs
	 * 
	 * @param a    The array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void runsAndPairs(Card[] a, Scoring crib) {
		int runLength = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i].getValue() == a[i - 1].getValue() + 1) {
				runLength++;
			}
		}

		if (runLength == 5) {
			crib.runOfFive();
		} else if (runLength == 4) {
			if (pair(a)) {
				crib.doubleRunOfFour();
			} else {
				crib.runOfFour();
			}
		} else if (runLength == 3) {
			if (threeOfAKind(a)) {
				crib.tripleRun();
			} else if (twoPair(a)) {
				crib.doubleDoubleRun();
			} else if (pair(a)) {
				if (doubleRun(a)) {
					crib.doubleRun();
				} else {
					crib.runOfThree();
					crib.pair();
				}
			} else {
				crib.runOfThree();
			}
		} else {
			pairs(a, crib);
		}
	}

	/**
	 * Checks an array of Cards for a pair within a run in the values
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if a double run appears, false if not
	 */
	public static boolean doubleRun(Card[] a) {
		int pairIndex = -1;
		if (a[0].getValue() == a[1].getValue()) {
			pairIndex = 0;
		} else if (a[1].getValue() == a[2].getValue()) {
			pairIndex = 1;
		} else if (a[2].getValue() == a[3].getValue()) {
			pairIndex = 2;
		} else if (a[3].getValue() == a[4].getValue()) {
			pairIndex = 3;
		}
		int runIndex = -1;
		if (a[0].getValue() + 1 == a[1].getValue()) {
			runIndex = 0;
		} else if (a[1].getValue() + 1 == a[2].getValue()) {
			runIndex = 1;
		} else if (a[2].getValue() + 1 == a[3].getValue()) {
			runIndex = 2;
		}
		if (runIndex == 0) {
			if (pairIndex == 3) {
				return false;
			} else {
				return true;
			}
		} else if (runIndex == 1) {
			return true;
		} else {
			if (pairIndex == 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Checks an array of Cards for up to four like values
	 * 
	 * @param a    The array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void pairs(Card[] a, Scoring crib) {
		if (fourOfAKind(a)) {
			crib.fourOfAKind();
		} else if (threeOfAKind(a)) {
			if (threeOfAKindAndPair(a)) {
				crib.pair();
				crib.threeOfAKind();
			} else {
				crib.threeOfAKind();
			}
		} else if (twoPair(a)) {
			crib.pair();
			crib.pair();
		} else if (pair(a)) {
			crib.pair();
		}
	}

	/**
	 * Checks an array of Cards for two sets of pairs of values
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there are two pairs, false if not
	 */
	public static boolean twoPair(Card[] a) {
		int pairs = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i].getValue() == a[i + 1].getValue()) {
				pairs++;
			}
		}
		if (pairs == 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks an Array of Cards if there is a pair of values in it
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there is a pair, false if not
	 */
	public static boolean pair(Card[] a) {
		// This will only run if there is no four of a kind or three of a kinds

		for (int i = 0; i < a.length - 1; i++) {
			if (a[i].getValue() == a[i + 1].getValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks an array of Cards if there is a pair of values in it, assuming there
	 * is a different set three values that are the same
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there is a pair and three of a kind, false if not
	 */
	public static boolean threeOfAKindAndPair(Card[] a) {
		// This will only run if there is already a three of a kind
		if (a[1].getValue() == a[2].getValue()) {
			if (a[3].getValue() == a[4].getValue()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (a[0].getValue() == a[1].getValue()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Checks an array of Cards if any three three values in a row are the same
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there are three consecutive identical values, false if not
	 */
	public static boolean threeOfAKind(Card[] a) {
		// This will only run if there are no four of a kinds
		for (int i = 0; i < a.length - 2; i++) {
			if ((a[i].getValue() == a[i + 1].getValue()) && (a[i].getValue() == a[i + 2].getValue())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks an Array of Cards if four values in a row are the same
	 * 
	 * @param a The array of Cards to be considered
	 * @return True if there are four identical values in a row, false if not
	 */
	public static boolean fourOfAKind(Card[] a) {
		for (int i = 0; i < a.length - 4; i++) {
			if ((a[i].getValue() == a[i + 1].getValue()) && (a[i].getValue() == a[i + 2].getValue())
					&& (a[i].getValue() == a[i + 3].getValue())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Orders an array of Cards so that the value in each successive card is greater
	 * than the last
	 * 
	 * @param a The array of Cards to be ordered
	 * @return The sorrted array of Cards
	 */
	public static Card[] orderedCards(Card[] a) {

		Card[] b = new Card[a.length];
		System.arraycopy(a, 0, b, 0, a.length);

		boolean swapped;
		do {
			swapped = false;
			for (int i = 0; i < b.length - 1; i++) {
				if (b[i].getValue() > b[i + 1].getValue()) {
					Card temp = b[i];
					b[i] = b[i + 1];
					b[i + 1] = temp;
					swapped = true;
				}
			}
		} while (swapped);

		return b;
	}

	/**
	 * Takes an input array of Cards and outputs an array of Cards with identical
	 * suits and types but converts any values above ten down to ten
	 * 
	 * @param a The array of Cards to be considered
	 * @return An array of Cards with no values above ten
	 */
	public static Card[] faceTen(Card[] a) {
		Card[] b = new Card[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = new Card(a[i].getType(), a[i].getValue(), a[i].getSuit());
			if (a[i].getValue() > 10) {
				b[i].setValue(10);
			}
		}
		return b;
	}

	/**
	 * Checks if the suits in an array of Cards all match, and runs the
	 * corresponding method within Scoring class
	 * 
	 * @param a    The array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void flush(Card[] a, Scoring crib) {
		int diffCut = 0;
		int cutSuit = 0;
		for (Card c : a) {
			if (c.getType().equals("Cut")) {
				cutSuit = c.getSuit();
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i].getSuit() != cutSuit) {
				diffCut++;
			}
		}

		if (diffCut == 0) {
			crib.flushFive();
		}
	}

	/**
	 * Checks if a hand card with the value of a jack in an array of Cards has the
	 * same suit as the cut card
	 * 
	 * @param a    The array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void knobs(Card[] a, Scoring crib) {
		int jackValue = 11;
		int cutCardSuit = 0;
		for (Card c : a) {
			if (c.getType().equals("Cut")) {
				cutCardSuit = c.getSuit();
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i].getType().equals("Hand") && (a[i].getValue() == jackValue) && a[i].getSuit() == cutCardSuit) {
				crib.knobs();
			}
		}
	}

	/**
	 * Calcuates every possible sum using the values of five Cards in an array of
	 * Cards
	 * 
	 * @param a    The array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void fifteens(Card[] a, Scoring crib) {
		twoCardFifteen(a, crib);// Two card combo
		threeCardFifteen(a, crib);// Three card combo
		fourCardFifteen(a, crib);// Four card Combo
		fiveCardFifteen(a, crib);// Five card Combo

	}

	/**
	 * Calculates all possible sums using two values from an array of Cards with
	 * length 5
	 * 
	 * @param a    THe array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void twoCardFifteen(Card[] a, Scoring crib) {
		for (int i = 0; i < a.length; i++) {
			for (int n = i + 1; n < a.length; n++) {
				int sum = a[i].getValue() + a[n].getValue();
				if (sum == 15) {
					crib.fifteen();
				}
			}
		}
	}

	/**
	 * Calculates all possible sums using three values from an array of Cards with
	 * length 5
	 * 
	 * @param a    THe array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void threeCardFifteen(Card[] a, Scoring crib) {
		for (int i = 0; i < a.length; i++) {
			for (int n = i + 1; n < a.length; n++) {
				for (int d = n + 1; d < a.length; d++) {

					int sum = a[i].getValue() + a[n].getValue() + a[d].getValue();
					if (sum == 15) {
						crib.fifteen();
					}
				}
			}
		}
	}

	/**
	 * Calculates all possible sums using four values from an array of Cards with
	 * length 5
	 * 
	 * @param a    THe array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void fourCardFifteen(Card[] a, Scoring crib) {
		for (int i = 0; i < a.length; i++) {
			for (int n = i + 1; n < a.length; n++) {
				for (int d = n + 1; d < a.length; d++) {
					for (int e = d + 1; e < a.length; e++) {

						int sum = a[i].getValue() + a[n].getValue() + a[d].getValue() + a[e].getValue();
						if (sum == 15) {
							crib.fifteen();
						}
					}
				}
			}
		}
	}

	/**
	 * Calculates all possible sums using five values from an array of Cards with
	 * length 5
	 * 
	 * @param a    THe array of Cards to be considered
	 * @param crib The current Scoring class
	 */
	public static void fiveCardFifteen(Card[] a, Scoring crib) {
		int total = 0;
		for (int i = 0; i < a.length; i++) {
			total += a[i].getValue();
		}

		if (total == 15) {
			crib.fifteen();
		}
	}

}
