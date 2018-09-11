package catalog;

/** Class Book represents a book in the library catalog */
public class Book {

    // FILL IN CODE: declare private instance variables:
    // title, author, year, averageRating, isCheckedOut
    private String author;
    private String title;
    private int year;
    private double averageRating;
    private boolean isCheckedOut = false;


    /**
     * Constructor of class Book
     * @param title title of the book
     * @param author author of the book
     * @param year year when the book was first published
     * @param avgRating average rating on goodreads
     */
    public Book(String title, String author, int year,
                double avgRating){
        // FILL IN CODE
        this.title = title;
        this.author = author;
        this.year = year;
        averageRating = avgRating;
    }

    /**
     * Return true if the book is currently checked out
     * and false otherwise.
     * @return value of boolean variable isCheckedOut
     */
    public boolean isCheckedOut() {
        // FILL IN CODE
        if (isCheckedOut){
            return true;
        } else {
            return false; // remember to change it!
        }//end of if-else statement
    }

    /**
     * Return the title of the book
     * @return title
     */
    public String getTitle() {
        // FILL IN CODE
        return title; // remember to change it
    }

    /** Return average rating of the book from goodreads
     *
     * @return average rating
     */
    public double getAverageRating() {
        // FILL IN CODE
        return averageRating; // remember to change it
    }

    /**
     * Check out the book if it is available,
     * by updating isCheckedOut variable.
     * If the book is not available (checked out), return false
     * @return true if was able to checkout the book, and false otherwise
     */
    public boolean checkoutBook() {
        // FILL IN CODE
        if (!isCheckedOut){
            isCheckedOut = true;
            return true;
        } else {
            return false;
        }//end of if statement
        // remember to change it
    }//end of method

    /**
     * Return the book if it is currently checked out, return true.
     * If the book is not checked out (can not return), return false
     * @return true if was able to return a book, false otherwise
     */
    public boolean returnBook() {
        // FILL IN CODE
        if(isCheckedOut){
            isCheckedOut = false;
            return true; // remember to change it
        } else {
            return false;
        }//end of if statement
    }//end of method

    /** Return a string with the information about the book
     * in the following format:
     * (title, author, year, averageRating)
     * @return string representation of the book
     */
    public String toString() {
        // FILL IN CODE
        String str = "(" + title + ", " + author + ", " + String.valueOf(year)+ ", " + String.valueOf(averageRating) + ")";
        return str; // // remember to change it
    }
}
