package maps;

/*
 * Based on:
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * ProbeHashMap was not included in the course package.
 * Created by: Cyrille Yannis Sonfack (COMP254 Lab 6, Exercise 1)
 */

import java.util.ArrayList;

/**
 * Map implementation using open addressing with linear probing.
 *
 * How it works:
 *   The table is a flat array; each slot holds at most one entry.
 *   On a collision, probe forward (+1, wrap around) until a free slot is found.
 *
 *   Example — keys A, B, C all hash to slot 2 (capacity = 7):
 *     slot: 0   1   2    3    4   5   6
 *               -  (A)  (B)  (C)  -   -
 *
 *   DEFUNCT sentinel: a removed slot cannot be set to null because that would
 *   cut the probe chain. Instead it is marked DEFUNCT — probing skips it but
 *   does not stop.
 *
 * EXERCISE 1: includes constructors that accept a custom maxLoad,
 * forwarded to AbstractHashMap exactly like ChainHashMap does.
 *
 * @author Cyrille Yannis Sonfack
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {

  private MapEntry<K,V>[] table;

  /**
   * Sentinel: marks a slot as "deleted".
   *   null    -> never used; probe can stop here
   *   DEFUNCT -> was deleted; probe must continue past here
   */
  private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

  // ------------------------------------------------------------------
  // Constructors — mirrors ChainHashMap's set of constructors
  // ------------------------------------------------------------------

  public ProbeHashMap() { super(); }
  public ProbeHashMap(int cap) { super(cap); }
  public ProbeHashMap(int cap, int p) { super(cap, p); }

  // === EXERCISE 1: new constructors with custom load factor ===
  /** Custom capacity, prime, and max load factor. */
  public ProbeHashMap(int cap, int p, double maxLoad) { super(cap, p, maxLoad); }
  /** Custom capacity, default prime 109345121, custom max load factor. */
  public ProbeHashMap(int cap, double maxLoad) { super(cap, 109345121, maxLoad); }

  // ------------------------------------------------------------------
  // Table creation
  // ------------------------------------------------------------------

  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (MapEntry<K,V>[]) new MapEntry[capacity];
  }

  // ------------------------------------------------------------------
  // isAvailable: is slot j free for writing?
  // ------------------------------------------------------------------

  private boolean isAvailable(int j) {
    return (table[j] == null || table[j] == DEFUNCT);
  }

  // ------------------------------------------------------------------
  // findSlot: linear probe search
  //
  // Returns:
  //   >= 0  -> index where key k was FOUND
  //   < 0   -> key absent; -(result + 1) is the first available slot
  // ------------------------------------------------------------------

  private int findSlot(int h, K k) {
    int avail = -1;
    int j = h;
    do {
      if (isAvailable(j)) {
        if (avail == -1) avail = j;       // first reusable slot
        if (table[j] == null) break;      // truly empty -> key definitely absent
      } else if (table[j].getKey().equals(k)) {
        return j;                         // key found
      }
      j = (j + 1) % capacity;
    } while (j != h);
    return -(avail + 1);
  }

  // ------------------------------------------------------------------
  // Bucket operations
  // ------------------------------------------------------------------

  @Override
  protected V bucketGet(int h, K k) {
    int j = findSlot(h, k);
    if (j < 0) return null;
    return table[j].getValue();
  }

  @Override
  protected V bucketPut(int h, K k, V v) {
    int j = findSlot(h, k);
    if (j >= 0) {                         // key exists: update
      V old = table[j].getValue();
      table[j].setValue(v);
      return old;
    }
    table[-(j+1)] = new MapEntry<>(k, v); // insert at first available slot
    n++;
    return null;
  }

  @Override
  protected V bucketRemove(int h, K k) {
    int j = findSlot(h, k);
    if (j < 0) return null;
    V old = table[j].getValue();
    table[j] = DEFUNCT;                   // mark deleted, do NOT use null
    n--;
    return old;
  }

  // ------------------------------------------------------------------
  // entrySet: skip nulls and DEFUNCT slots
  // ------------------------------------------------------------------

  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h = 0; h < capacity; h++)
      if (!isAvailable(h))
        buffer.add(table[h]);
    return buffer;
  }
}
