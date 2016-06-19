package temp;
/*
 *Write a method to replace all spaces in a string with ‘%20’.
 */
public class EncodeStringSpaces {

    public static void v0(String s) {
	int spaceCount = 0;
	int len = s.length();

	char str[] = s.toCharArray();

	for(int i = 0; i < len; i++)
	    if(str[i] == ' ')
		spaceCount++;

       	char newArr[] = new char[len + 3 * spaceCount - 2];
	int j = 0;

	for(int i = 0; i < len; i++) {
	    if(str[i] == ' ') {
		newArr[j++] = '%';
		newArr[j++] = '2';
		newArr[j++] = '0';
	    }else {
		newArr[j++] = str[i];
	    }
	}

	System.out.println(String.copyValueOf(newArr));
    }

    public static void main(String args[]) {
	System.out.println(args[0].replaceAll("\\s", "%20"));
	v0(args[0]);
    }
}
