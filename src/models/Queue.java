package models;

public class Queue<T> {

	private Node<T> firstNode;
	private Node<T> finishNode;

	public Queue() {
		this.finishNode = null;
		this.firstNode = null;
	}

	public void push(T data) {
		Node<T> temp = new Node<T>(data);
		if (firstNode == null) {
			firstNode = temp;
			finishNode= temp;
		}else {			
			finishNode.setNext(temp);
			finishNode = finishNode.getNext();
		}
	}

	public T pop() {
		Node<T> tempNode = firstNode;
		firstNode = firstNode.getNext();
		return tempNode.getData();
	}

	public boolean isEmpty() {
		return firstNode == null;
	}
	
	public Node<T> getFirstNode() {
		return firstNode;
	}

}
