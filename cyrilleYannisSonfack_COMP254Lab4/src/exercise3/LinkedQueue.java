package exercise3;

public class LinkedQueue<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
            tail = newest;
        } else {
            tail.next = newest;
            tail = newest;
        }
        size++;
    }

    public E first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return head.element;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        E answer = head.element;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    public void concatenate(LinkedQueue<E> Q2) {
        if (Q2 == null) {
            throw new IllegalArgumentException("Q2 must not be null.");
        }
        if (Q2 == this) {
            throw new IllegalArgumentException("Q2 must be a different queue.");
        }
        if (Q2.isEmpty()) {
            return;
        }

        if (this.isEmpty()) {
            this.head = Q2.head;
            this.tail = Q2.tail;
            this.size = Q2.size;
        } else {
            this.tail.next = Q2.head;
            this.tail = Q2.tail;
            this.size += Q2.size;
        }

        Q2.head = null;
        Q2.tail = null;
        Q2.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[FRONT ");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.element);
            walk = walk.next;
            if (walk != null) {
                sb.append(" -> ");
            }
        }
        sb.append(" REAR]");
        return sb.toString();
    }
}

