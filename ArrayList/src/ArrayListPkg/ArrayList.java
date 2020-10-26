package ArrayListPkg;


/*
 * For this activity, simply implement
 * all of the bwlo methods!
 */
public class ArrayList<T> {
	private T[] arr;
	private int initArrSize = 10;
	private int incArrSize = 10;
	private int arrayCount = 0;
	
	public ArrayList() {
		arr = (T[]) new Object[initArrSize];
	}

	public void add(T t) {
		int arrSize = arr.length;
		
		// If the last array node is being used, increase the array size
		if( arr[arrSize-1] != null) {
			T[] newArr = (T[]) new Object[arrSize+incArrSize];
			
			// Copy all objects from original array
			for( int i=0; i<arrSize; i++ ) {
				newArr[i] = arr[i];
			}
			
			arr = newArr;

		}
		
		// Add new object to array
		arr[arrayCount] = t;
		arrayCount++;
		
	}
	
	public T get(int i) {
		
		T t = null;
		
		if( i >= 0 && i < arrayCount ) {
			t = arr[i];
		}
			
		return( t );
	}
	
	public int size() {
		return arrayCount;
	}
	
	/*
	 * Split should take arr and split into a number of subarrays.
	 * The number is given by numberOfNewArrays.
	 * 
	 * eg if [1, 5, 6, 5, 7] and input 3
	 * so the output would be: 
	 * [
	 *   [1, 5],
	 *   [6, 5],
	 *   [7, null]
	 * ]
	 */
	public T[][] split(int numberOfNewArrays) {
		return null;
	}
}