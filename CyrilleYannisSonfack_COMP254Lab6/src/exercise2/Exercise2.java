package exercise2;

import maps.SortedTableMap;

/**
 * Exercise2 — COMP254 Lab 6, Exercise 2
 * Student  : Cyrille Yannis Sonfack
 *
 * Tests the containKey(K key) method implemented in SortedTableMap.
 */
public class Exercise2 {

    // ======================================================================
    // SECTION 1 — Normal keys with non-null values
    // ======================================================================

    static void demoNormalKeys() {
        System.out.println("============================================================");
        System.out.println("  EXERCISE 2 — SECTION 1: containKey with non-null values");
        System.out.println("============================================================\n");

        SortedTableMap<Integer, String> map = new SortedTableMap<>();

        map.put(5, "apple");
        map.put(2, "banana");
        map.put(8, "cherry");
        map.put(1, "date");
        map.put(9, "elderberry");
        map.put(4, "fig");
        map.put(6, "grape");

        System.out.println("Entries inserted (keys: 1, 2, 4, 5, 6, 8, 9 — stored sorted)");
        System.out.println();

        System.out.printf("%-6s  %-14s  %-14s%n", "Key", "containKey", "get");
        System.out.println("------------------------------------");
        int[] testKeys = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int k : testKeys) {
            System.out.printf("%-6d  %-14b  %-14s%n",
                    k, map.containKey(k), map.get(k));
        }
        System.out.println();

        System.out.println("--- Update key 5 -> \"AVOCADO\", remove key 2 ---");
        map.put(5, "AVOCADO");
        map.remove(2);
        System.out.println("containKey(5) = " + map.containKey(5) + "  -> " + map.get(5));
        System.out.println("containKey(2) = " + map.containKey(2) + "  (was 'banana', now removed)");
        System.out.println();
    }

    // ======================================================================
    // SECTION 2 — The null-value ambiguity that containKey resolves
    // ======================================================================

    static void demoNullAmbiguity() {
        System.out.println("============================================================");
        System.out.println("  EXERCISE 2 — SECTION 2: The null-value problem");
        System.out.println("============================================================\n");

        SortedTableMap<String, String> map = new SortedTableMap<>();
        map.put("ghost",   null);    // legitimate entry whose value is null
        map.put("present", "hello");

        System.out.println("Map has: (\"ghost\", null)  and  (\"present\", \"hello\")");
        System.out.println();

        System.out.println("Using get() alone — AMBIGUOUS:");
        System.out.println("  get(\"ghost\")   = " + map.get("ghost")
                + "   <- 'not found' OR 'value is null'?");
        System.out.println("  get(\"missing\") = " + map.get("missing")
                + "   <- same result, completely different meaning!");
        System.out.println();

        System.out.println("Using containKey() — UNAMBIGUOUS:");
        System.out.println("  containKey(\"ghost\")   = " + map.containKey("ghost")
                + "   <- key exists (value is intentionally null)");
        System.out.println("  containKey(\"missing\") = " + map.containKey("missing")
                + "  <- key truly absent");
        System.out.println("  containKey(\"present\") = " + map.containKey("present")
                + "   <- key exists with non-null value");
        System.out.println();
    }

    // ======================================================================
    // SECTION 3 — Edge cases
    // ======================================================================

    static void demoEdgeCases() {
        System.out.println("============================================================");
        System.out.println("  EXERCISE 2 — SECTION 3: Edge cases");
        System.out.println("============================================================\n");

        SortedTableMap<Integer, Integer> map = new SortedTableMap<>();

        System.out.println("Empty map:");
        System.out.println("  containKey(1) = " + map.containKey(1));  // false
        System.out.println();

        map.put(5, 500);
        System.out.println("Single entry, key = 5:");
        System.out.println("  containKey(5) = " + map.containKey(5));  // true
        System.out.println("  containKey(4) = " + map.containKey(4));  // false (less than min)
        System.out.println("  containKey(6) = " + map.containKey(6));  // false (greater than max)
        System.out.println();

        map.remove(5);
        System.out.println("After removing the only entry:");
        System.out.println("  containKey(5) = " + map.containKey(5));  // false
        System.out.println();
    }

    public static void main(String[] args) {
        demoNormalKeys();
        demoNullAmbiguity();
        demoEdgeCases();
    }
}
