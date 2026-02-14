/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
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
 * Experimental analysis of prefixAverage1 (O(n²)) and prefixAverage2 (O(n)).
 * Uses representative values of n, doubling each trial.
 */
public class PrefixAverageExperiment {

  private static final int RUNS_PER_N = 5;  // average over several runs to smooth noise

  /** Builds a double array of length n with random values in [0, 1). */
  private static double[] randomArray(int n) {
    double[] x = new double[n];
    for (int i = 0; i < n; i++)
      x[i] = Math.random();
    return x;
  }

  /** Returns average elapsed time in milliseconds over RUNS_PER_N runs of prefixAverage1(x). */
  private static long timePrefixAverage1(double[] x) {
    long total = 0;
    for (int r = 0; r < RUNS_PER_N; r++) {
      long start = System.nanoTime();
      PrefixAverage.prefixAverage1(x);
      long end = System.nanoTime();
      total += (end - start) / 1_000_000;
    }
    return total / RUNS_PER_N;
  }

  /** Returns average elapsed time in milliseconds over RUNS_PER_N runs of prefixAverage2(x). */
  private static long timePrefixAverage2(double[] x) {
    long total = 0;
    for (int r = 0; r < RUNS_PER_N; r++) {
      long start = System.nanoTime();
      PrefixAverage.prefixAverage2(x);
      long end = System.nanoTime();
      total += (end - start) / 1_000_000;
    }
    return total / RUNS_PER_N;
  }

  public static void main(String[] args) {
    int n = 10000;   // starting value
    int trials = 10;
    try {
      if (args.length > 0) trials = Integer.parseInt(args[0]);
      if (args.length > 1) n = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) { }

    System.out.println("Prefix average experimental analysis (n doubled each trial)");
    System.out.println("n\t\tprefixAverage1 (ms)\tprefixAverage2 (ms)");
    System.out.println("--------------------------------------------------------------");

    for (int t = 0; t < trials; t++) {
      double[] x = randomArray(n);
      long t1 = timePrefixAverage1(x);
      long t2 = timePrefixAverage2(x);
      System.out.printf("%d\t\t%d\t\t\t%d%n", n, t1, t2);
      n *= 2;
    }

    System.out.println("\nConclusion: prefixAverage1 grows roughly like n² (O(n²)); prefixAverage2 like n (O(n)); prefixAverage2 is much faster for large n.");
  }
}
