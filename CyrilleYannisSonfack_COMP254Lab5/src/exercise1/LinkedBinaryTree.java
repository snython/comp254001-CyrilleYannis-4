package exercise1;

public class LinkedBinaryTree<E> {

    public static class Node<E> {
        E element;
        Node<E> parent, left, right;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }

        public E getElement() { return element; }
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
    public int size()     { return size; }

    /**
     * Returns the position visited after p in an inorder traversal,
     * or null if p is the last node visited.
     *
     * If p has a right child, the successor is the leftmost node in that subtree.
     * Otherwise, climb the tree until p lies in a left subtree; that ancestor is next.
     *
     * Worst-case running time: O(h), where h is the height of the tree.
     */
    public Node<E> inorderNext(Node<E> p) {
        if (p.right != null) {
            Node<E> q = p.right;
            while (q.left != null)
                q = q.left;
            return q;
        } else {
            Node<E> q = p;
            while (q.parent != null) {
                if (q == q.parent.left)
                    return q.parent;
                q = q.parent;
            }
            return null;
        }
    }
}
