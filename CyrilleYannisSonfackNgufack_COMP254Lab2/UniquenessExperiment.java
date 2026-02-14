/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Experimental analysis: find the largest n such that unique1 (resp. unique2)
 * runs in one minute or less. Uses binary search on n.
 */
public class UniquenessExperiment {

  private static final long ONE_MINUTE_MS = 60000;

  /** Builds an array of length n with distinct values 0..n-1 (so no early return). */
  private static int[] distinctArray(int n) {
    int[] data = new int[n];
    for (int i = 0; i < n; i++)
      data[i] = i;
    return data;
  }

  /**
   * Binary search for largest n such that the given algorithm runs in <= ONE_MINUTE_MS.
   * @param useUnique1 true for unique1, false for unique2
   * @return largest n for which runtime <= 60 seconds
   */
  private static int findMaxN(boolean useUnique1) {
    int low, high;
    if (useUnique1) {
      low = 1_000;
      high = 500_000;
    } else {
      low = 100_000;
      high = 50_000_000;
    }

    int[] warm = distinctArray(Math.min(low + 1000, high));
    if (useUnique1) Uniqueness.unique1(warm);
    else Uniqueness.unique2(warm);

    int best = low;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int[] data = distinctArray(mid);
      long start = System.currentTimeMillis();
      if (useUnique1)
        Uniqueness.unique1(data);
      else
        Uniqueness.unique2(data);
      long elapsed = System.currentTimeMillis() - start;

      if (elapsed <= ONE_MINUTE_MS) {
        best = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return best;
  }

  /** Refines and prints the result: largest n with runtime <= 60s (with a quick check). */
  private static void report(boolean useUnique1) {
    String name = useUnique1 ? "unique1" : "unique2";
    System.out.println("Finding largest n for " + name + " (runtime <= 60 seconds)...");
    int n = findMaxN(useUnique1);
    // Confirm with one timed run
    int[] data = distinctArray(n);
    long start = System.currentTimeMillis();
    if (useUnique1) Uniqueness.unique1(data);
    else Uniqueness.unique2(data);
    long elapsed = System.currentTimeMillis() - start;
    System.out.println("  Largest n such that " + name + " runs in one minute or less: " + n);
    System.out.println("  (Measured runtime for n=" + n + ": " + elapsed + " ms)");
  }

  public static void main(String[] args) {
    System.out.println("Uniqueness experiment: binary search for max n (runtime <= 60 s)\n");
    report(false);  // unique2 first (faster)
    System.out.println();
    report(true);   // unique1
  }
}
