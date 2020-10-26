package fizzBuzz;

public class Driver {
	
	private static void FizzBuzz( int a ) {
		for( int i = 1; i <= a; i++ ) {
			if( ( i % 3) == 0 && ( i % 5 ) == 0 ) {
				System.out.println( "FizzBuzz" );
			}
			else if( ( i % 3) == 0 ) {
				System.out.println( "Fizz" );
			}
			else if( ( i % 5) == 0 ) {
				System.out.println( "Buzz" );
			}
			else {
				System.out.println( i );
			}
		}
	}

	public static void main( String args[] ) {
		int a = 30;
		FizzBuzz( a );
	}
}
