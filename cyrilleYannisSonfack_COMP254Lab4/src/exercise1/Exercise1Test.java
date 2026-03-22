package exercise1;

public class Exercise1Test {

    public static void run() {
        testIndexOf();
        System.out.println("Exercise 1 OK (indexOf).");
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

    // Exercise 1: indexOf(p) for PositionalList using only interface methods.
    private static void testIndexOf() {
        PositionalList<Integer> L = new LinkedPositionalList<>();

        PositionalList.Position<Integer> p0 = L.addLast(10);
        PositionalList.Position<Integer> p1 = L.addLast(20);
        PositionalList.Position<Integer> p2 = L.addLast(30);

        System.out.println("Exercise 1 - BEFORE:");
        System.out.println("List L = " + L);

        assertEquals("indexOf(first)", 0, L.indexOf(p0));
        assertEquals("indexOf(middle)", 1, L.indexOf(p1));
        assertEquals("indexOf(last)", 2, L.indexOf(p2));

        System.out.println("indexOf(p0) = " + L.indexOf(p0));
        System.out.println("indexOf(p1) = " + L.indexOf(p1));
        System.out.println("indexOf(p2) = " + L.indexOf(p2));

        PositionalList<Integer> L2 = new LinkedPositionalList<>();
        PositionalList.Position<Integer> foreignPos = L2.addLast(999);
        assertEquals("indexOf(foreign position)", -1, L.indexOf(foreignPos));
        System.out.println("indexOf(foreignPos from L2) = " + L.indexOf(foreignPos));

        assertTrue("list not empty", !L.isEmpty());
        System.out.println("Exercise 1 - AFTER:");
        System.out.println("List L = " + L);
    }
}

