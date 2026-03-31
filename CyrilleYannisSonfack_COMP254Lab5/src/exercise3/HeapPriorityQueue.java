package exercise3;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> {

    public static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key   = key;
            this.value = value;
        }

        public K getKey()   { return key;   }
        public V getValue() { return value; }

        public String toString() { return "(" + key + ", " + value + ")"; }
    }

    private ArrayList<Entry<K, V>> heap = new ArrayList<Entry<K, V>>();

    @SuppressWarnings("unchecked")
    private Comparator<K> comp = (a, b) -> ((Comparable<K>) a).compareTo(b);

    private int parent(int j) { return (j - 1) / 2; }
    private int left(int j)   { return 2 * j + 1;   }
    private int right(int j)  { return 2 * j + 2;   }

    private void swap(int i, int j) {
        Entry<K, V> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * Restores heap order by moving the entry at index j upward.
     * Performs a single upward swap if needed, then recurs on the parent index.
     * No loop is used.
     */
    private void upheap(int j) {
        if (j == 0) return;
        int p = parent(j);
        if (comp.compare(heap.get(j).key, heap.get(p).key) < 0) {
            swap(j, p);
            upheap(p);
        }
    }

    private void downheap(int j) {
        while (left(j) < heap.size()) {
            int small = left(j);
            if (right(j) < heap.size()
                    && comp.compare(heap.get(right(j)).key, heap.get(small).key) < 0)
                small = right(j);
            if (comp.compare(heap.get(small).key, heap.get(j).key) >= 0) break;
            swap(j, small);
            j = small;
        }
    }

    public int size()        { return heap.size();   }
    public boolean isEmpty() { return heap.isEmpty(); }

    public void insert(K key, V value) {
        heap.add(new Entry<K, V>(key, value));
        upheap(heap.size() - 1);
    }

    public Entry<K, V> min() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return min;
    }
}
