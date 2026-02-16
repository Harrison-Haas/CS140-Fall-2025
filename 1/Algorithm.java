/*Harrison Haas
 * This code is the steps to do 2 loads of laundry
*/
public class Algorithm {

	public static void main(String[] args) {
		//These two lines are the washing the first load of laundry, the first 5 steps
		System.out.println("Sort laundry into seperate loads");
		Method2(args);
		//These two lines are the drying of the first load of laundry, the next two steps
		System.out.println("Move load to dryer");
		System.out.println("Begin cycle");
		/*This line calls on the second method:
		 *The method for one washing a load of laundry*/
		Method2(args);
		/*The next two lines are the intermediate steps:
		 *The first load is done and the second is ready to dry*/
		System.out.println("Put dried clothes away");
		System.out.println("Move load to dryer");
		//This line calls on the first Method, representing the last dryer cycle
		Method1(args);
		//The last line is the final step of laundry//
		System.out.println("Put dried clothes away");
		
	}
	public static void Method1(String[] args) {
		//This Method is a generic method for one cycle of washing/drying
		System.out.println("Begin cycle");
		System.out.println("Wait until cycle is complete");
		System.out.println("Take load out of washer/dryer");
	}
	
	public static void Method2(String[] args) {
		//This Method accounts for the washing of one load of laundry
		System.out.println("Put a load into washer with detergent");
		Method1(args);
		
	}
}
