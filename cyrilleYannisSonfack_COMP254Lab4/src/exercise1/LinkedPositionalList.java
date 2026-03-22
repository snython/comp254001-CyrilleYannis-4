package exercise1;

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private class NodePosition implements Position<E> {
        private final Node<E> node;

        NodePosition(Node<E> node) {
            this.node = node;
        }

        @Override
        public E getElement() {
            return node.element;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof LinkedPositionalList.NodePosition)) return false;
            @SuppressWarnings("unchecked")
            NodePosition other = (NodePosition) obj;
            return this.node == other.node;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(node);
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    private Node<E> validate(Position<E> p) {
        if (p == null) {
            throw new IllegalArgumentException("Position must not be null.");
        }
        if (!(p instanceof LinkedPositionalList.NodePosition)) {
            throw new IllegalArgumentException("Invalid position type.");
        }
        NodePosition pos = (NodePosition) p;
        Node<E> node = pos.node;
        if (node.next == null || node.prev == null) {
            throw new IllegalArgumentException("Position is no longer valid.");
        }
        return node;
    }

    private Position<E> makePosition(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return new NodePosition(node);
    }

    private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.next = newest;
        successor.prev = newest;
        size++;
        return new NodePosition(newest);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return makePosition(header.next);
    }

    @Override
    public Position<E> last() {
        return makePosition(trailer.prev);
    }

    @Override
    public Position<E> before(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.prev);
    }

    @Override
    public Position<E> after(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.next);
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.next);
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.prev, trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node.prev, node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node, node.next);
    }

    @Override
    public E replace(Position<E> p, E e) {
        Node<E> node = validate(p);
        E old = node.element;
        node.element = e;
        return old;
    }

    @Override
    public E remove(Position<E> p) {
        Node<E> node = validate(p);
        Node<E> predecessor = node.prev;
        Node<E> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;

        E old = node.element;
        node.prev = null;
        node.next = null;
        node.element = null;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Position<E> walk = first();
        while (walk != null) {
            sb.append(walk.getElement());
            walk = after(walk);
            if (walk != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

