/**
 * Determines the best cards to discard in a cribbage hand
 * 
 * @author Harrison Haas
 */
public class DiscardCalculator {

	/**
	 * Calculates the combination of four that would be the best cribbage hand from
	 * an inputted array of Cards
	 * 
	 * @param a the array of Cards to be considered
	 * @return The optimal four cards as an array of Cards
	 */
	public static Card[] calculation(Card[] a) {
		Card cutCard = cutCard(a);
		Card[][] allDiscards = possibleHands(a, cutCard);

		return optimalDiscard(allDiscards);
	}

	/**
	 * Finds the array of Cards in a two dimensional array that returns the highest
	 * score
	 * 
	 * @param a The two dimensional array of Cards to be considered
	 * @return The array of Cards with the highest score
	 */
	public static Card[] optimalDiscard(Card[][] a) {
		int highScore = 0;
		Card[] output = a[0];
		for (Card[] b : a) {
			if (HandScorer.calculation(b, false) > highScore) {
				highScore = HandScorer.calculation(b, false);
				output = b;
			}
		}
		return output;
	}

	/**
	 * Returns the first Card with a value of 10 or 11 that is not included in an
	 * array of Cards
	 * 
	 * @param a The array of Cards to be considered
	 * @return A Card with value of 10 or 11 that is not used in the inputted array;
	 *         if none is found, null
	 */

	public static Card cutCard(Card[] a) {
		int[] candidates = { 10, 11 };
		for (int value : candidates) {
			for (int suit = 1; suit <= 4; suit++) {
				if (!containsCard(a, value, suit)) {
					return new Card("Cut", value, suit);
				}
			}
		}
		return null;
	}

	/**
	 * Checks an array of Cards for if it contains a card with a specified value and
	 * suit
	 * 
	 * @param a     The array of Cards to be considered
	 * @param value The value to be checked
	 * @param suit  The suit to be checked
	 * @return True if a card with the specified value and suit is in the array,
	 *         false if not
	 */
	public static boolean containsCard(Card[] a, int value, int suit) {
		for (Card c : a) {
			if (c.getValue() == value && c.getSuit() == suit) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates a two dimensional array of Cards that contains all the possible
	 * combinations of 4 cards from the 6 card inputted array and the inputted
	 * cutCard in all combinatoins
	 * 
	 * @param a       The array of Cards to be considered
	 * @param cutCard The cutCard to be added to each array of Cards
	 * @return A two dimensional array of cards
	 */
	public static Card[][] possibleHands(Card[] a, Card cutCard) {
		int combinations = 15; // From 6 cards there are exactly 15 possible combinations of 4 unique cards
		int discardedHandSize = 5; // The size of a cribbage hand after discard is exactly 4 cards, add 1 for the
									// Cut Card
		int b = a.length;
		Card[][] output = new Card[combinations][discardedHandSize];

		int comboIndex = 0;

		for (int i = 0; i < b; i++) {
			for (int j = i + 1; j < b; j++) {
				for (int k = j + 1; k < b; k++) {
					for (int l = k + 1; l < b; l++) {
						output[comboIndex][0] = a[i];
						output[comboIndex][1] = a[j];
						output[comboIndex][2] = a[k];
						output[comboIndex][3] = a[l];
						output[comboIndex][4] = cutCard;
						comboIndex++;
					}
				}
			}
		}
		return output;
	}
}
