package CalculatorActivity;

public class ClassCalculator implements Calculator {
	public int add(int a, int b) {
		return( a + b );
	}
	
	public int subtract(int a, int b) {
		return( a - b );		
	}
	
	public int multiply(int a, int b) {
		return( a * b );		
	}
	
	public int divide(int a, int b) {
		return( a / b );		
	}
	
	// Advanced operations
	
	// Return x to the power e
	public int exponent(int x, int e) {
		int num = 1;
		for (int i = 0; i < e; ++i ) {
			num *= x;
		}
		
		return num;
	}
	
	// Return fib(i)
	public int fibonacci(int i) {
		if (i < 2)
			return i;
		
		else return fibonacci(i - 1) + fibonacci(i - 2);
	}
	
	// Parse a String into parameters and an operation
	// eg, given "5 + 2", return add(5, 2)
	// Look into String.parse()
	public int parse(String s) {
		// "5 + 2"
		String[] parts = s.split(" ");
		
		// "fib E e E"
		
		//								  0    1    2
		// Now we have array like this: ["5", "+", "2"]
		
		if (Character.isDigit(parts[0].charAt(0))) {
			int firstNum = Integer.parseInt(parts[0]),
					secondNum = Integer.parseInt(parts[2]);
			
			switch(parts[1]) {
			case "+":
				return add(firstNum, secondNum);
			case "-":
				return subtract(firstNum, secondNum);
			case "*":
				return multiply(firstNum, secondNum);
			case "/":
				return divide(firstNum, secondNum);
			default:
				return 0;
			
			}
		} else {
			switch (parts[0]) {
			case "fib":
				// fib 25
				return fibonacci(Integer.parseInt(parts[1]));
			case "exp":
				// exp 10 2
				return exponent(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
			default:
				return 0;
			}
		}	
	}
}
