/**
 * The SearchStrategy interface defines a contract for search strategies within the system.
 * This acts as case sensitivity withiin our system to make sure things are running smoothly
 * Classes implementing this interface must define the search method, which outlines how searches
 * are conducted.
 */

public interface SearchStrategy {

    /**
     * Method to execute a search within a directory for files containing specified search terms.
     *
     * @param folderPath The directory path where the search is to be performed.
     * @param searchTerms The search terms used to find matches within files.
     * @return A list of string arrays, each representing a file and details about the search hits.
     */
    java.util.List<String[]> search(String folderPath, String... searchTerms);
}
