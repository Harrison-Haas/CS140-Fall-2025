
public class Scoring {

	private int points;
	private boolean print;

	// Constructor

	public Scoring(boolean print) {
		this.points = 0;
		this.print = print;
	}

	// Getters and Setters

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	// Scoring Methods
	public void fifteen() {
		points += 2;
		if (print) {
			System.out.println("Fifteen-" + points);
		}
	}

	public void pair() {
		points += 2;
		if (print) {
			System.out.println("Pair for " + points);
		}
	}

	public void threeOfAKind() {
		points += 6;
		if (print) {
			System.out.println("Three of a Kind makes " + points);
		}
	}

	public void fourOfAKind() {
		points += 12;
		if (print) {
			System.out.println("Four of a Kind is " + points);
		}
	}

	public void runOfThree() {
		points += 3;
		if (print) {
			System.out.println("Run of three for " + points);
		}
	}

	public void runOfFour() {
		points += 4;
		if (print) {
			System.out.println("Run of four makes " + points);
		}
	}

	public void runOfFive() {
		points += 5;
		if (print) {
			System.out.println("Run of five is " + points);
		}
	}

	public void flushFour() {
		points += 4;
		if (print) {
			System.out.println("Flush of four makes " + points);
		}
	}

	public void flushFive() {
		points += 5;
		if (print) {
			System.out.println("Flush of five is " + points);
		}
	}

	public void knobs() {
		points += 1;
		if (print) {
			System.out.println("Knobs makes " + points);
		}
	}

	public void doubleRunOfFour() {
		points += 10;
		if (print) {
			System.out.println("Double run of four for " + points);
		}
	}

	public void tripleRun() {
		points += 15;
		if (print) {
			System.out.println("Triple run is " + points);
		}
	}

	public void doubleDoubleRun() {
		points += 16;
		if (print) {
			System.out.println("Double double run makes " + points);
		}
	}

	public void doubleRun() {
		points += 8;
		if (print) {
			System.out.println("Double run for " + points);
		}
	}

	public int finalScore() {
		return points;
	}
}
