package ArrayListPkg;


public class ArrayListTest {

	public static void main(String[] args) {
		
		String s = "Hello";
		ArrayList<String> a1 = new ArrayList();
		
		a1.add( s );
		System.out.println( "Getting array[0] " + a1.get(0) );
		System.out.println( "Getting array[1] (should be null) " + a1.get(1) );
	}

}
