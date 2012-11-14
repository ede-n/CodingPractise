import java.util.Map;
import java.util.HashMap;
/* Question
 *Design an algorithm and write code to remove the duplicate characters in a string
 *without using any additional buffer. NOTE: One or two additional variables are fine.
 *An extra copy of the array is not.
 *FOLLOW UP
 *Write the test cases for this method.
 */
public class RemoveDuplicates {

    //T(n) = O(n) 
    //Space thrice the size of input ( inputsize + sb_size + uniqueSMap_size );
    public static void v0(String input) {
	//Loop through the elements in order
	StringBuilder sb = new StringBuilder();
	Map<String,Integer> uniqueSMap = new HashMap<String,Integer>();

	for(int i = 0; i < input.length(); i++) {
	    String s = ""+input.charAt(i);
	    if(!uniqueSMap.containsKey(s)) {
		sb.append(s);
		uniqueSMap.put(s,i);
	    }
	}
	System.out.println(sb.toString());
    }

    //Text book solution T(n) = O(n^2)
    public static void v1(String input) {
	char str[] = input.toCharArray();
	int len = input.length();
	int tail = 1; 
	//Invariant: [0..tail-1] is a list of unique characters
	for(int i = 1; i < len; i++) { //for every new element
	    int j;
	    for(j = 0; j < tail; j++) { 
		if(str[j] == str[i]) {//if its already seen
		    break; //skip
		}
	    }
	    if(j == tail) { //If its not already seen
		str[tail] = str[i];//replace the first duplicate character
		tail++;
	    }
	}
	
       	System.out.println(String.copyValueOf(str) + " length of non duplicate substring from index 0: " + tail);
    }

    public static void main(String args[]) {
	
	v0(args[0]);
	v1(args[0]);
    }


}
