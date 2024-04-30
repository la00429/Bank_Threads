package co.edu.uptc.structures;

public class Node<E> {
	private E data;
	private Node<E> next;

	public Node(E data) {
		this.data = data;
	}

	public E getValue() {
		return this.data;
	}

	public Node<E> getNext() {
		return this.next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return data + "->" + next;
	}
}
