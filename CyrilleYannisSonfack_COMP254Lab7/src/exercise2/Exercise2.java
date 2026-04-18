package exercise2;

import java.util.Arrays;
import java.util.Queue;

/**
 * Test class for Exercise 2 — Bottom-up Merge Sort.
 */
public class Exercise2 {

    public static void main(String[] args) {

        System.out.println("=== Exercise 2: Bottom-Up Merge Sort ===\n");

        // Test 1 — integers
        Integer[] numbers = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original : " + Arrays.toString(numbers));
        Queue<Integer> sorted = BottomUpMergeSort.sort(numbers);
        System.out.println("Sorted   : " + sorted);

        // Test 2 — strings
        System.out.println();
        String[] words = {"banana", "apple", "mango", "cherry", "date"};
        System.out.println("Original : " + Arrays.toString(words));
        Queue<String> sortedWords = BottomUpMergeSort.sort(words);
        System.out.println("Sorted   : " + sortedWords);

        // Test 3 — single element (edge case)
        System.out.println();
        Integer[] single = {42};
        System.out.println("Original : " + Arrays.toString(single));
        Queue<Integer> sortedSingle = BottomUpMergeSort.sort(single);
        System.out.println("Sorted   : " + sortedSingle);

        // Test 4 — already sorted (edge case)
        System.out.println();
        Integer[] alreadySorted = {1, 2, 3, 4, 5};
        System.out.println("Original : " + Arrays.toString(alreadySorted));
        Queue<Integer> sortedAlready = BottomUpMergeSort.sort(alreadySorted);
        System.out.println("Sorted   : " + sortedAlready);
    }
}
