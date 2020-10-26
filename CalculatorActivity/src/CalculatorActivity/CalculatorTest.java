package CalculatorActivity;

public class CalculatorTest {
	
	public static void main (String args[] ) {
		ClassCalculator c = new ClassCalculator();
		
		int a = 6;
		int b = 2;
		int x;
		
		x = c.add( a, b );
		String s = a + " + " + b + " = " + x;
		System.out.println( a + " + " + b + "= " + x);
	}	
}
