package exercise1;

/**
 * CyrilleYannisSonfack_COMP254Lab5_Ex1
 *
 * Tree used for testing:
 *
 *           8
 *          / \
 *         3   10
 *        / \    \
 *       1   6    14
 *          / \   /
 *         4   7 13
 *
 * Expected inorder sequence: 1, 3, 4, 6, 7, 8, 10, 13, 14
 */
public class Exercise1Test {

    public static void main(String[] args) {

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();

        LinkedBinaryTree.Node<Integer> root = tree.addRoot(8);
        LinkedBinaryTree.Node<Integer> n3   = tree.addLeft(root, 3);
        LinkedBinaryTree.Node<Integer> n10  = tree.addRight(root, 10);
                                              tree.addLeft(n3, 1);
        LinkedBinaryTree.Node<Integer> n6   = tree.addRight(n3, 6);
                                              tree.addLeft(n6, 4);
                                              tree.addRight(n6, 7);
        LinkedBinaryTree.Node<Integer> n14  = tree.addRight(n10, 14);
                                              tree.addLeft(n14, 13);

        // find the first node in inorder (leftmost)
        LinkedBinaryTree.Node<Integer> current = tree.root();
        while (current.left != null)
            current = current.left;

        System.out.print("Inorder traversal: ");
        while (current != null) {
            System.out.print(current.getElement());
            current = tree.inorderNext(current);
            if (current != null) System.out.print(" -> ");
        }
        System.out.println();

        System.out.println("\ninorderNext(6)  = " + tree.inorderNext(n6).getElement());
        System.out.println("inorderNext(14) = " + tree.inorderNext(n14) + " (last node)");

        System.out.println("\nWorst-case running time: O(h), where h = height of the tree");
        System.out.println("  Skewed tree: O(n)  |  Balanced tree: O(log n)");
    }
}
