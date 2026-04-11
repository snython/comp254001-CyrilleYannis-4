/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *    Data Structures and Algorithms in Java, Sixth Edition
 *
 * Exercise 2 modification by: Cyrille Yannis Sonfack (COMP254 Lab 6)
 *   Implemented the containKey(K key) method using findIndex.
 */
package maps;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a map using a sorted table.
 *
 * @author Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 * Modified by: Cyrille Yannis Sonfack
 */
public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {

  private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

  public SortedTableMap() { super(); }
  public SortedTableMap(Comparator<K> comp) { super(comp); }

  // ------------------------------------------------------------------
  // findIndex: recursive binary search
  // Returns the smallest index j in [low..high] where table[j].key >= key.
  // Returns high+1 if no such entry exists.
  // ------------------------------------------------------------------

  private int findIndex(K key, int low, int high) {
    if (high < low) return high + 1;
    int mid = (low + high) / 2;
    int comp = compare(key, table.get(mid));
    if (comp == 0)
      return mid;
    else if (comp < 0)
      return findIndex(key, low, mid - 1);
    else
      return findIndex(key, mid + 1, high);
  }

  private int findIndex(K key) { return findIndex(key, 0, table.size() - 1); }

  // ------------------------------------------------------------------
  // EXERCISE 2: containKey
  // ------------------------------------------------------------------

  /**
   * containKey(key) — returns true if the map contains an entry with the
   * given key, false otherwise.
   *
   * WHY THIS IS NEEDED:
   *   get(key) returns null both when the key is absent AND when the key
   *   exists but its value is null. containKey() gives an unambiguous answer.
   *
   * ALGORITHM (uses the existing findIndex as instructed):
   *   1. findIndex(key) -> index j (smallest index where table[j].key >= key)
   *   2. If j == size()                    -> key is past all entries -> absent
   *   3. If compare(key, table[j]) != 0   -> table[j].key != key    -> absent
   *   4. Otherwise                         -> exact match            -> present
   *
   * TIME COMPLEXITY: O(log n) — same as binary search.
   *
   * @param key  the key to search for
   * @return true if the key exists in the map, false otherwise
   */
  public boolean containKey(K key)
  {
    int j = findIndex(key);                            // Step 1
    if (j == size() || compare(key, table.get(j)) != 0)
      return false;                                    // Steps 2-3: absent
    return true;                                       // Step 4: found
  }

  // ------------------------------------------------------------------
  // Standard map operations (unchanged from original)
  // ------------------------------------------------------------------

  @Override
  public int size() { return table.size(); }

  @Override
  public V get(K key) throws IllegalArgumentException {
    checkKey(key);
    int j = findIndex(key);
    if (j == size() || compare(key, table.get(j)) != 0) return null;
    return table.get(j).getValue();
  }

  @Override
  public V put(K key, V value) throws IllegalArgumentException {
    checkKey(key);
    int j = findIndex(key);
    if (j < size() && compare(key, table.get(j)) == 0)
      return table.get(j).setValue(value);
    table.add(j, new MapEntry<K,V>(key,value));
    return null;
  }

  @Override
  public V remove(K key) throws IllegalArgumentException {
    checkKey(key);
    int j = findIndex(key);
    if (j == size() || compare(key, table.get(j)) != 0) return null;
    return table.remove(j).getValue();
  }

  private Entry<K,V> safeEntry(int j) {
    if (j < 0 || j >= table.size()) return null;
    return table.get(j);
  }

  @Override public Entry<K,V> firstEntry() { return safeEntry(0); }
  @Override public Entry<K,V> lastEntry()  { return safeEntry(table.size()-1); }

  @Override
  public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException {
    return safeEntry(findIndex(key));
  }

  @Override
  public Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
    int j = findIndex(key);
    if (j == size() || !key.equals(table.get(j).getKey())) j--;
    return safeEntry(j);
  }

  @Override
  public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
    return safeEntry(findIndex(key) - 1);
  }

  @Override
  public Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
    int j = findIndex(key);
    if (j < size() && key.equals(table.get(j).getKey())) j++;
    return safeEntry(j);
  }

  private Iterable<Entry<K,V>> snapshot(int startIndex, K stop) {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    int j = startIndex;
    while (j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
      buffer.add(table.get(j++));
    return buffer;
  }

  @Override public Iterable<Entry<K,V>> entrySet() { return snapshot(0, null); }

  @Override
  public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
    return snapshot(findIndex(fromKey), toKey);
  }
}
