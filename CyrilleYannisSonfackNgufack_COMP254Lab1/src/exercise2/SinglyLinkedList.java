package exercise2;

public class SinglyLinkedList<E> {

    // Nested Node class
    public static class Node<E> {
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
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    // Constructor
    public SinglyLinkedList() { }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0)
            tail = head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;
        return answer;
    }

    // Get node at specific index (helper for testing)
    public Node<E> getNode(int index) {
        if (index < 0 || index >= size) return null;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Swaps two nodes in the list (not just their contents).
     * @param node1 first node to swap
     * @param node2 second node to swap
     */
    public void swapNodes(Node<E> node1, Node<E> node2) {
        // Check if nodes are the same
        if (node1 == node2) {
            System.out.println("Same node - no swap needed");
            return;
        }

        // Check if nodes are null
        if (node1 == null || node2 == null) {
            System.out.println("One or both nodes are null - cannot swap");
            return;
        }

        // Find predecessors of node1 and node2 by traversing the list
        Node<E> prev1 = null;
        Node<E> prev2 = null;
        Node<E> current = head;

        while (current != null) {
            if (current.getNext() == node1) {
                prev1 = current;
            }
            if (current.getNext() == node2) {
                prev2 = current;
            }
            current = current.getNext();
        }

        // Handle case where node1 is head
        if (head == node1) {
            prev1 = null;
        }
        // Handle case where node2 is head
        if (head == node2) {
            prev2 = null;
        }

        // Save the next pointers
        Node<E> next1 = node1.getNext();
        Node<E> next2 = node2.getNext();

        // Check if nodes are adjacent
        if (node1.getNext() == node2) {
            // node1 is directly before node2
            if (prev1 != null) {
                prev1.setNext(node2);
            } else {
                head = node2;
            }
            node2.setNext(node1);
            node1.setNext(next2);
        } else if (node2.getNext() == node1) {
            // node2 is directly before node1
            if (prev2 != null) {
                prev2.setNext(node1);
            } else {
                head = node1;
            }
            node1.setNext(node2);
            node2.setNext(next1);
        } else {
            // Nodes are not adjacent
            if (prev1 != null) {
                prev1.setNext(node2);
            } else {
                head = node2;
            }

            if (prev2 != null) {
                prev2.setNext(node1);
            } else {
                head = node1;
            }

            node1.setNext(next2);
            node2.setNext(next1);
        }

        // Update tail if needed
        if (tail == node1) {
            tail = node2;
        } else if (tail == node2) {
            tail = node1;
        }
    }

    // Display method for testing
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
