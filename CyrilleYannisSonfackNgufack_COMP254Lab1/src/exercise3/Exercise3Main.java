package exercise3;

/**
 * Test class for CircularlyLinkedList clone method.
 * Author: Cyrille Yannis Sonfack Ngufack
 */
public class Exercise3Main {

    public static void main(String[] args) {
        System.out.println("Exercise 3: Testing CircularlyLinkedList clone");
        System.out.println("==============================================\n");

        try {
            // Create original list
            CircularlyLinkedList<String> original = new CircularlyLinkedList<>();
            original.addLast("A");
            original.addLast("B");
            original.addLast("C");
            original.addLast("D");

            System.out.println("Original list: " + original);
            System.out.println("Original size: " + original.size());

            // Clone the list
            CircularlyLinkedList<String> cloned = original.clone();

            System.out.println("\nCloned list:   " + cloned);
            System.out.println("Cloned size:   " + cloned.size());

            // Verify they are separate lists by modifying cloned list
            System.out.println("\n--- Modifying cloned list ---");
            cloned.addFirst("X");
            cloned.removeFirst();
            cloned.addLast("E");

            System.out.println("Original list after modifying clone: " + original);
            System.out.println("Cloned list after modification:      " + cloned);

            // Test rotation on cloned list
            System.out.println("\n--- Testing rotation on cloned list ---");
            cloned.rotate();
            System.out.println("Cloned list after rotate: " + cloned);
            System.out.println("Original list (unchanged): " + original);

            // Test cloning empty list
            System.out.println("\n--- Testing clone of empty list ---");
            CircularlyLinkedList<Integer> emptyList = new CircularlyLinkedList<>();
            CircularlyLinkedList<Integer> clonedEmpty = emptyList.clone();
            System.out.println("Empty list:  " + emptyList);
            System.out.println("Cloned empty: " + clonedEmpty);

        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported: " + e.getMessage());
        }
    }
}
