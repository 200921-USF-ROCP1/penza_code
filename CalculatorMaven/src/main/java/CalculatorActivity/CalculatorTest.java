package CalculatorActivity;

//import CalculatorNew.ClassCalculator;

public class CalculatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassCalculator c = new ClassCalculator();
		
		int a = 6;
		int b = 2;
		int x;
		
		x = c.add( a, b );
		String s = a + " + " + b + " = " + x;
		System.out.println( s );
	}

}
