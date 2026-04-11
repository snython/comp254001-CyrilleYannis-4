package maps;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *    Data Structures and Algorithms in Java, Sixth Edition
 *
 * Exercise 1 modification by: Cyrille Yannis Sonfack (COMP254 Lab 6)
 *   Added two new constructors that accept a custom maxLoad.
 */

import java.util.ArrayList;

/**
 * Map implementation using hash table with separate chaining.
 *
 * Each array slot holds an UnsortedTableMap (a "bucket/chain").
 * Collisions are resolved by appending to the bucket list.
 *
 * === EXERCISE 1 CHANGE ===
 * Added constructors that forward a custom maxLoad to AbstractHashMap:
 *   ChainHashMap(int cap, int p, double maxLoad)
 *   ChainHashMap(int cap, double maxLoad)
 * Everything else is identical to the original textbook implementation.
 *
 * @author Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 * Modified by: Cyrille Yannis Sonfack
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
  private UnsortedTableMap<K,V>[] table;

  // ------------------------------------------------------------------
  // Constructors — original three + two new ones for Exercise 1
  // ------------------------------------------------------------------

  public ChainHashMap() { super(); }
  public ChainHashMap(int cap) { super(cap); }
  public ChainHashMap(int cap, int p) { super(cap, p); }

  // === EXERCISE 1: new constructors with custom load factor ===
  /** Custom capacity, prime, and max load factor. */
  public ChainHashMap(int cap, int p, double maxLoad) { super(cap, p, maxLoad); }
  /** Custom capacity, default prime 109345121, custom max load factor. */
  public ChainHashMap(int cap, double maxLoad) { super(cap, 109345121, maxLoad); }

  // ------------------------------------------------------------------
  // Table creation
  // ------------------------------------------------------------------

  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
  }

  // ------------------------------------------------------------------
  // Bucket operations (unchanged from original)
  // ------------------------------------------------------------------

  @Override
  protected V bucketGet(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    return bucket.get(k);
  }

  @Override
  protected V bucketPut(int h, K k, V v) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null)
      bucket = table[h] = new UnsortedTableMap<>();
    int oldSize = bucket.size();
    V answer = bucket.put(k,v);
    n += (bucket.size() - oldSize);
    return answer;
  }

  @Override
  protected V bucketRemove(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    int oldSize = bucket.size();
    V answer = bucket.remove(k);
    n -= (oldSize - bucket.size());
    return answer;
  }

  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h=0; h < capacity; h++)
      if (table[h] != null)
        for (Entry<K,V> entry : table[h].entrySet())
          buffer.add(entry);
    return buffer;
  }
}
