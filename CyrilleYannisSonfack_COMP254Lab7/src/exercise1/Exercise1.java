package exercise1;

/**
 * Test class for Exercise 1 — Iterative treeSearch in a BST.
 */
public class Exercise1 {

    public static void main(String[] args) {

        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        // Build the tree
        //        6
        //       / \
        //      2   9
        //     / \ /
        //    1  4 8
        bst.put(6, "six");
        bst.put(2, "two");
        bst.put(9, "nine");
        bst.put(1, "one");
        bst.put(4, "four");
        bst.put(8, "eight");

        System.out.println("=== Exercise 1: Iterative treeSearch ===\n");

        // Search for keys that exist
        int[] searchKeys = {6, 2, 9, 1, 4, 8};
        System.out.println("Searching for existing keys:");
        for (int k : searchKeys) {
            BinarySearchTree.Node<Integer, String> result = bst.treeSearch(k);
            System.out.println("  treeSearch(" + k + ") -> " + result);
        }

        // Search for keys that do NOT exist
        int[] missingKeys = {5, 7, 10};
        System.out.println("\nSearching for missing keys:");
        for (int k : missingKeys) {
            BinarySearchTree.Node<Integer, String> result = bst.treeSearch(k);
            System.out.println("  treeSearch(" + k + ") -> " + result + " (not found)");
        }

        // Use get() which also calls treeSearch internally
        System.out.println("\nUsing get():");
        System.out.println("  get(4)  = " + bst.get(4));
        System.out.println("  get(99) = " + bst.get(99) + " (not found)");
    }
}
