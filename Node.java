//import java.lang.IllegalArgumentException;

public class Node<T> {
    
    private T data;
    private Node<T> next;

    public Node(T aData) {
	if (aData == null)
	    throw new IllegalArgumentException();
	this.data = aData;
    }
    
    public T getData() {
	return this.data;
    }

    public Node<T> getNext() {
	return this.next;
    }

    @Override
    public String toString() {
	return this.data.toString();
    }

    public void setNext(Node<T> node) {
	this.next = node;
    }

    public void addToTail(T data) {
	Node<T> node = new Node<T>(data);
	Node<T> n = this;
	
	while (n.next != null) {
	    n = n.next;
	}
	
	n.next = node;
    }

    public Node deleteNode(Node<T> head,T data) {
	Node<T> n = head;
	if (n.getData() == data) {
	    return head.getNext();
	}
	while (n.getNext()  != null) { 
	    if (n.getNext().getData() == data) {
		n.setNext(n.getNext().getNext());
		return head;
	    }
	    n = n.getNext();
	}
	
	return head;
    }
}

