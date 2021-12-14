package models;

public class Queue<T> {

	private Node<T> firstNode;
	private Node<T> finishNode;

	public Queue() {
		this.finishNode = null;
		this.firstNode = null;
	}

	public void push(T data) {
		if (firstNode == null) {
			firstNode = new Node<T>(data);
			finishNode = firstNode;
		}
		finishNode.setNext(new Node<T>(data));
	}

	public T pop() {
		if (!isEmpty()) {
			Node<T> tempNode = firstNode;
			firstNode = firstNode.getNext();
			return tempNode.getData();
		}
		return null;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

}
