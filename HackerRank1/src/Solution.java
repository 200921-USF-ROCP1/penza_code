import java.io.*;
//import java.util.*;

public class Solution {

    /*
     * Complete the pageCount function below.
     */
    static int pageCount(int n, int p) {
        /*
         * Write your code here.
         */
        int pCount = 0;
        int lastPage = n;
        
        // There should be an easier way using modulo, but I'm stuck thinking about the math
        // This could also be more efficient if you only count from the front or back
        int midPt = n/2;
        
        if ( p <= midPt ) {
        	for( int i = 1; i <= p; i=i+2 ) {      		
	        		pCount++;
	        }
        }
        
        //Past the midpoint.  Count from the back.
        else if ( p >= midPt ) {
        	if( n%2 == 0) {
        		lastPage = n+1;
        	}
            for( int i = lastPage; i-1 > p; i=i-2 ) {
            		pCount++;
            }
        }
        
        return( pCount );

    }
    
    static int saveThePrisoner(int n, int m, int s) {
    	int warn = 0;
    	
    	warn = n%m + s;
    	return warn;

    }

   // private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	/*
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int result = pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
        */
    	
    	int n =4;
    	int p = 6;
    	int s = 2;
    	int result = saveThePrisoner(n, p, s);
    	System.out.println( result );
    }
}
