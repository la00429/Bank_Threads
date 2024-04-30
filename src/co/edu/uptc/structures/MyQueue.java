package co.edu.uptc.structures;

public class MyQueue<T>  {
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head = null;
        this.tail = head;

    }

    public void push(T data) {
        Node<T> node = new Node<T>(data);
        if (isEmpty()) {
            head = node;
            tail = head;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public T poll() {
        T data = head.getValue();
        head = head.getNext();
        return data;
    }


    public T peek() {
        return head.getValue();
    }

    public boolean isEmpty() {
        return head == null;
    }
}
