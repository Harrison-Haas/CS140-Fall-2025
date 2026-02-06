
public class Card {
	
	private String type;
	private int value;
	private int suit;
	
	// Constructors
	
	public Card(String type, int value, int suit) {
		this.type = type;
		this.value = value;
		this.suit = suit;
	}

	// Getters and Setters
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	// Regular instance methods
	
	public String suitName() {
		if(this.suit == 1) {
			return "Hearts";
		} else if(this.suit == 2) {
			return "Diamonds";
		} else if(this.suit == 3) {
			return "Clubs";
		} else if(this.suit == 4) {
			return "Spades";
		} else {
			return null;
		}
	}
	
	public String valueName() {
		if(this.value == 1) {
			return "Ace";
		} else if (this.value > 1 && this.value < 11) {
			return "" + this.value;
		} else if (this.value == 11) {
			return "Jack";
		} else if (this.value ==12) {
			return "Queen";
		} else if (this.value == 13) {
			return "King";
		} else {
			return null;
		}
	}
	
	
	public String toString() {
		return valueName() + " of " + suitName() + "(" + getType() + ")";
	}
	
	
}

