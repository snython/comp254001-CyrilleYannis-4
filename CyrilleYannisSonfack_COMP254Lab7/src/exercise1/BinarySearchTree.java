package exercise1;

/**
 * A simple Binary Search Tree with an iterative treeSearch method.
 *
 * Exercise 1 — COMP254 Lab 7
 * The original treeSearch (Code Fragment 11.3) uses recursion.
 * This version replaces recursion with a while-loop so it never
 * risks a stack-overflow on a large, unbalanced tree.
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    // -------- inner Node class --------
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key   = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    private Node<K, V> root;

    // -------- insert --------
    public void put(K key, V value) {
        root = insertRec(root, key, value);
    }

    private Node<K, V> insertRec(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value);
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left  = insertRec(node.left,  key, value);
        else if (cmp > 0) node.right = insertRec(node.right, key, value);
        else              node.value = value;   // update existing key
        return node;
    }

    // -------- iterative treeSearch (key deliverable) --------
    /**
     * Searches the tree iteratively for the given key using a loop.
     * Returns the matching Node, or null if not found.
     *
     * The recursive version from Code Fragment 11.3:
     *   if (isExternal(p)) return p;
     *   int comp = compare(key, p.getElement());
     *   if (comp == 0)       return p;
     *   else if (comp < 0)   return treeSearch(left(p),  key);
     *   else                 return treeSearch(right(p), key);
     *
     * Here we express the same logic with a while-loop instead of recursion.
     */
    public Node<K, V> treeSearch(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if      (cmp == 0) return current;         // key found
            else if (cmp <  0) current = current.left; // go left
            else               current = current.right;// go right
        }
        return null; // key not found
    }

    // -------- get (uses iterative search) --------
    public V get(K key) {
        Node<K, V> node = treeSearch(key);
        return (node != null) ? node.value : null;
    }
}
