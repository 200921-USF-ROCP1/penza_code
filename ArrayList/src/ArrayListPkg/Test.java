package ArrayListPkg;

import java.io.*;
import java.util.*;

public class Test {


		    /*
		     * Complete the pageCount function below.
		     */
		    static int pageCount(int n, int p) {
		        /*
		         * Write your code here.
		         */
		        int pCount = 0;
		        
		        // The student only needs to open the book for the first or last page
		        // not n-1 necessarily
		        if( p == 1 || p == n || p == n-1 ) {
		            pCount = 0;  
		        }
		        else {
		            
		        }
		        
		        return( pCount );

		    }

		    private static final Scanner scanner = new Scanner(System.in);

		    public static void main(String[] args) throws IOException {
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
		    }

    
    }