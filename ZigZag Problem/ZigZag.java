import java.util.Scanner;
public class ZigZag{
	//Find maximum of two numbers
	public static int max(int a,int b){
		if(a>b)return a;
		return b;
	}
	//Main method
	public static void main (String[] args) {
		//write sequence here
		int sequence[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    	System.out.println(longestZigZag(sequence));
    }
    
    //Method to find length of longest Zig-Zag sequence
    public static int longestZigZag(int []sequence){
    	//Find size of the array
	    int SIZE = sequence.length;
	    /*dpPos will hold the maximum length of subsequence ending at i such that 
	     *difference between last two element is positive
	     */
	    int []dpPos =new int[SIZE];
	    /*dpPos will hold the maximum length of subsequence ending at i such that 
	     *difference between last two element is negative
	     *
	     *And final answer will be the maximum among the last elements of both arrays
	     */
	    int []dpNeg = new int[SIZE];
	    //initialize all elements of dpPos and dpNeg with 1
	    for(int i=0;i<SIZE;i++){
	    	dpPos[i] = dpNeg[i] = 1;
	    }
	    
	    for(int i=0;i<SIZE;i++){
	        for(int j=0;j<i;j++){
	            if(sequence[i]-sequence[j]>0){
	                dpPos[i] = max(dpNeg[j]+1,dpPos[i]);
	            }
	            else if(sequence[i]-sequence[j]<0)
	                dpNeg[i] = max(dpPos[j]+1,dpNeg[i]);
	        }
    	}
    	return max(dpPos[SIZE-1],dpNeg[SIZE-1]);
    }
}