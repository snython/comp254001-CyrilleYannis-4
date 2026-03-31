package exercise2;

/**
 * CyrilleYannisSonfack_COMP254Lab5_Ex2
 *
 * Tree used for testing:
 *
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *          /
 *         7
 */
public class Exercise2Test {

    public static void main(String[] args) {

        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();

        LinkedBinaryTree.Node<Integer> root = tree.addRoot(1);
        LinkedBinaryTree.Node<Integer> n2   = tree.addLeft(root, 2);
        LinkedBinaryTree.Node<Integer> n3   = tree.addRight(root, 3);
                                              tree.addLeft(n2, 4);
        LinkedBinaryTree.Node<Integer> n5   = tree.addRight(n2, 5);
                                              tree.addRight(n3, 6);
                                              tree.addLeft(n5, 7);

        System.out.println("Element and subtree height for every position:");
        System.out.println();
        tree.printHeights();
    }
}
