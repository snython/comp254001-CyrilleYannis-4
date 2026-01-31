package exercise1;

/**
 * Test class for DoublyLinkedList concatenate method.
 * Author: Cyrille Yannis Sonfack Ngufack
 */
public class Exercise1Main {

    public static void main(String[] args) {
        System.out.println("Exercise 1: Testing DoublyLinkedList Concatenation");
        System.out.println("=================================================\n");

        // Create first list L
        DoublyLinkedList<String> L = new DoublyLinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");

        // Create second list M
        DoublyLinkedList<String> M = new DoublyLinkedList<>();
        M.addLast("D");
        M.addLast("E");
        M.addLast("F");

        System.out.println("List L before concatenation: " + L);
        System.out.println("List L size: " + L.size());
        System.out.println("List M before concatenation: " + M);
        System.out.println("List M size: " + M.size());

        // Concatenate M to L
        L.concatenate(M);

        System.out.println("\nAfter concatenating M to L:");
        System.out.println("List L (now L'): " + L);
        System.out.println("List L' size: " + L.size());
        System.out.println("List M (should be empty): " + M);
        System.out.println("List M size: " + M.size());

        // Test with empty list
        System.out.println("\n--- Testing with empty list ---");
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        list1.addLast(1);
        list1.addLast(2);

        DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<>();

        System.out.println("List1: " + list1);
        System.out.println("Empty list: " + emptyList);

        list1.concatenate(emptyList);

        System.out.println("After concatenating empty list to list1: " + list1);
        System.out.println("List1 size: " + list1.size());
    }
}
