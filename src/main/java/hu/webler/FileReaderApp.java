package hu.webler;

import hu.webler.util.FileHandler;

import java.util.*;

public class FileReaderApp {

    private static hu.webler.util.FileHandler FileHandler;

    public static void main(String[] args) {

        final String DELIMITER = ";";
        final String CATEGORY_FILE_PATH = "src/main/resources/category.txt";
        final String BOOK_FILE_PATH = "src/main/resources/book.txt";

        List<String[]> categories = FileHandler.readTxtFile(CATEGORY_FILE_PATH, DELIMITER);
        List<String[]> books = FileHandler.readTxtFile(BOOK_FILE_PATH, DELIMITER);

        // FileHandler.printStringArrays(categories);
        // FileHandler.printStringArrays(books);

        Map<Integer, List<String>> bookCategoryMap = new HashMap<>();

        // Iterate over each book
        for (String[] book : books) {
            // Assuming the first element of each book array is the category number, remove it
            String[] bookDetails = new String[book.length - 1];
            System.arraycopy(book, 1, bookDetails, 0, bookDetails.length);
            String bookDetailsString = String.join(DELIMITER, bookDetails);

            // Extract the category number from the book array
            int category = Integer.parseInt(book[0]);

            // Add the book to the corresponding category in the map
            //bookCategoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(bookDetailsString);

            // Get the list of books for the current category from the map
            List<String> categoryBooks = bookCategoryMap.get(category);

            // If the list doesn't exist for the current category, create a new one and put it into the map
            if (categoryBooks == null) {
                categoryBooks = new ArrayList<>();
                bookCategoryMap.put(category, categoryBooks);
            }

            // Add the book details to the list for the current category
            categoryBooks.add(bookDetailsString);
        }

        // Print the books grouped by category
        for (Map.Entry<Integer, List<String>> entry : bookCategoryMap.entrySet()) {
            System.out.println("Category " + entry.getKey() + ":");
            for (String book : entry.getValue()) {
                System.out.println(book);
            }
            System.out.println();
        }


        System.out.println("---------");

        // Print the books grouped by category name
        for (String[] category : categories) {
            int categoryNumber = Integer.parseInt(category[0]);
            String categoryName = category[1];
            List<String> booksInCategory = bookCategoryMap.get(categoryNumber);

            if (booksInCategory != null) {
                System.out.println(categoryName + ":");
                for (String book : booksInCategory) {
                    System.out.println(book);
                }
                System.out.println();
            }
        }
    }
}