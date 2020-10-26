package myFirstServlet;


public class Main {
	//public class Main {
	    


	    public static void main()
	    {
	    	//INPUT [uncomment & modify if required]
	        String sampleInput = 0;
	        int result = -404;
	        
	        String s = String.Parse(Console.ReadLine());
	        
	        System.out.println( s );
	        
	        //write your Logic here:
	        int count = 0;
			for( int i=0; i<s.length; i++) {
			    String a = s.charAt(0);
			    s = s.substring( s, 1, s.length-1 );
			    if( s.indexOf( a, s ) > -1 ) {
			        count++;
			    }

			    System.out.println( a );		    
			    System.out.println( s );
			}
	    	
	        //OUTPUT [uncomment & modify if required]
	        Console.WriteLine(result);
	    }


	    
	//}
}
