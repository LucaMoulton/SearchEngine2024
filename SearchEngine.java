/**
 *  Search Engine Application
 *  Author: Luca Moulton
 *  Student Number: C20434394
 *  Date: 19\04\2024
 *  Module Code: CMPU2016
 *  Description:
 * This application provides a graphical interface to search for text within files
 * located in a specified directory using various search operators.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * The SearchEngine class sets up the graphical user interface for the search engine application.
 * It handles user interactions and manages the display of search results and help information.
 */
public class SearchEngine extends JFrame {
    private JTextField searchTermField;  // TextField for entering search terms
    private JButton searchButton, folderButton;  // Buttons for initiating the search and selecting of the folder
    private JLabel folderLabel;  // Label that displays the selected folder
    private JFileChooser folderChooser;  // File chooser to select the folder that will be searched
    private String selectedFolderPath;  // Path to the selected folde r the user chose

    /**
     * this Constructor initializes the GUI components and opens the help window.
     */
    public SearchEngine() {
        createWindow();  // Sets up the main window
        initializeComponents();  // Initialize GUI interaction and visual components
        launchHelpWindow();  // Display the 2nd window with instructions and help
    }

    /**
     * Creates the main window with base settings for the user.
     */
    private void createWindow() {
        setTitle("Search Engine");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }

    /**
     * Initializes GUI components and adds them into the GUI main window.
     */
    private void initializeComponents() {
        folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        folderButton = new JButton("Select the Folder");
        folderButton.addActionListener(this::chooseFolder);

        folderLabel = new JLabel("No folder is selected");

        searchTermField = new JTextField(20);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this::performSearch);

        add(folderButton);
        add(folderLabel);
        add(new JLabel("Enter search terms:"));
        add(searchTermField);
        add(searchButton);
        pack();
    }

    /**
     * acts as the Event handler for 'Select Folder' button and Opens a dialog to choose a folder.
     */
    private void chooseFolder(ActionEvent e) {
        int returnVal = folderChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File folder = folderChooser.getSelectedFile();
            selectedFolderPath = folder.getAbsolutePath();
            folderLabel.setText("Folder: " + folder.getName());
        }
    }

    /**
     * The Event handler for 'Search' button. Performs a search and then displays the results.
     */
    private void performSearch(ActionEvent e) {
        if (selectedFolderPath == null) {
            JOptionPane.showMessageDialog(this, "Please select a folder first to search.");
            return;
        }
        String[] searchTerms = searchTermField.getText().split("\\s+");
        AbstractSearchStrategy searchStrategy = new BasicSearch();
        List<String[]> results = searchStrategy.search(selectedFolderPath, searchTerms);
        displayResults(results);
    }

    /**
     * Displays search results in a new window with a table, showing all files ranked by matching words.
     * Ranked from most instances to least
     */
    private void displayResults(List<String[]> results) {
        JTable resultTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"File Name", "Matches"}, 0);

        results.forEach(result -> model.addRow(new Object[]{result[0], result[1]}));
        resultTable.setModel(model);

        JFrame resultFrame = new JFrame("Search Results");
        resultFrame.add(new JScrollPane(resultTable));
        resultFrame.setSize(600, 400);
        resultFrame.setVisible(true);
    }

    /**
     * Launches a 2nd help window that provides instructions on how to use the search operators.
     * also includes the breakdown of operators names and usages
     */
    private void launchHelpWindow() {
        JTextArea helpText = new JTextArea(
                "Usage Instructions and Operator Explanations:\n\n" +
                        "- firstly Select a folder using the 'Select Folder' button.\n\n" +
                        "Enter search terms or if needed add an operator \n" +
                        "Search Operators:\n" +
                        "AND - Finds files containing all specified terms.\n" +
                        "   Example: 'apple AND orange' finds files containing both 'apple' and 'orange'.\n\n" +
                        "WITH - Finds files where the specified terms are in close proximity or related context.\n" +
                        "   Example: 'apple WITH orange' finds files where 'apple' and 'orange' appear closely together.\n\n" +
                        "OR - Finds files containing any of the specified terms.\n" +
                        "   Example: 'apple OR orange' finds files containing either 'apple', 'orange', or both.\n\n" +
                        "NOT - Excludes files containing the specified terms.\n" +
                        "   Example: 'apple NOT orange' finds files containing 'apple' but not 'orange'.\n\n" +
                        "DATE - Searches for files containing dates within a specified range.\n" +
                        "   Example: 'DATE:01/01/2020..31/12/2020' finds files with dates in the year 2020.\n\n" +
                        "- Click 'Search' to display results.\n\n" +
                        "Enter search terms using the operators above to refine your search."
        );
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(helpText);
        JFrame helpFrame = new JFrame("Help and Usage");
        helpFrame.add(scrollPane);
        helpFrame.setSize(500, 400);
        helpFrame.setVisible(true);
    }

    /**
     * Main method used to launch the search engines GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchEngine().setVisible(true));
    }
}
