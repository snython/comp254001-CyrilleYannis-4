package exercise2;

/**
 * Test class for SinglyLinkedList swapNodes method.
 * Author: Cyrille Yannis Sonfack Ngufack
 */
public class Exercise2Main {

    public static void main(String[] args) {
        System.out.println("Exercise 2: Testing SinglyLinkedList swapNodes");
        System.out.println("==============================================\n");

        // Test 1: Swap two middle nodes
        System.out.println("Test 1: Swap two middle nodes (B and D)");
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        list1.addLast("A");
        list1.addLast("B");
        list1.addLast("C");
        list1.addLast("D");
        list1.addLast("E");

        System.out.println("Before swap: " + list1);
        SinglyLinkedList.Node<String> nodeB = list1.getNode(1);
        SinglyLinkedList.Node<String> nodeD = list1.getNode(3);
        list1.swapNodes(nodeB, nodeD);
        System.out.println("After swap:  " + list1);

        // Test 2: Swap adjacent nodes
        System.out.println("\nTest 2: Swap adjacent nodes (B and C)");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
        list2.addLast("A");
        list2.addLast("B");
        list2.addLast("C");
        list2.addLast("D");

        System.out.println("Before swap: " + list2);
        SinglyLinkedList.Node<String> node2B = list2.getNode(1);
        SinglyLinkedList.Node<String> node2C = list2.getNode(2);
        list2.swapNodes(node2B, node2C);
        System.out.println("After swap:  " + list2);

        // Test 3: Swap first and last nodes
        System.out.println("\nTest 3: Swap first and last nodes (A and D)");
        SinglyLinkedList<String> list3 = new SinglyLinkedList<>();
        list3.addLast("A");
        list3.addLast("B");
        list3.addLast("C");
        list3.addLast("D");

        System.out.println("Before swap: " + list3);
        SinglyLinkedList.Node<String> node3A = list3.getNode(0);
        SinglyLinkedList.Node<String> node3D = list3.getNode(3);
        list3.swapNodes(node3A, node3D);
        System.out.println("After swap:  " + list3);

        // Test 4: Swap same node
        System.out.println("\nTest 4: Try to swap same node (A with A)");
        SinglyLinkedList<String> list4 = new SinglyLinkedList<>();
        list4.addLast("A");
        list4.addLast("B");

        System.out.println("Before swap: " + list4);
        SinglyLinkedList.Node<String> node4A = list4.getNode(0);
        list4.swapNodes(node4A, node4A);
        System.out.println("After swap:  " + list4);
    }
}
