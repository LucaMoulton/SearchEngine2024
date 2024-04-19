import java.io.File;
import java.util.ArrayList;

/**
 * BasicSearch class extends AbstractSearchStrategy to provide specific search functionalities.
 * It implements the abstract search method to execute simple text-based searches within files
 * being located in a specified directory. This class focuses on finding files containing the provided
 * search terms and counting their occurrences.
 */
public class BasicSearch extends AbstractSearchStrategy {

    /**
     * Searches the specified directory for files containing the provided search terms.
     * This method overrides the abstract search from AbstractSearchStrategy.
     *
     * @param folderPath Stores the path to the directory where files are to be searched.
     * @param searchTerms Holds the array of terms to search for within the files.
     * @return A list of String arrays, each containing the file name and the count of occurrences
     *         of the search terms within that file to be displayed when needed.
     */
    @Override
    public java.util.List<String[]> search(String folderPath, String... searchTerms) {
        File folder = new File(folderPath);  // Creates a object for the specified directory.
        File[] listOfFiles = folder.listFiles();  // List the files found in the directory.
        java.util.List<String[]> results = new ArrayList<>();  // Initializes the list to hold the results.

        // Iterates through each of the file in the directory.
        for (File file : listOfFiles) {
            if (file.isFile()) {  // Ensures the object is indeed a file.
                String content = readContent(file);  // Reads the content of the file.
                boolean include = false;  // Flag to determine if the file should be included in our results.
                int count = 0;  // Counter for the occurrences of the terms.

                // Iterate through each search term provided.
                for (String term : searchTerms) {
                    if (content.contains(term)) {  // Checks if the content contains the searched term.
                        include = true;  // Sets include flag to true if term is found.
                        count += countOccurrences(content, term);  // Adds the count of occurrences of this term to the total count.
                    }
                }

                // If the file contains any of the terms, adds it to the results list.
                if (include) {
                    results.add(new String[]{file.getName(), String.valueOf(count)});  // Adds file names and total count to results.
                }
            }
        }
        return results;  // Returns the results.
    }
}
