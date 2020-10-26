package fizzBuzz;

public class FizzBuzz {
	
	private FizzBuzz( int a ) {
		for( int i = 1; i <= a; i++ ) {
			if( ( a % 3) == 0 ) {
				System.out.println( "Fizz" );
			}
			else if( ( a % 5) == 0 ) {
				System.out.println( "Buzz" );
			}
			else if( ( a % 3) == 0 && ( a % 5 ) == 0 ) {
				System.out.println( "FizzBuzz" );
			}
			else {
				System.out.println( a );
			}
		}
	}

	public static void main( String args[] ) {
		FizzBuzz f = new FizzBuzz( 10 );
	}
}
