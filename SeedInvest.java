import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;


public class SeedInvest {

	private static  int[] input = {-1, -1, 4, 2, 1, 25, 0, 3, 2};
	private static int target = 3;
	
	
	
	public static void main(String[] args) throws IOException {
		
		SumPairsBasic(input, target);
		SumPairsHash(input,target);
		SumPairSort(input, target);
		ReadInputFile();
		
	}
	
	
	//O(n^2) solution -> Most basic solution.
	public static void SumPairsBasic(int[] input, int target){
				for(int i=0; i< input.length; i++){
					for(int j=i+1; j<input.length;j++)
					{
						if(input[i] +input[j] == target){
							System.out.println(input[i] +" , "+input[j]);
						}
					}
				}
	}
	
	
	
	//Another method, O(n log n) solution. Sort the input array and do a binary search for the complement 
		public static void SumPairSort(int[] input, int target){
			Arrays.sort(input);
			for(int i=0;i<input.length;i++){
				int found = Arrays.binarySearch(input, target-input[i]);
				if(found >= 0){
					System.out.println(input[i] + " "+(target -input[i]));
				}
			}
			
		}
		
	
//	O(n) solution. Insert complement as the key and element as value, then iterate and if array contains 
//	the key element, it means it meets our condition
	public static void SumPairsHash(int[] input, int target){
		Hashtable<Integer, Integer> numbers = new Hashtable<Integer, Integer>();
		
		for(int i=0;i<input.length;i++){
			if(numbers.containsKey(input[i]))
				System.out.println(input[i] +" , "+ numbers.get(input[i]));
			else
		     numbers.put(target-input[i], input[i]);
			}
		}
	
	
	//Assuming the input is a text file too large to store in memory. Iterate through it line by line and then call
	//one of the methods.
	public static void ReadInputFile() throws IOException{
		File file = new File("/Users/tharanagaraj/Documents/workspace/Practice/src/input.txt");
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		int[] input = new int[30];
		try {
			while(it.hasNext()){
					String line = it.nextLine();
					String[] values = line.trim().split(",\\s*");
					for(int i=0;i< values.length;i++)
					{
						input[i] =  Integer.parseInt(values[i]);
					}
					SumPairsHash(input, target);
				}
			}
		finally {
		    LineIterator.closeQuietly(it);
		}
		
	}

}
