package exercise1;

import maps.ChainHashMap;
import maps.ProbeHashMap;

import java.util.Random;

/**
 * Exercise1 — COMP254 Lab 6, Exercise 1
 * Student  : Cyrille Yannis Sonfack
 *
 * Demonstrates and measures the configurable load factor added to
 * AbstractHashMap, tested on ChainHashMap and ProbeHashMap.
 */
public class Exercise1 {

    // ======================================================================
    // SECTION 1 — Basic correctness: put / get / remove / update
    // ======================================================================

    static void demoBasicOperations() {
        System.out.println("============================================================");
        System.out.println("  EXERCISE 1 — SECTION 1: Basic Operations");
        System.out.println("============================================================\n");

        // --- ChainHashMap with custom load factor ---
        System.out.println("ChainHashMap  (capacity=17, maxLoad=0.75)");
        System.out.println("-----------------------------------------");
        ChainHashMap<String, Integer> chain = new ChainHashMap<>(17, 0.75);
        chain.put("alice", 10);
        chain.put("bob",   20);
        chain.put("carol", 30);
        System.out.println("Inserted: alice=10, bob=20, carol=30");
        System.out.println("  get(alice) = " + chain.get("alice"));   // 10
        System.out.println("  get(bob)   = " + chain.get("bob"));     // 20
        System.out.println("  get(dave)  = " + chain.get("dave"));    // null
        System.out.println("  size       = " + chain.size());         // 3
        chain.put("alice", 99);
        System.out.println("After put(alice, 99) -> get(alice) = " + chain.get("alice")); // 99
        chain.remove("bob");
        System.out.println("After remove(bob)    -> get(bob)   = " + chain.get("bob"));  // null
        System.out.println("  size after removes = " + chain.size());  // 2
        System.out.println();

        // --- ProbeHashMap with custom load factor ---
        System.out.println("ProbeHashMap  (capacity=17, maxLoad=0.6)");
        System.out.println("-----------------------------------------");
        probe.put("x", 1);
        probe.put("y", 2);
        probe.put("z", 3);
        System.out.println("Inserted: x=1, y=2, z=3");
        System.out.println("  get(x) = " + probe.get("x"));    // 1
        System.out.println("  get(y) = " + probe.get("y"));    // 2
        System.out.println("  get(w) = " + probe.get("w"));    // null
        probe.remove("y");
        System.out.println("After remove(y) -> get(y) = " + probe.get("y"));   // null
        System.out.println("After remove(y) -> get(z) = " + probe.get("z"));   // 3 (DEFUNCT keeps chain intact)
        System.out.println();

        // --- setMaxLoad method ---
        System.out.println("Using setMaxLoad() method:");
        ChainHashMap<Integer, Integer> m = new ChainHashMap<>(17); // default 0.5
        System.out.println("  Default maxLoad = " + m.getMaxLoad());
        m.setMaxLoad(0.8);
        System.out.println("  After setMaxLoad(0.8) = " + m.getMaxLoad());
        System.out.println();
    }

    // ======================================================================
    // SECTION 2 — Efficiency experiment
    // ======================================================================

    static void runExperiment() {
        System.out.println("============================================================");
        System.out.println("  EXERCISE 1 — SECTION 2: Efficiency Experiment");
        System.out.println("============================================================\n");

        System.out.println("Methodology:");
        System.out.println("  - 10,000 random Integer keys, fixed seed (reproducible).");
        System.out.println("  - Each test: insert all keys, then look up all keys.");
        System.out.println("  - Timed in milliseconds for ChainHashMap and ProbeHashMap.");
        System.out.println("  - Repeated for maxLoad = 0.3, 0.5, 0.7, 0.9.");
        System.out.println();

        final int    N    = 10_000;
        final Random rand = new Random(42);

        Integer[] keys = new Integer[N];
        for (int i = 0; i < N; i++)
            keys[i] = rand.nextInt(500_000);

        double[] loads = { 0.3, 0.5, 0.7, 0.9 };

        System.out.printf("%-15s  %-22s  %-22s%n",
                "maxLoad", "ChainHashMap (ms)", "ProbeHashMap (ms)");
        System.out.println("-------------------------------------------------------------");

        for (double load : loads) {
            ChainHashMap<Integer, String> chainMap = new ChainHashMap<>(17, load);
            long t0 = System.nanoTime();
            for (Integer k : keys) chainMap.put(k, "v" + k);
            for (Integer k : keys) chainMap.get(k);
            long chainMs = (System.nanoTime() - t0) / 1_000_000;

            ProbeHashMap<Integer, String> probeMap = new ProbeHashMap<>(17, load);
            t0 = System.nanoTime();
            for (Integer k : keys) probeMap.put(k, "v" + k);
            for (Integer k : keys) probeMap.get(k);
            long probeMs = (System.nanoTime() - t0) / 1_000_000;

            System.out.printf("%-15.1f  %-22d  %-22d%n", load, chainMs, probeMs);
        }

        System.out.println();
        System.out.println("Analysis:");
        System.out.println("  * Lower maxLoad -> more resizes, fewer collisions per lookup.");
        System.out.println("  * Higher maxLoad -> fewer resizes, more collisions.");
        System.out.println("  * ChainHashMap handles high loads better (chains grow freely).");
        System.out.println("  * ProbeHashMap degrades faster at high loads (fixed array clusters).");
        System.out.println();
    }

    public static void main(String[] args) {
        demoBasicOperations();
        runExperiment();
    }
}
