package maps;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *    Data Structures and Algorithms in Java, Sixth Edition
 *
 * Exercise 1 modification by: Cyrille Yannis Sonfack (COMP254 Lab 6)
 *   Added configurable maxLoad field and supporting constructor/setter.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract base class supporting Map implementations that use hash
 * tables with MAD compression.
 *
 * === EXERCISE 1 CHANGE ===
 * Original hardcoded load factor:
 *     if (n > capacity / 2)   // always 0.5
 *
 * New: a 'maxLoad' field that the user sets via constructor or setMaxLoad().
 *     if ((double) n / capacity > maxLoad)
 *
 * @author Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 * Modified by: Cyrille Yannis Sonfack
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
  protected int n = 0;                 // number of entries in the dictionary
  protected int capacity;              // length of the table
  private int prime;                   // prime factor
  private long scale, shift;           // the shift and scaling factors

  // === EXERCISE 1: new field ===
  protected double maxLoad;            // maximum load factor before resizing

  // ------------------------------------------------------------------
  // Constructors
  // ------------------------------------------------------------------

  /**
   * EXERCISE 1: new constructor — caller chooses the max load factor.
   * @param cap     initial table capacity
   * @param p       prime factor for MAD hashing
   * @param maxLoad resize when n/capacity exceeds this value (e.g. 0.75)
   */
  public AbstractHashMap(int cap, int p, double maxLoad) {
    prime = p;
    capacity = cap;
    this.maxLoad = maxLoad;
    Random rand = new Random();
    scale = rand.nextInt(prime-1) + 1;
    shift = rand.nextInt(prime);
    createTable();
  }

  /** Creates a hash table with the given capacity and prime factor (default load = 0.5). */
  public AbstractHashMap(int cap, int p) { this(cap, p, 0.5); }

  /** Creates a hash table with given capacity and prime factor 109345121. */
  public AbstractHashMap(int cap) { this(cap, 109345121); }

  /** Creates a hash table with capacity 17 and prime factor 109345121. */
  public AbstractHashMap() { this(17); }

  // ------------------------------------------------------------------
  // EXERCISE 1: setter / getter for maxLoad
  // ------------------------------------------------------------------

  /**
   * Sets the maximum load factor at any time (alternative to the constructor).
   * @param maxLoad new threshold, must be in (0, 1)
   */
  public void setMaxLoad(double maxLoad) {
    if (maxLoad <= 0 || maxLoad >= 1)
      throw new IllegalArgumentException("maxLoad must be between 0 and 1 (exclusive).");
    this.maxLoad = maxLoad;
  }

  public double getMaxLoad() { return maxLoad; }

  // ------------------------------------------------------------------
  // Public map methods
  // ------------------------------------------------------------------

  @Override
  public int size() { return n; }

  @Override
  public V get(K key) { return bucketGet(hashValue(key), key); }

  @Override
  public V remove(K key) { return bucketRemove(hashValue(key), key); }

  /**
   * EXERCISE 1 change: the load-factor check now uses maxLoad instead of 0.5.
   *   BEFORE: if (n > capacity / 2)
   *   AFTER:  if ((double) n / capacity > maxLoad)
   */
  @Override
  public V put(K key, V value) {
    V answer = bucketPut(hashValue(key), key, value);
    if ((double) n / capacity > maxLoad)   // EXERCISE 1: configurable threshold
      resize(2 * capacity - 1);
    return answer;
  }

  // ------------------------------------------------------------------
  // Private utilities
  // ------------------------------------------------------------------

  private int hashValue(K key) {
    return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
  }

  private void resize(int newCap) {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
    for (Entry<K,V> e : entrySet())
      buffer.add(e);
    capacity = newCap;
    createTable();
    n = 0;
    for (Entry<K,V> e : buffer)
      put(e.getKey(), e.getValue());
  }

  // ------------------------------------------------------------------
  // Abstract methods for subclasses
  // ------------------------------------------------------------------

  protected abstract void createTable();
  protected abstract V bucketGet(int h, K k);
  protected abstract V bucketPut(int h, K k, V v);
  protected abstract V bucketRemove(int h, K k);
}
