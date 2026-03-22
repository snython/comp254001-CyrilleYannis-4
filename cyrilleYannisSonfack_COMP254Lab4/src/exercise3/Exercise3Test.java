package exercise3;

public class Exercise3Test {

    public static void run() {
        testConcatenate();
        System.out.println("Exercise 3 OK (concatenate queue).");
    }

    private static void assertEquals(String label, Object expected, Object actual) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new RuntimeException(label + " expected=[" + expected + "] actual=[" + actual + "]");
        }
    }

    private static void assertTrue(String label, boolean condition) {
        if (!condition) {
            throw new RuntimeException("Assertion failed: " + label);
        }
    }

    // Exercise 3: concatenate(Q2) for LinkedQueue in O(1) time.
    private static void testConcatenate() {
        LinkedQueue<Integer> Q1 = new LinkedQueue<>();
        Q1.enqueue(1);
        Q1.enqueue(2);

        LinkedQueue<Integer> Q2 = new LinkedQueue<>();
        Q2.enqueue(3);
        Q2.enqueue(4);
        Q2.enqueue(5);

        System.out.println("Exercise 3 - BEFORE:");
        System.out.println("Q1 = " + Q1);
        System.out.println("Q2 = " + Q2);
        Q1.concatenate(Q2);
        System.out.println("Exercise 3 - AFTER Q1.concatenate(Q2):");
        System.out.println("Q1 = " + Q1);
        System.out.println("Q2 = " + Q2);

        assertTrue("Q2 empty after concatenate", Q2.isEmpty());

        assertEquals("Q1.dequeue #1", 1, Q1.dequeue());
        assertEquals("Q1.dequeue #2", 2, Q1.dequeue());
        assertEquals("Q1.dequeue #3", 3, Q1.dequeue());
        assertEquals("Q1.dequeue #4", 4, Q1.dequeue());
        assertEquals("Q1.dequeue #5", 5, Q1.dequeue());
        System.out.println("After dequeue all, Q1 = " + Q1);
        assertTrue("Q1 empty after dequeue all", Q1.isEmpty());
    }
}

