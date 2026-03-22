package exercise2;

public class LinkedStack<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> top = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e) {
        top = new Node<>(e, top);
        size++;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top.element;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        E answer = top.element;
        top = top.next;
        size--;
        return answer;
    }

    public static <E> void transfer(LinkedStack<E> S, LinkedStack<E> T) {
        if (S == null || T == null) {
            throw new IllegalArgumentException("Stacks must not be null.");
        }
        if (S == T) {
            throw new IllegalArgumentException("S and T must be different stacks.");
        }
        while (!S.isEmpty()) {
            T.push(S.pop());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TOP ");
        Node<E> walk = top;
        while (walk != null) {
            sb.append(walk.element);
            walk = walk.next;
            if (walk != null) {
                sb.append(" -> ");
            }
        }
        sb.append(" BOTTOM]");
        return sb.toString();
    }
}

