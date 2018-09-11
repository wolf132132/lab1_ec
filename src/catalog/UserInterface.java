package catalog;

import java.util.Scanner;

/** This class allows the user to interact with the system
 * using keyboard input.
 */
public class UserInterface {
    public final static String CHECKOUT  = "1";
    public final static String RETURN  = "2";
    public final static String PRINT_AVAILABLE  = "3";
    public final static String EXIT  = "4";

    /**
     * The method interacts with the user allowing them
     * to checkout a book, to return a book, to see a list
     * of available books, and to exit the program.
     * @param lc Library Catalog
     */
    public void mainLoop(LibraryCatalog lc) {
        // FILL IN CODE
        // Repeatedly prompt the user:
        // To check out a book, enter 1
        // To return a book, enter 2
        // To see a list of books currently available, enter 3
        // To exit, enter 4

        // If the user enters 1, ask for the title, and if the book is available,
        // check it out

        // If the user enters 2, ask for the title, and if the book is currently
        // checked out, return it.

        // If a user enters 3, display all the books that are currently
        // available (not checked out).

        // If a user enters 4, exit the program
        String userOption;
        String nameOfBook;
        Book book;
        Scanner userOptionInput = new Scanner(System.in);
        Scanner titleInput = new Scanner(System.in);
        while(true){
            System.out.println("To check out a book, please insert 1.");
            System.out.println("To return a book, please insert 2.");
            System.out.println("To see which book is available, please insert 3.");
            System.out.println("To exit from the program, insert 4.");
            userOption = userOptionInput.nextLine();
            try {
                if (userOption.equals(CHECKOUT)) {
                    System.out.println("Please insert the title of the book: ");
                    nameOfBook = titleInput.nextLine();
                    book = lc.findBook(nameOfBook);
                    if (!book.isCheckedOut()) {
                        lc.checkoutBook(nameOfBook);
                        System.out.println("The book has been checked out successfully");
                        System.out.println("");
                    } else {
                        System.out.println("The book is not available to be checked out.");
                        System.out.println("");
                    }
                    continue;
                } else if (userOption.equals(RETURN)) {
                    System.out.println("Please insert the title of the book: ");
                    nameOfBook = titleInput.nextLine();
                    book = lc.findBook(nameOfBook);
                    if (book.isCheckedOut()) {
                        lc.returnBook(nameOfBook);
                        System.out.println("The book has been returned");
                        System.out.println("");
                    } else {
                        System.out.println("Book was not returned successfully");
                        System.out.println("");
                    }
                    continue;
                } else if (userOption.equals(PRINT_AVAILABLE)) {
                    System.out.println("System is showing available books: ");
                    System.out.println(lc.getAvailableBooks());
                    continue;
                } else {
                    System.out.println("System terminated.");
                    System.out.println("");
                    break;
                }//end of if statement
            }catch (NullPointerException e){
                e.printStackTrace();
                System.out.println("Book is not found. Return to main menu");
                System.out.println("");
                continue;
            }//end of try-catch
        }//end of while loop
    } // mainLoop
}
