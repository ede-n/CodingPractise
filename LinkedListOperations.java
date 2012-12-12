import java.util.Map;
import java.util.HashMap;
public class LinkedListOperations {
    
    public static <T> Node<T> createLList(T[] t) {
	if (t == null)
	    return null;

	Node<T> head = new Node<T>(t[0]);
	Node<T> prev = head;
	for(int i = 1; i < t.length; i++) {
	    Node<T> node = new Node<T>(t[i]);
	    prev.setNext(node);
	    prev = node;
	}
	
	return head;
    }

    public static <T> void printLList(String header, Node<T> head) {
	
	if (head == null)
	    return;
	
	StringBuilder sb = new StringBuilder();
	sb.append("**" + header + "**\n");

	Node<T> temp = head;
	while(temp != null) {
	    sb.append(temp.toString() + " ");
	    temp = temp.getNext();
	}
	System.out.println(sb.toString());
    }

    /**
     *Write code to remove duplicates from an unsorted linked list.
     *Using a buffer.
     * T(n) = O(n)
     */
    public static <T> void removeDuplicates(final Node<T> head) {
	Map<String,Integer> uniqMap = new HashMap<String,Integer>();
	
	Node<T> prev = head;
	Node<T> temp = prev;
	uniqMap.put(temp.toString(), 1);
	while (temp.getNext() != null) {
	    temp = temp.getNext();
	    if (uniqMap.containsKey(temp.toString())) {
		if(temp.getNext() != null)
		    prev.setNext(temp.getNext());	 
	    } else {
		uniqMap.put(temp.toString(), 1);
	    }
	    prev = temp;
	}
	return;
    }

    /**
     *Write code to remove duplicates from an unsorted linked list,
     *without using a buffer.
     *T(n) = O(n^2)
     */
    public static <T> void removeDups(final Node<T> head) {
	
	Node<T> prev = head;
	Node<T> temp = prev;
	while (temp.getNext() != null) {
	    temp = temp.getNext();
	    Node<T> uniqPointer = head;
	    while (temp != uniqPointer) {
		if (temp.getData().equals(uniqPointer.getData())) {
		    prev.setNext(temp.getNext());
		    break;
		}
		uniqPointer = uniqPointer.getNext();
	    }
	    prev = temp;	    
	}
	return;
    }
    
    /**
     *BEAUTY!!!
     *find the nth to last element.
     *Strategy: If two pointers p1 and p2 are separated by a distance
     *'n', and p2(along with p1) is slid till the end of the list.
     *The element p1 points to is the nth from last.
     */
    public static <T> Node<T> nthToLast(final Node<T> head, int n) {
	
	if (head == null || n < 1)
	    return null;
	
	Node<T> p1 = head;
	Node<T> p2 = head;
	//slide p2 forward to n locations, let p1 stay put.
	for (int i = 0; i < n-1; i++) {
	    if (p2.getNext() != null)
		p2 = p2.getNext();
	    else
		return null;
	}
	//slide p1 and p2 forward.
	while (p2.getNext() == null) {
	    p1 = p1.getNext();
	    p2 = p2.getNext();
	}
	
	return p1;
    }

    /**
     *You have two numbers represented by a linked list, where each node
     *contains a single digit. The digits are stored in reverse order,
     *such that the 1’s digit is at the head of the list.
     *Write a function that adds the two numbers and returns the sum as
     *a linkedlist.
     *EXAMPLE
     *Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
     *Output: 8 -> 0 -> 8
     */
    public static Node<Integer> addIntLLists (Node<Integer> h1, 
					      Node<Integer> h2) {
	int sum = 0;
	int carryFwd = 0;
	Node<Integer> resHead = new Node<Integer>(0);
	Node<Integer> temp = resHead;

	while (h1 != null && h2 != null) {
	    sum = h1.getData() + h2.getData() + carryFwd;
	    Node<Integer> newNode = new Node<Integer>(sum % 10);
	    temp.setNext(newNode);
	    temp = newNode;
	    carryFwd = sum / 10;
	    h1 = h1.getNext();
	    h2 = h2.getNext();
	}

	Node<Integer> head = null;
	if (h1 == null)
	    head = h2;
	if (h2 == null)
	    head = h1;
	
	while (head != null) {
	    sum = head.getData() + carryFwd;
	    Node<Integer> newNode = new Node<Integer>(sum % 10);
	    temp.setNext(newNode);
	    temp = newNode;
	    head = head.getNext();
	    carryFwd = sum / 10;
	}
	
	if (carryFwd == 1)
	    temp.setNext(new Node<Integer>(1));
	
	return resHead.getNext();
    }

    public static void main(String[] args) {

	Integer[] a = {0,1,2,3,4,5,6,7,8,9,10};
	Node<Integer> n = createLList(a);
	printLList("a",n);
	Integer[] b = {0,1,1,2,2,3,4,5,5,6,7,7,8,9,10};
	n = createLList(b);
	printLList("b",n);
	removeDuplicates(n);
	printLList("removeDuplicates",n);
	Integer[] c = {1,1,0,5,8,0};
	n = createLList(c);
	printLList("c",n);
	removeDups(n);
	printLList("removeDups",n);
	n = createLList(a);
	Node<Integer> nth = nthToLast(n,9);
	System.out.println(nth.toString());
	Integer[] x = {8,1,9};
	Integer[] y = {2,5,1};
	n = addIntLLists(createLList(x),createLList(y));
	printLList("addIntLLists",n);
    }
    
}