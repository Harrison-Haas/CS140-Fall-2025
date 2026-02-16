
/**
 * @author Harrion Haas
 * Your Summary Here
 */
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import javax.imageio.ImageIO;

public class Assignment10 {

	// WARNING! DO NOT MODIFY ANYTHING ABOVE THIS LINE. (Except for JavaDoc.)

	public static final String PATH_TO_PPM_INPUT_FILE = "C:\\Users\\Harrison\\OneDrive\\Desktop\\HWCode\\Assignment10\\lincoln.ppm";

	public static final String PATH_TO_JPG_OUTPUT_FILE = "C:\\Users\\Harrison\\OneDrive\\Desktop\\HWCode\\Assignment10\\blurredLincoln.jpg";

	/**
	 * Compresses a single dimensional array of integers into a two dimensional
	 * array of integers consisting of the number from the inputted array in the
	 * first row of each column followed by the amount of times it occurs
	 * consecutively in the second column
	 * 
	 * @param arr The array of integers to be compressed
	 * @return The two dimensional compressed array
	 */
	public static int[][] compressRuns(int[] arr) {
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
			} else if (arr[i] == arr[i - 1]) {
			} else {
				count++;
			}
		}

		int[][] output = new int[count][2];
		int i = 0;
		int amount = 1;
		for (int row = 0; row < output.length; row++) {
			for (int col = 0; col < output[row].length; col++) {
				while (i < arr.length) {
					if (i == 0) {
					} else if (arr[i] == arr[i - 1]) {
						amount++;
					} else {
						break;
					}
					i++;
				}
				if (col == 0) {
					output[row][col] = arr[i - 1];
				} else {
					output[row][col] = amount;
				}
			}
			i++;
			amount = 1;
		}
		return output;
	}

	/**
	 * Decompresses a multi dimensional array of integers with the first column in a
	 * row corresponding to the value, and the second corresponding to how many
	 * times the value appears consecutively
	 * 
	 * @param arr The two dimensinonal array of integers to be decompressed
	 * @return The decompressed single dimensional array of integers
	 */
	public static int[] decompressRuns(int[][] arr) {
		int length = 0;
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				if (col == arr[row].length - 1) {
					length += arr[row][col];
				}
			}
		}
		int[] output = new int[length];
		int currentIndex = 0;
		for (int row = 0; row < arr.length; row++) {
			for (int r = 0; r < arr[row][1]; r++) {
				output[currentIndex] = arr[row][0];
				currentIndex++;
			}
		}
		return output;
	}

	/**
	 * Finds the local maxima in an array of integers and returns them in a new
	 * array of integers
	 * 
	 * @param arr The array of integer to be considered
	 * @return The local maxima in the inputted array
	 */
	public static int[] localMaxima(int[] arr) {
		int[] maxima = new int[arr.length];
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				if (arr[i] > arr[i + 1]) {
					maxima[count] = arr[i];
					count++;
				}
			} else if (i == arr.length - 1) {
				if (arr[i] > arr[i - 1]) {
					maxima[count] = arr[i];
					count++;
				}
			} else {
				if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
					maxima[count] = arr[i];
					count++;
				}
			}
		}

		int[] output = new int[count];
		for (int i = 0; i < output.length; i++) {
			output[i] = maxima[i];
		}

		return output;
	}

	/**
	 * Blurs a ppm file in the form of a multidimensional array of integers by
	 * taking the average value of an index's up to eight closest neighbors
	 * 
	 * @param data the data from the ppm file as a multidimensional array of
	 *             integers
	 * @return the blurred result of the input as a new multidimensional array of
	 *         integers
	 */
	public static int[][] blurImageData(int[][] data) {
		int[][] output = new int[data.length][data[0].length];
		for (int row = 0; row < output.length; row++) {
			for (int col = 0; col < output[row].length; col++) {
				if (row == 0) {
					output[row][col] = firstRow(data, row, col);
				} else if (row == output.length - 1) {
					output[row][col] = lastRow(data, row, col);
				} else {
					output[row][col] = middleRows(data, row, col);
				}
			}
		}
		return output;
	}

	/**
	 * Takes an index of a multidimensional array of integers and computes the
	 * average of it's neighbors next to and below it
	 * 
	 * @param data The data to be considered as a multidimensional array of integers
	 * @param row  The current row being considered
	 * @param col  The current column being considered
	 * @return The integer value of the average of the current index and its closest
	 *         neighbors next to and below it
	 */
	public static int firstRow(int[][] data, int row, int col) {
		int output = 0;
		if (col == 0) {
			int valuesNextTo = data[row][col] + data[row][col + 1];
			int valuesBelow = data[row + 1][col] + data[row + 1][col + 1];
			output = (valuesNextTo + valuesBelow) / 4;
		} else if (col == data[row].length - 1) {
			int valuesNextTo = data[row][col - 1] + data[row][col];
			int valuesBelow = data[row + 1][col - 1] + data[row + 1][col];
			output = (valuesNextTo + valuesBelow) / 4;
		} else {
			int valuesNextTo = data[row][col - 1] + data[row][col] + data[row][col + 1];
			int valuesBelow = data[row + 1][col - 1] + data[row + 1][col] + data[row + 1][col + 1];
			output = (valuesNextTo + valuesBelow) / 6;
		}
		return output;
	}

	/**
	 * Takes an index of a multidimensional array of inegers and computes the
	 * average of it's neighbors above and next to it
	 * 
	 * @param data The data to be considered as a multidimensional array of integers
	 * @param row  The current row being considered
	 * @param col  The current column being considered
	 * @return The integer value of the average of the current index and its closest
	 *         neighbors above and next to it
	 */
	public static int lastRow(int[][] data, int row, int col) {
		int output = 0;
		if (col == 0) {
			int valuesAbove = data[row - 1][col] + data[row - 1][col + 1];
			int valuesNextTo = data[row][col] + data[row][col + 1];
			output = (valuesAbove + valuesNextTo) / 4;
		} else if (col == data[row].length - 1) {
			int valuesAbove = data[row - 1][col - 1] + data[row - 1][col];
			int valuesNextTo = data[row][col - 1] + data[row][col];
			output = (valuesAbove + valuesNextTo) / 4;
		} else {
			int valuesAbove = data[row - 1][col - 1] + data[row - 1][col] + data[row - 1][col + 1];
			int valuesNextTo = data[row][col - 1] + data[row][col] + data[row][col + 1];
			output = (valuesAbove + valuesNextTo) / 6;
		}
		return output;
	}

	/**
	 * Takes an index of a multidimensional array of integers and computes the
	 * agverage of its neighbors above, next to, and below it
	 * 
	 * @param data The data to be considered as a multidimensional array of integers
	 * @param row  The current row being considered
	 * @param col  The current column being considered
	 * @return The integer value of the average of the current index and its closest
	 *         neighbors above, next to, and below it
	 */
	public static int middleRows(int[][] data, int row, int col) {
		int output = 0;
		if (col == 0) {
			int valuesAbove = data[row - 1][col] + data[row - 1][col + 1];
			int valuesNextTo = data[row][col] + data[row][col + 1];
			int valuesBelow = data[row + 1][col] + data[row + 1][col + 1];
			output = (valuesAbove + valuesNextTo + valuesBelow) / 6;
		} else if (col == data[row].length - 1) {
			int valuesAbove = data[row - 1][col - 1] + data[row - 1][col];
			int valuesNextTo = data[row][col - 1] + data[row][col];
			int valuesBelow = data[row + 1][col - 1] + data[row + 1][col];
			output = (valuesAbove + valuesNextTo + valuesBelow) / 6;
		} else {
			int valuesAbove = data[row - 1][col - 1] + data[row - 1][col] + data[row - 1][col + 1];
			int valuesNextTo = data[row][col - 1] + data[row][col] + data[row][col + 1];
			int valuesBelow = data[row + 1][col - 1] + data[row + 1][col] + data[row + 1][col + 1];
			output = (valuesAbove + valuesNextTo + valuesBelow) / 9;
		}
		return output;
	}

	// WARNING! DO NOT MODIFY ANYTHING BELOW THIS LINE.

	public static void main(String[] args) throws IOException {
		System.out.println("\n=== Task 1: Compression ===");
		runTask1CompressionTest();

		System.out.println("\n=== Task 1: Decompression ===");
		runTask1DecompressionTest();

		System.out.println("\n=== Task 2: Local Maxima ===");
		runTask2Test();

		System.out.println("\n=== Task 3: Image Blurring ===");
		runTask3Test();
	}

	/**
	 * Utility method to print out the contents of an array.
	 *
	 * @param arr The array to be printed
	 */
	public static void printArray(int[] arr) {
		for (int val : arr) {
			System.out.print(val + " ");
		}
		System.out.println();
	}

	/**
	 * Utility method to print out the contents of an array.
	 *
	 * @param arr The array to be printed
	 */
	public static void printArray(int[][] arr) {
		for (int[] row : arr) {
			printArray(row);
		}
	}

	/**
	 * Runs unit test for compressing array runs.
	 */
	public static void runTask1CompressionTest() {
		int[] example = { 4, 4, 4, 1, 1, 2 };
		int[][] result = compressRuns(example);

		System.out.println("Input");
		printArray(example);

		System.out.println("Result");
		printArray(result);

		System.out.println("Expected");
		int[][] expected = { { 4, 3 }, { 1, 2 }, { 2, 1 } };
		printArray(expected);
	}

	/**
	 * Runs unit test for decompressing array runs.
	 */
	public static void runTask1DecompressionTest() {
		int[][] example = { { 4, 3 }, { 1, 2 }, { 2, 1 } };
		int[] result = decompressRuns(example);

		System.out.println("Input");
		printArray(example);

		System.out.println("Result");
		printArray(result);

		System.out.println("Expected");
		int[] expected = { 4, 4, 4, 1, 1, 2 };
		printArray(expected);
	}

	/**
	 * Runs a unit test for the finding of local maxima in an array.
	 */
	public static void runTask2Test() {
		int[] example = { 1, 2, 5, 2, 3, 3, 9 };
		int[] result = localMaxima(example);

		System.out.println("Input");
		printArray(example);

		System.out.println("Result");
		printArray(result);

		System.out.println("Expected");
		int[] expected = { 5, 9 };
		printArray(expected);
	}

	/**
	 * Creates a blurred JPG image based on an input PPM file.
	 *
	 * @throws IOException
	 */
	private static void runTask3Test() throws IOException {
		int[][] imageData = readP2PPM(PATH_TO_PPM_INPUT_FILE);
		// Blur a few times to make it obvious; a single blur can be very subtle.
		int timesToBlur = 25;
		for (int blurCount = 0; blurCount < timesToBlur; blurCount++) {
			imageData = blurImageData(imageData);
		}
		writeGrayscaleJPG(imageData, PATH_TO_JPG_OUTPUT_FILE);
		System.out.println("Wrote file to " + PATH_TO_JPG_OUTPUT_FILE);
	}

	/**
	 * Convert a 2D int array of grayscale [0,255] values to a JPEG file.
	 *
	 * @param pixels     2D array, pixels[row][col], 0–255
	 * @param outputPath Path to the output .jpg file
	 * @throws IOException If the file cannot be written
	 */
	public static void writeGrayscaleJPG(int[][] pixels, String outputPath) throws IOException {
		if (pixels == null || pixels.length == 0 || pixels[0].length == 0) {
			throw new IllegalArgumentException("pixels array must be non-empty");
		}
		int maxVal = 255;
		if (maxVal <= 0) {
			throw new IllegalArgumentException("maxVal must be > 0");
		}

		int height = pixels.length;
		int width = pixels[0].length;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();

		for (int y = 0; y < height; y++) {
			if (pixels[y].length != width) {
				throw new IllegalArgumentException("All rows must have the same length");
			}
			for (int x = 0; x < width; x++) {
				int gray = pixels[y][x];

				// Clamp to [0, maxVal]
				if (gray < 0)
					gray = 0;
				if (gray > maxVal)
					gray = maxVal;

				// Normalize to [0,255]
				int gray255;
				if (maxVal == 255) {
					gray255 = gray;
				} else {
					// scale with rounding
					gray255 = (gray * 255 + maxVal / 2) / maxVal;
				}

				raster.setSample(x, y, 0, gray255);
			}
		}
		ImageIO.write(image, "jpg", new File(outputPath));
	}

	/**
	 * Reads a P2 (ASCII) PPM file and returns its pixel data as int[height][width].
	 * Each value is in [0, maxVal] as specified in the file header.
	 *
	 * @param path Path to the .ppm file
	 * @return 2D int array of grayscale values
	 * @throws IOException if there is an I/O or format error
	 */
	public static int[][] readP2PPM(String path) throws IOException {
		try (Reader r = new BufferedReader(new FileReader(path))) {
			StreamTokenizer st = new StreamTokenizer(r);

			// Configure tokenizer:
			// - Treat '#' as starting a comment to end of line
			// - Default whitespace rules (space, newline, etc.)
			st.commentChar('#');

			// 1. Read magic number ("P2")
			String magic = nextWord(st);
			if (!"P2".equals(magic)) {
				throw new IOException("Not a P2 PPM file: magic=" + magic);
			}

			// 2. Read width, height, and maxVal
			int width = nextInt(st);
			int height = nextInt(st);
			int maxVal = nextInt(st); // you can store/use this if needed

			if (width <= 0 || height <= 0 || maxVal <= 0) {
				throw new IOException(
						"Invalid PPM header: width=" + width + ", height=" + height + ", maxVal=" + maxVal);
			}

			int[][] pixels = new int[height][width];

			// 3. Read pixel data row by row
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int gray = nextInt(st);

					// Optionally clamp to [0, maxVal]
					if (gray < 0)
						gray = 0;
					if (gray > maxVal)
						gray = maxVal;

					pixels[y][x] = gray;
				}
			}

			return pixels;
		}
	}

	// Helper: read next token as a "word" (for the magic number "P2")
	private static String nextWord(StreamTokenizer st) throws IOException {
		int tokenType = st.nextToken();
		if (tokenType == StreamTokenizer.TT_WORD) {
			return st.sval;
		}
		throw new IOException("Expected a word token, got type=" + tokenType);
	}

	// Helper: read next token as an int
	private static int nextInt(StreamTokenizer st) throws IOException {
		int tokenType = st.nextToken();
		if (tokenType == StreamTokenizer.TT_NUMBER) {
			return (int) st.nval;
		} else if (tokenType == StreamTokenizer.TT_WORD) {
			// In case numbers are read as words (rare, but safer):
			try {
				return Integer.parseInt(st.sval);
			} catch (NumberFormatException e) {
				throw new IOException("Expected integer, got: " + st.sval, e);
			}
		}
		throw new IOException("Expected integer, got token type=" + tokenType);
	}
}