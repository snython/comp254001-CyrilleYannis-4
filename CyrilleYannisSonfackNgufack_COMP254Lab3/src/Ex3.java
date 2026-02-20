import java.io.File;
import java.util.Scanner;

/**
 * Exercise 3: Recursive find(path, filename) that reports all file system entries
 * rooted at the given path having the given file name.
 */
public class Ex3 {

    /**
     * Reports all entries under path (file or directory) whose name equals filename.
     * Prints the absolute path of each match.
     */
    public static void find(File path, String filename) {
        if (path == null || filename == null) {
            return;
        }
        if (!path.exists()) {
            return;
        }
        if (path.isFile()) {
            if (path.getName().equals(filename)) {
                System.out.println(path.getAbsolutePath());
            }
            return;
        }
        // path is a directory: report if this directory's name matches, then recurse
        if (path.getName().equals(filename)) {
            System.out.println(path.getAbsolutePath());
        }
        File[] children = path.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            find(child, filename);
        }
    }

    /**
     * Overload: find(String path, String filename) for convenience.
     */
    public static void find(String path, String filename) {
        find(new File(path), filename);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to search: ");
        String path = scanner.nextLine().trim();
        System.out.print("Enter filename to find: ");
        String filename = scanner.nextLine().trim();
        scanner.close();
        System.out.println("Entries with name \"" + filename + "\":");
        find(path, filename);
    }
}
