import java.util.Scanner;

/**
 * Exercise 2: Recursive method to determine if a string is a palindrome.
 */
public class Ex2 {

    /**
     * Returns true if s is a palindrome (equal to its reverse), false otherwise.
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter strings to check for palindrome (empty line to quit):");
        String line;
        while (!(line = scanner.nextLine().trim()).isEmpty()) {
            boolean result = isPalindrome(line);
            System.out.println("\"" + line + "\" is " + (result ? "" : "not ") + "a palindrome.");
        }
        scanner.close();
    }
}
