package exercise3;

public class CircularlyLinkedList<E> implements Cloneable {

    // Nested Node class
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    }

    // Instance variables
    private Node<E> tail = null;
    private int size = 0;

    // Constructor
    public CircularlyLinkedList() { }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // Rotate the first element to the back of the list
    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);  // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;  // only one node
        else
            tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    /**
     * Creates a shallow copy of this CircularlyLinkedList.
     * The new list has its own nodes but shares element references.
     */
    @SuppressWarnings("unchecked")
    public CircularlyLinkedList<E> clone() throws CloneNotSupportedException {
        // First use inherited Object.clone()
        CircularlyLinkedList<E> other = (CircularlyLinkedList<E>) super.clone();

        // If list is empty, return the clone as is
        if (size == 0) {
            return other;
        }

        // Create new nodes for the cloned list
        other.tail = null;
        other.size = 0;

        // Start from the first node (after tail)
        Node<E> current = tail.getNext();  // first node
        Node<E> firstNewNode = null;
        Node<E> prevNewNode = null;

        // Traverse all nodes in the circular list
        for (int i = 0; i < size; i++) {
            Node<E> newNode = new Node<>(current.getElement(), null);

            if (firstNewNode == null) {
                firstNewNode = newNode;  // remember the first node
            }

            if (prevNewNode != null) {
                prevNewNode.setNext(newNode);
            }

            prevNewNode = newNode;
            current = current.getNext();
        }

        // Link the last node back to the first (circular link)
        prevNewNode.setNext(firstNewNode);

        // Set tail to the last node
        other.tail = prevNewNode;
        other.size = this.size;

        return other;
    }

    // Display method for testing
    public String toString() {
        if (isEmpty()) return "()";

        StringBuilder sb = new StringBuilder("(");
        Node<E> current = tail.getNext();  // start at first node
        for (int i = 0; i < size; i++) {
            sb.append(current.getElement());
            current = current.getNext();
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
