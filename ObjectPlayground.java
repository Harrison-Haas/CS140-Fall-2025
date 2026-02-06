/*
Name: Harrison Haas
Description: 
This program demonstrates key Java concepts including the difference between primitives and objects,
comparison behavior using '==' vs '.equals()', variable scope, and operator precedence.
Each method provides illustrative examples and outputs to help understand how Java handles memory,
evaluates expressions, and enforces scoping rules.
*/

/*
Reflection: Understanding what your code is actually doing will help with logic errors, ie your code behaving unexpectedly
 */
import java.util.Scanner;
public class ObjectPlayground {

	public static void main(String[] args) {
		primitiveVsObjectExamples();
		primitiveVsObjectExamplesInAction();
		scopeDemo();
		precedenceExample();
	}
	public static void primitiveVsObjectExamples() {
		// This method demonstrates the difference between a primitive and an object.
		// A primitive (like int) stores the actual value directly.
		// An object (like String) stores a reference to the data that lives in the heap.
		System.out.println("First Method:");
		int a = 2;
		String alpha = "Two";
		
		System.out.println("Primitive example: a = " + a + " (Stored Directly in the Stack)");
		System.out.println("Object example: alpha = " + alpha + " (Stored as a reference to the heap where the actual data lies)");
		System.out.println("--------------");
	}
	public static void primitiveVsObjectExamplesInAction() {
		/*
		This method demonstrates how comparison works differently for primitives and objects.
		
		For primitives like int:
			'==' compares the actual values directly.
		 	'.equals()' does not apply
		
		For objects like Strings:
			'==' checks if both variables point to the same memory location (reference comparison).
			'.equals()' checks if the actual contents of the objects are the same (value comparison).
		 */
		System.out.println("Second Method:");
		String alpha = "Two";
		String beta = "Two";
		
		System.out.println(alpha == beta);
		System.out.println(alpha.equals(beta));
		
		int a = 2;
		int b = 2;
		
		System.out.println(a == b);
		
		System.out.println("--------------");
	}
	public static void scopeDemo() {
		/*
		Variables are not accesable outside of the scope they are declared in
		Java enforces this so that variables are not unintenionally modified outside of the scope they are used in
		 */
		
		System.out.println("Third Method:");
		int count = 2;
		
		if (count == 2) {
			int inside = 0;
			System.out.println(inside);
		}
		
		//System.out.println(inside);
		
		//int inside cannot be printed here becasue it is only defined insde the if statement
		//int inside would have to be declared and initialized prior to the if statement
		
		System.out.println("--------------");
	}
	public static void precedenceExample() {
		/*
		Multiplication and division have higher precedence in java than addition and subtraction
		Adding the parentheses overrides this causing the addition to be first in the below examples
		 */
		System.out.println("Fourth Method:");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter first numner:");
		double a = sc.nextDouble();
		
		System.out.println("Enter second numner:");
		double b = sc.nextDouble();
		
		System.out.println("Enter third numner:");
		double c = sc.nextDouble();
		
		sc.close();
		
		double d = a + b * c ;
		double e = (a + b) * c;
		double f = a / b + c;
		double g = a/ (b + c);
		
		System.out.println("a + b * c = " + d );
		System.out.println("(a + b) * c = " + e);
		System.out.println("a / b + c = " + f);
		System.out.println("a / (b + c) = " + g);
		System.out.println("--------------");
	}
}

