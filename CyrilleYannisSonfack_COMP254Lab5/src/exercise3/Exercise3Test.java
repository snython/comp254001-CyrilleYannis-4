package exercise3;

/**
 * CyrilleYannisSonfack_COMP254Lab5_Ex3
 *
 * Tests the recursive upheap of HeapPriorityQueue.
 * Keys inserted: 5, 2, 8, 1, 9, 3
 * Expected output in ascending order: 1, 2, 3, 5, 8, 9
 */
public class Exercise3Test {

    public static void main(String[] args) {

        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();

        pq.insert(5, "five");
        pq.insert(2, "two");
        pq.insert(8, "eight");
        pq.insert(1, "one");
        pq.insert(9, "nine");
        pq.insert(3, "three");

        System.out.println("Removing entries in priority order:");
        while (!pq.isEmpty())
            System.out.println("  " + pq.removeMin());
    }
}
