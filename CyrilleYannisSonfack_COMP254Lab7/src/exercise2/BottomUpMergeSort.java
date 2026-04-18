package exercise2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Bottom-up merge sort using a queue of queues.
 *
 * Exercise 2 — COMP254 Lab 7
 *
 * Algorithm:
 *   1. Place each item into its own single-element queue.
 *   2. Collect all those queues into one outer "queue of queues".
 *   3. Repeatedly dequeue two queues, merge them into one sorted queue,
 *      and enqueue the merged result back into the outer queue.
 *   4. Stop when only one queue remains — it holds all items in sorted order.
 */
public class BottomUpMergeSort {

    /**
     * Sorts an array of Comparable items and returns a Queue with
     * all elements in ascending order.
     */
    public static <T extends Comparable<T>> Queue<T> sort(T[] items) {

        // Step 1 & 2 — wrap every item in its own queue, then collect them
        Queue<Queue<T>> queueOfQueues = new LinkedList<>();
        for (T item : items) {
            Queue<T> single = new LinkedList<>();
            single.add(item);
            queueOfQueues.add(single);
        }

        // Step 3 — keep merging pairs until one queue is left
        while (queueOfQueues.size() > 1) {
            Queue<Queue<T>> nextRound = new LinkedList<>();

            while (queueOfQueues.size() > 1) {
                Queue<T> q1 = queueOfQueues.poll();
                Queue<T> q2 = queueOfQueues.poll();
                nextRound.add(merge(q1, q2));
            }

            // If there was an odd queue left over, carry it forward
            if (!queueOfQueues.isEmpty()) {
                nextRound.add(queueOfQueues.poll());
            }

            queueOfQueues = nextRound;
        }

        // Step 4 — the single remaining queue is fully sorted
        return queueOfQueues.isEmpty() ? new LinkedList<>() : queueOfQueues.poll();
    }

    /**
     * Merges two sorted queues into one sorted queue.
     */
    private static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        Queue<T> merged = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek().compareTo(q2.peek()) <= 0)
                merged.add(q1.poll());
            else
                merged.add(q2.poll());
        }

        // Drain whichever queue still has elements
        merged.addAll(q1);
        merged.addAll(q2);

        return merged;
    }
}
