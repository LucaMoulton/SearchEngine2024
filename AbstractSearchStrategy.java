import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * AbstractSearchStrategy is an abstract class that provides functionalities for specific search strategies.
 * It encapsulates methods for reading files and counting occurrences within , which are common
 * across various search implementations.
 */
public abstract class AbstractSearchStrategy {

    /**
     * Abstract method that is implemented by subclasses to define specific behaviors.
     *
     * @param folderPath The path to the directory locations of the file.
     * @param searchTerms A variable to be used in the search process.
     * @return A list of string arrays where each array contains information about a file and its search results.
     */
    public abstract java.util.List<String[]> search(String folderPath, String... searchTerms);

    /**
     * Reads the content of given file and returns it as a single string to be used later.
     *
     * @param file The files from which to read content.
     * @return A string containing all the content of the files.
     * below the scanner is now used to scan the files this needs the util import to do so
     */
    protected String readContent(File file) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Counts the occurrences of specified terms within a given string.
     *
     * @param content The string with which to count occurrences.
     * @param term The terms to search for within the content.
     * @return The number of occurrences within .
     */
    protected int countOccurrences(String content, String term) {
        int count = 0;
        Pattern p = Pattern.compile("\\b" + Pattern.quote(term) + "\\b");
        Matcher m = p.matcher(content);
        while (m.find()) {
            count++;
        }
        return count;
    }
}
