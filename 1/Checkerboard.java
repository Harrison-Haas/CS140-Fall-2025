/* 
 * Harrison Haas
 * This Code is a 5x5 checkerboard of 4x4 squares of 8
 */
public class Checkerboard {
	
	public static void main(String[] args){
		DoubleLine(args);
		DoubleLine(args);
		Four8(args);		
	}
	//This method is for one row starting with an 8//
	public static void One8(String[] args) {
		//I made this method to use less typing in later methods//
		System.out.println("8888    8888    8888");		
	}
	//This method is for one row starting with a blank//
		public static void OneBlank(String[] args) {
			//I made this method to use less typing in later methods//
			System.out.println("    8888    8888    ");
		}
	//This Method is for 4 rows starting with 8s//
	public static void Four8(String[] args) {
		//If I could I would write the first line and then just multiply it by 4//
		One8(args);
		One8(args);
		One8(args);
		One8(args);	
	}
	//This method is 4 rows starting with blanks//
	public static void FourBlank(String[] args) {
		//Same thing from the last method goes for this one as well, the x4//
		OneBlank(args);
		OneBlank(args);
		OneBlank(args);
		OneBlank(args);
	}
	//This method is two rows, the top row beginning with a black square
	public static void DoubleLine(String[] args) {
		Four8(args);
		FourBlank(args);		
	}
}