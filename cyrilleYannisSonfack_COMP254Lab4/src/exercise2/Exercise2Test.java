package exercise2;

public class Exercise2Test {

    public static void run() {
        testTransfer();
        System.out.println("Exercise 2 OK (transfer stack).");
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

    // Exercise 2: transfer(S, T) for stacks.
    private static void testTransfer() {
        LinkedStack<Integer> S = new LinkedStack<>();
        S.push(1);
        S.push(2);
        S.push(3); // top

        LinkedStack<Integer> T = new LinkedStack<>();
        System.out.println("Exercise 2 - BEFORE:");
        System.out.println("S = " + S);
        System.out.println("T = " + T);
        LinkedStack.transfer(S, T);
        System.out.println("Exercise 2 - AFTER transfer(S, T):");
        System.out.println("S = " + S);
        System.out.println("T = " + T);

        assertTrue("S is empty after transfer", S.isEmpty());

        // bottom of S ends up at top of T => popping T yields 1,2,3.
        assertEquals("T.pop #1", 1, T.pop());
        assertEquals("T.pop #2", 2, T.pop());
        assertEquals("T.pop #3", 3, T.pop());
        System.out.println("After popping T fully, T = " + T);
        assertTrue("T empty after popping all", T.isEmpty());
    }
}

