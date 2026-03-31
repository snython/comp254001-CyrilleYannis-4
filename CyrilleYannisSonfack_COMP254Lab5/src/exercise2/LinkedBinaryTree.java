package exercise2;

public class LinkedBinaryTree<E> {

    public static class Node<E> {
        E element;
        Node<E> parent, left, right;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }
    }

    private Node<E> root;
    private int size;

    public Node<E> addRoot(E e) {
        root = new Node<E>(e, null);
        size = 1;
        return root;
    }

    public Node<E> addLeft(Node<E> parent, E e) {
        parent.left = new Node<E>(e, parent);
        size++;
        return parent.left;
    }

    public Node<E> addRight(Node<E> parent, E e) {
        parent.right = new Node<E>(e, parent);
        size++;
        return parent.right;
    }

    public Node<E> root() { return root; }

    /**
     * Prints every position's element followed by the height of its subtree.
     * Uses a postorder traversal: children are processed before the parent,
     * so each subtree height is known when the parent is visited.
     * Height is 0 for a leaf and 1 + max(child heights) otherwise.
     */
    public void printHeights() {
        System.out.printf("%-10s %s%n", "Element", "Height");
        System.out.println("-------------------");
        printHeights(root);
    }

    private int printHeights(Node<E> p) {
        if (p == null) return -1;

        int leftH  = printHeights(p.left);
        int rightH = printHeights(p.right);
        int h = 1 + Math.max(leftH, rightH);

        System.out.printf("%-10s %d%n", p.element, h);
        return h;
    }
}
