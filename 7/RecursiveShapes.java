/**
 * Author: Harrison Mitchell Haas Description: This program prints three
 * different shapes using recursion only. Two shapes are from class
 * (checkerboard and empty square), and one is original (a filled in triangle).
 */

public class RecursiveShapes {

	/**
	 * Main method that calls all shape-printing methods.
	 * 
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		int checkerboardSquareSize = 4;
		int checkerboardWidth = 8;
		int checkerboardLength = 8;
		System.out.println("Checkerboard (" + checkerboardSquareSize + "x" + checkerboardSquareSize + " squares, "
				+ checkerboardLength + "x" + checkerboardWidth + " board):\n");
		printCheckerboard(4, 8, 8);

		int squareHeight = 5;
		System.out.println("\n\nEmpty Square (" + squareHeight + "x" + squareHeight + "):\n");
		printEmptySquare(squareHeight);

		int triangleHeight = 6;
		System.out.println("\n\nFilled in Triangle (height " + triangleHeight + "):\n");
		printTriangle(triangleHeight);
	}

	/**
	 * Prints a checkerboard pattern using recursion.
	 * 
	 * @param squareSize The size of each square (in characters).
	 * @param rows       Number of squares vertically.
	 * @param cols       Number of squares horizontally.
	 */
	public static void printCheckerboard(int squareSize, int rows, int cols) {
		printCheckerboardRow(squareSize, rows, cols, 0);
	}

	/**
	 * Helper method to print each row of the checkerboard.
	 * 
	 * @param squareSize Size of each square.
	 * @param rows       Total number of square rows.
	 * @param cols       Total number of square columns.
	 * @param currentRow Current row being printed.
	 */
	public static void printCheckerboardRow(int squareSize, int rows, int cols, int currentRow) {
		if (currentRow >= rows * squareSize)
			return;
		printCheckerboardLine(squareSize, cols, currentRow);
		printCheckerboardRow(squareSize, rows, cols, currentRow + 1);
	}

	/**
	 * Prints a single line of the checkerboard.
	 * 
	 * @param squareSize Size of each square.
	 * @param cols       Number of columns.
	 * @param currentRow Current row index.
	 */
	public static void printCheckerboardLine(int squareSize, int cols, int currentRow) {
		printCheckerboardCol(squareSize, cols, currentRow, 0);
		System.out.println();
	}

	/**
	 * Prints each character in a line of the checkerboard.
	 * 
	 * @param squareSize Size of each square.
	 * @param cols       Number of columns.
	 * @param currentRow Current row being considered.
	 * @param currentCol Current column being considered.
	 */
	public static void printCheckerboardCol(int squareSize, int cols, int currentRow, int currentCol) {
		if (currentCol >= cols * squareSize)
			return;
		boolean isWhite = ((currentRow / squareSize) + (currentCol / squareSize)) % 2 == 0;
		System.out.print(isWhite ? "#" : " ");
		printCheckerboardCol(squareSize, cols, currentRow, currentCol + 1);
	}

	/**
	 * Prints an empty square with a border using recursion.
	 * 
	 * @param size The size of the square (number of characters per side).
	 */
	public static void printEmptySquare(int size) {
		printEmptySquareRow(size, 0);
	}

	/**
	 * Helper method to print each row of the empty square.
	 * 
	 * @param size Size of the square.
	 * @param row  Current row being considered.
	 */
	public static void printEmptySquareRow(int size, int row) {
		if (row >= size)
			return;
		printEmptySquareCol(size, row, 0);
		System.out.println();
		printEmptySquareRow(size, row + 1);
	}

	/**
	 * Prints each character in a row of the empty square.
	 * 
	 * @param size Size of the square.
	 * @param row  Current row being looked at.
	 * @param col  Current column being looked at.
	 */
	public static void printEmptySquareCol(int size, int row, int col) {
		if (col >= size)
			return;
		if (row == 0 || row == size - 1 || col == 0 || col == size - 1) {
			System.out.print("*");
		} else {
			System.out.print(" ");
		}
		printEmptySquareCol(size, row, col + 1);
	}

	/**
	 * Prints a solid right-angled triangle using recursion.
	 * 
	 * @param height The height of the triangle.
	 */
	public static void printTriangle(int height) {
		printTriangleRow(height, 1);
	}

	/**
	 * Helper method to print each row of the triangle.
	 * 
	 * @param height     Total height of the triangle.
	 * @param currentRow Current row being printed.
	 */
	public static void printTriangleRow(int height, int currentRow) {
		if (currentRow > height)
			return;
		printTriangleStars(currentRow);
		System.out.println();
		printTriangleRow(height, currentRow + 1);
	}

	/**
	 * Prints a row of arrows for the triangle.
	 * 
	 * @param count Number of arrows to print.
	 */
	public static void printTriangleStars(int count) {
		if (count == 0)
			return;
		System.out.print("^");
		printTriangleStars(count - 1);
	}
}