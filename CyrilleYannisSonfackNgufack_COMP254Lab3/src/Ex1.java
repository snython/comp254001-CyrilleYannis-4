/**
 * Exercise 1: Recursive product of two positive integers using only addition and subtraction.
 */
public class Ex1 {

    /**
     * Computes m * n recursively using only addition and subtraction.
     * product(m, n) = m + m + ... + m (n times).
     */
    public static int product(int m, int n) {
        if (n <= 0) {
            return 0;
        }
        return m + product(m, n - 1);
    }

    public static void main(String[] args) {
        System.out.println("product(3, 4) = " + product(3, 4));   // 12
        System.out.println("product(5, 0) = " + product(5, 0));   // 0
        System.out.println("product(7, 2) = " + product(7, 2));   // 14
    }
}
