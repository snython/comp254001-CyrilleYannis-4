package exercise1;

/**
 * DoublyLinkedList implementation with header and trailer sentinel nodes.
 */
public class DoublyLinkedList<E> {

    // Nested Node class
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }

    // Instance variables
    private Node<E> header;    // header sentinel
    private Node<E> trailer;   // trailer sentinel
    private int size = 0;

    // Constructor
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    public E removeLast() {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    // Private helper methods
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /**
     * Concatenates list M to the end of this list (L).
     * After this operation, this list contains all elements from L followed by M.
     * List M becomes empty after concatenation.
     */
    public void concatenate(DoublyLinkedList<E> M) {
        // If M is empty, nothing to do
        if (M.isEmpty()) {
            return;
        }

        // Get the last node of L (before trailer) and first node of M (after header)
        Node<E> lastOfL = this.trailer.getPrev();
        Node<E> firstOfM = M.header.getNext();
        Node<E> lastOfM = M.trailer.getPrev();

        // Connect end of L to beginning of M
        lastOfL.setNext(firstOfM);
        firstOfM.setPrev(lastOfL);

        // Connect end of M to trailer of L
        lastOfM.setNext(this.trailer);
        this.trailer.setPrev(lastOfM);

        // Update size
        this.size += M.size;

        // Make M empty by resetting its sentinels
        M.header.setNext(M.trailer);
        M.trailer.setPrev(M.header);
        M.size = 0;
    }

    // Display method
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> current = header.getNext();
        while (current != trailer) {
            sb.append(current.getElement());
            current = current.getNext();
            if (current != trailer) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
