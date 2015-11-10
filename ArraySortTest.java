/*
 * Adhyaksa Pribadi
 * Deliverable 4
 * ArraySort Property-Based
 * Testing
 */

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.*;

import org.junit.*;

public class ArraySortTest {
	
	/*
	 * Populate a HashMap with keys from 0 to 99
	 * with random arrays of random sizes
	 */
	public static Map<Integer, Integer[]> popArrays() {
		Map<Integer, Integer[]> hm = new HashMap<Integer, Integer[]>();
		for(int i = 0; i < 100; i++){	//fill an array with random integers

			Random generator = new Random();
			int SIZE = generator.nextInt(32) + 1; //prevent running out of memory in jvm
			Integer[] arrs = new Integer[SIZE];

			for (int j = 0; j < arrs.length; j++){
			      arrs[j] = generator.nextInt(100);
			    }
			hm.put(i, arrs); //put the array into the hashmap

		}
		return hm;
	}


	@Test
	/*
	 * Property 1
	 * Array will be the same size after it
	 * is sorted
	 */
	public void testSize() {
		Map<Integer, Integer[]> hm = popArrays();
		for(int index = 0; index < hm.size(); index++) {// loop through all arrays
			Integer[] orig = hm.get(index);
			Integer[] notSorted = new Integer[orig.length];
	
			System.arraycopy(orig,0,notSorted,0, orig.length );//make a copy of the array
			Arrays.sort(orig);//sort the original array
	
			assertEquals(orig.length, notSorted.length);// check length are equal
		}
	}

	@Test
	/*
	 * Property 2
	 * Arrays should have the same contents pre and post sort
	 */
	public void testContents () {
		Map<Integer, Integer[]> hm = popArrays();
		for(int index = 0; index < hm.size(); index++) {// loop through all arrays
			Integer[] orig = hm.get(index);
			Integer[] notSorted = new Integer[orig.length];
			System.arraycopy(orig,0,notSorted,0, orig.length );//make a copy of the array			
			Arrays.sort(orig);	//sort the original array
			for(int i = 0; i < notSorted.length; i++) {// check that all elements in sorted array are in original array
				if(Arrays.binarySearch(orig, notSorted[i]) < 0) {
					fail("testContents");
				}
			}
		}
	}
	
	@Test
	/*
	 * Property 3
	 * Arrays should stay sorted when sort is used
	 * on it a second time.
	 */
	public void testArrayIdempotent () {
		Map<Integer, Integer[]> hm = popArrays();
		for(int index = 0; index < hm.size(); index++) {// loop through all arrays
			Integer[] orig = hm.get(index);
			Integer[] notSorted = new Integer[orig.length];
			System.arraycopy(orig,0,notSorted,0, orig.length );//make a copy of the array			
			Arrays.sort(orig);	//sort the original array
			System.arraycopy(orig,0,notSorted,0, orig.length );//make a copy of the array
			Arrays.sort(orig);// pass sorted array to Arrays.sort again

			
			// check to make sure contents have not changed
			assertArrayEquals(notSorted, orig);
		}
	}

}

