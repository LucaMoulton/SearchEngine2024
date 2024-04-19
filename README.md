## SearchEngine2024

Project Overview

This Java Search Engine is designed to allow users to search for text within documents stored in a specified directory. It supports various search operations and displays the results in a user-friendly graphical interface.

list of my classes:

- SearchEngine.java
Manages the user interface, including input fields for search terms, buttons for folder selection and searches, and displaying results.
  
- BasicSearch.java
Implements the search functionality by extending the `AbstractSearchStrategy` class. handles the scanning of files and counting the occurrences of search terms within these files.
  
- AbstractSearchStrategy.java
  Provides foundational methods used by search classes, such as reading file contents and counting term occurrences within text.
  
- SearchStrategy.java (Interface)
  Defines a common interface for all search strategies, ensuring consistency  among various search implementations.

## Core Functionalities
- File Searching: Allows users to search for specific terms within the text files of a selected directory.
- Graphical User Interface: Provides a simple and intuitive interface for users to interact with the application, including selecting directories and initiating searches.
- Result Display: Shows search results in a tabular format, listing files that match the search criteria along with the count of matching words.

## Advanced Functionalities

- Multiple Search Operators: Supports various search operators like AND, OR, NOT, and DATE, allowing for complex search queries.
- Help Window: Includes a help window that explains the usage of different search operators and general application functionalities.
- Spell Correction: Implements a basic spell correction feature that corrects single-letter misspellings in the search terms.

## Future Enhancements
If given more time to develop this project, I would consider the following features as something that the users will find useful:

- Advanced Spell Check**: Upgrade the spell correction to handle more complex misspellings and maybe even have a pop up to correct words.
- Search Context Preview: Show snippets of text around search hits in the results to provide context without needing to open and read through the entire document.
- User Customization Options: Allow users to customize proximity range for the WITH operator and sensitivity settings for spell correction.

## Conclusion
This search engine provides a robust foundation for text searching within documents, with room for expansion and customization based on user needs or project requirements. The application is designed to be simple yet powerful, offering users the ability to efficiently manage and search through large volumes of text data and find what they are looking for.
