package temp;
/*
 *Write a method to decide if two strings are anagrams or not.
 */
public class AnagramTest {

    //If the characters in str1 are unique, we need not do
    // the swapping step I am doing.
    //Here I am trying to swap characters in str2 to make it
    //s1.
    public static boolean v0(String str1, String str2) {
	int len1 = str1.length();
	int len2 = str2.length();

	if(len1 != len2)
	    return false;
	else if (str1.equals(str2))
	    return false;

	char x[] = str1.toCharArray();
	char y[] = str2.toCharArray();

	for(int i =0; i < len1; i++) {
	    for(int j = i; j < len2; j++) {
		if(x[i] == y[j]) {
		    char temp = y[i];
		    y[i] = y[j];
		    y[j] = temp;
		    // System.out.println(String.copyValueOf(y));
		    break;
		}else
		    return false;
	    }
	}

	return true;
    }

    public static void main(String args[]) {
	boolean ret = v0(args[0], args[1]);
	System.out.println(""+ret);
    }

}
