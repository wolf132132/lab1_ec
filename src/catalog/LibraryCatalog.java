package catalog;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/** LibraryCatalog Class - represents a collection of books,
 *  where each book can be "checked out" and "returned".
 */
public class LibraryCatalog {

    /* ArrayList to store the books in the catalog. Remember to
    initialize this array in the constructor of the class. */
    private ArrayList<Book> books;

    /**
     * Constructor for LibraryCatalog
     */
    public LibraryCatalog() {
        // FILL IN CODE: initialize the ArrayList books here
        books = new ArrayList<>();
    }

    /** Adds a book to the ArrayList of books.
     *
     * @param book book to be added to the catalog
     */
    public void addBook(Book book) {
        // FILL IN CODE
        books.add(book);
    }

    /**
     * Searches for the book with the given title in the ArrayList
     * "books" and returns the book. Returns null if there is no book
     *  with this title.
     * @param title title of the book
     * @return reference to the Book with the given title
     */
    public Book findBook(String title) {
        // FILL IN CODE
        for (int i = 0; i < books.size(); i++){
            if ((books.get(i).getTitle()).equals(title)){
                return books.get(i);
            }
        }//end of while loop
        return null;
    }
    /**
     * Checks out the book with the given title from the library.
     *  First, searches for the book with the given title, and then
     * checks it out (by calling the corresponding method in class Book)
     * if it is not currently checked out. The method returns true if it
     * was able to check out the book, otherwise false.
     * @param title title of the book
     * @return true if was able to checkout a book, false otherwise
     */
    public boolean checkoutBook(String title) {
        // FILL IN CODE
        for (int i = 0; i < books.size(); i++) {
            if ((books.get(i).getTitle()).equals(title)) {
                if (!books.get(i).isCheckedOut()) {
                    books.get(i).checkoutBook();
                    return true;
                }//end of if statement.
            }//end of if statement
        }//End of for loop
        return false; // remember to change it
    }
    /**
     * Returns the book with the given title to the library. If the
     * book is in the ArrayList of books, has the matching title and is
     * currently checked out, the method is going to "return" it (by
     * calling the returnBook() method of class Book.
     * @param title title of the book to return
     * @return true if was able to return the book, false otherwise
     */
    public boolean returnBook(String title) {
        // FILL IN CODE
        for (int i = 0; i < books.size(); i++){
            if ((books.get(i).getTitle()).equals(title)){
                if (books.get(i).isCheckedOut()){
                    books.get(i).returnBook();
                    return true;
                }//end of is statement
            }//end of if statement
        }//end of for loop
        return false; // remember to change it
    }

    /**
     * Reads the file with the given filename line by line;
     * extracts book title, author, year and average rating from each line;
     * creates a Book object, and adds it to the ArrayList of books.
     *
     * @param filename name of the file that contains books info
     */
    public void addBooksFromFile(String filename) {

        // FILL IN CODE
        // You can use Scanner or BufferedReader to read from the file.
        // To split the line into individual words (title, author, year, averageRating),
        // use line.split("/").

        // To convert a String into an integer for the
        // variable "year", you can use Integer.parseInt(String s) function.
        // You are required to catch FileNotFoundException or IOException
        // in this method
        //readLine returns year as 1985.0 for example. Therefore, instantiate yearTemp to be converted into double
        //Two variables will be used to stored string from reading the line
        // and delimiter patterns respectively.
        //readLineArray: delimiter as comma
        //readLineArrayCopy: delimiter as quote
        FileReader file;
        BufferedReader input;
        String temp;
        String readLine;
        String readLineCopy;
        String[] readLineArray;
        String[] readLineArrayCopy;
        Book book;
        String title;
        String author;
        double yearTemp;
        int year = 0;
        int indexForYear = 0;
        int indexForLastQuoteOriginalTitle;
        int indexForLanguage = 0;
        double rating;
        try{
            file = new FileReader(filename);
            input = new BufferedReader(file);
            //First row, which are attributes, is not required. Skip this row.
            input.readLine();
            while((readLine = input.readLine())!=null){
                //Read from the rest of the file
                //Each line is separated by ","
                //Store the content from readLine to readLineCopy in order to perform second split if necessary.
                readLineCopy = readLine;
                readLineArray = readLine.split(",");
                readLineArrayCopy = readLineCopy.split("\"");
                //In summary, there will be four situations. Comma in author, Comma in title, comma in author and title and No presence of comma at all.
                //index of author should be 7 if being split by comma. If first character of readLineArrayCopy[7] is quote, it indicates that
                //in author info, there is a comma. If use delimiter comma, author info will be divided into index 7 and 8 and so on.
                //check if there is comma in author info
                if ((readLineArray[7].charAt(0)) == '"'){
                    //use quote as delimiter to extract author info
                    author = readLineArrayCopy[1];
                    //Iteration in order to get index of year column
                    //regardless how many commas in author column, there will be only two quotes.
                    //Iterate the readLineArray until encounter second quote and then the next index will be year.
                    for (int j = 8; j < readLineArray.length; j++){
                        temp = readLineArray[j];
                        if (temp.charAt(temp.length() - 1) == '"'){
                            indexForYear = j + 1;
                            break;
                        }//end of if statement
                    }//end of for loop
                    yearTemp = Double.parseDouble(readLineArrayCopy[indexForYear]);
                    year = (int)yearTemp;

                    //In certain cases, there are commas in original_title but not in title or the other way around.
                    //Original_title is not empty. Check if there is comma in original_title. If this is the case, a quote will be present as well. Iterate again until encounter second quote. Then the next index will be title
                    //There is comma in original title
                    if (readLineArray[indexForYear + 1].charAt(0) == '"'){
                        for (int k = indexForYear + 2; k < readLineArray.length; k++){
                            temp = readLineArray[k];
                            //Encounter the second quote
                            if (temp.charAt(temp.length() - 1) == '"'){
                                indexForLastQuoteOriginalTitle = k;
                                //check if there is quotes in title (next index)
                                if (readLineArray[k + 1].charAt(0) == '"'){
                                    title = readLineArrayCopy[5];
                                    break;
                                //No comma (no quote) in title
                                }else{
                                    title = readLineArray[indexForLastQuoteOriginalTitle + 1];
                                    break;
                                }//end of if statement
                            }//end of if statement
                        }//end of for loop

                        //Since there will be different number of commas in title and author columns, after we use delimiter as comma, indices after that will be changed.
                        //Create a for loop to iterate the array. As long as hitting eng, "", en-US and so on, it stops. Then the next index will be rating.
                        for (int i = 0; i < readLineArray.length; i++){
                            if (readLineArray[i].equals("ara") || readLineArray[i].equals("dan") || readLineArray[i].equals("en") || readLineArray[i].equals("en-CA") ||
                            readLineArray[i].equals("en-GB") || readLineArray[i].equals("en-US") || readLineArray[i].equals("eng") || readLineArray[i].equals("fil")
                            || readLineArray[i].equals("fre") || readLineArray[i].equals("ger") || readLineArray[i].equals("ind") || readLineArray[i].equals("ita") ||
                                    readLineArray[i].equals("jpn") || readLineArray[i].equals("mul") || readLineArray[i].equals("nl") || readLineArray[i].equals("nor")
                            || readLineArray[i].equals("per") || readLineArray[i].equals("pol") || readLineArray[i].equals("por") || readLineArray[i].equals("rum")
                            || readLineArray[i].equals("rus") || readLineArray[i].equals("spa") || readLineArray[i].equals("swe") || readLineArray[i].equals("tur")
                            || readLineArray[i].equals("vie") || readLineArray[i].equals("")){
                                rating = Double.parseDouble(readLineArray[i + 1]);
                                break;
                            }//end of if statement
                        }//end of for loop

                    //Certain original_title columns are empty
                    } else if (readLineArray[indexForYear + 1] == ""){
                        //check if there is quote in title
                        if (readLineArray[indexForYear + 2].charAt(0) == '"'){
                            title = readLineArrayCopy[3];
                            //extract rating by iteration.
                            for (int i = 0; i < readLineArray.length; i++) {
                                if (readLineArray[i].equals("ara") || readLineArray[i].equals("dan") || readLineArray[i].equals("en") || readLineArray[i].equals("en-CA") ||
                                        readLineArray[i].equals("en-GB") || readLineArray[i].equals("en-US") || readLineArray[i].equals("eng") || readLineArray[i].equals("fil")
                                        || readLineArray[i].equals("fre") || readLineArray[i].equals("ger") || readLineArray[i].equals("ind") || readLineArray[i].equals("ita") ||
                                        readLineArray[i].equals("jpn") || readLineArray[i].equals("mul") || readLineArray[i].equals("nl") || readLineArray[i].equals("nor")
                                        || readLineArray[i].equals("per") || readLineArray[i].equals("pol") || readLineArray[i].equals("por") || readLineArray[i].equals("rum")
                                        || readLineArray[i].equals("rus") || readLineArray[i].equals("spa") || readLineArray[i].equals("swe") || readLineArray[i].equals("tur")
                                        || readLineArray[i].equals("vie") || readLineArray[i].equals("")) {
                                    rating = Double.parseDouble(readLineArray[i + 1]);
                                    break;
                                }//end of if statement
                            }//end of for loop

                        //there is no quote in title
                        //When original_title columns are empty, indexForYear + 2 will be index for title
                        }else{
                            title = readLineArray[indexForYear + 2];
                            for (int i = 0; i < readLineArray.length; i++) {
                                if (readLineArray[i].equals("ara") || readLineArray[i].equals("dan") || readLineArray[i].equals("en") || readLineArray[i].equals("en-CA") ||
                                        readLineArray[i].equals("en-GB") || readLineArray[i].equals("en-US") || readLineArray[i].equals("eng") || readLineArray[i].equals("fil")
                                        || readLineArray[i].equals("fre") || readLineArray[i].equals("ger") || readLineArray[i].equals("ind") || readLineArray[i].equals("ita") ||
                                        readLineArray[i].equals("jpn") || readLineArray[i].equals("mul") || readLineArray[i].equals("nl") || readLineArray[i].equals("nor")
                                        || readLineArray[i].equals("per") || readLineArray[i].equals("pol") || readLineArray[i].equals("por") || readLineArray[i].equals("rum")
                                        || readLineArray[i].equals("rus") || readLineArray[i].equals("spa") || readLineArray[i].equals("swe") || readLineArray[i].equals("tur")
                                        || readLineArray[i].equals("vie") || readLineArray[i].equals("")) {
                                    rating = Double.parseDouble(readLineArray[i + 1]);
                                    break;
                                }//end of if statement
                            }//end of for loop
                        }//end of if-else statement

                    //No comma in original_title
                    } else {
                        for (int h = indexForYear + 1; h < readLineArray.length; h++){
                            //There is comma in tile
                            if (readLineArray[h].charAt(0) == '"'){
                                title = readLineArrayCopy[3];
                                for (int i = 0; i < readLineArray.length; i++) {
                                    if (readLineArray[i].equals("ara") || readLineArray[i].equals("dan") || readLineArray[i].equals("en") || readLineArray[i].equals("en-CA") ||
                                            readLineArray[i].equals("en-GB") || readLineArray[i].equals("en-US") || readLineArray[i].equals("eng") || readLineArray[i].equals("fil")
                                            || readLineArray[i].equals("fre") || readLineArray[i].equals("ger") || readLineArray[i].equals("ind") || readLineArray[i].equals("ita") ||
                                            readLineArray[i].equals("jpn") || readLineArray[i].equals("mul") || readLineArray[i].equals("nl") || readLineArray[i].equals("nor")
                                            || readLineArray[i].equals("per") || readLineArray[i].equals("pol") || readLineArray[i].equals("por") || readLineArray[i].equals("rum")
                                            || readLineArray[i].equals("rus") || readLineArray[i].equals("spa") || readLineArray[i].equals("swe") || readLineArray[i].equals("tur")
                                            || readLineArray[i].equals("vie") || readLineArray[i].equals("")) {
                                        rating = Double.parseDouble(readLineArray[i + 1]);
                                        break;
                                    }//end of if statement
                                }//end of for loop
                            //no comma in title
                            }else{
                                title = readLineArray[indexForYear + 2];
                                rating = Double.parseDouble(readLineArray[indexForYear + 4]);
                            }//end of if-else statement
                        }//end of for loop
                    }//end of if-else if-else statement

                //No comma in author
                }else{
                    author = readLineArray[7];
                    yearTemp = Double.parseDouble(readLineArray[8]);

                    //There is comma (quote) in original title
                    if (readLineArray[9].charAt(0) == '"'){
                        //check if there is comma in title by checking quote
                        for (int p = 10; p < readLineArray.length; p++){
                            temp = readLineArray[p];
                            //there is comma in title
                            if (readLineArray[p].charAt(temp.length() - 1) == '"'){
                                title = readLineArrayCopy[3];
                                for (int i = 0; i < readLineArray.length; i++) {
                                    if (readLineArray[i].equals("ara") || readLineArray[i].equals("dan") || readLineArray[i].equals("en") || readLineArray[i].equals("en-CA") ||
                                            readLineArray[i].equals("en-GB") || readLineArray[i].equals("en-US") || readLineArray[i].equals("eng") || readLineArray[i].equals("fil")
                                            || readLineArray[i].equals("fre") || readLineArray[i].equals("ger") || readLineArray[i].equals("ind") || readLineArray[i].equals("ita") ||
                                            readLineArray[i].equals("jpn") || readLineArray[i].equals("mul") || readLineArray[i].equals("nl") || readLineArray[i].equals("nor")
                                            || readLineArray[i].equals("per") || readLineArray[i].equals("pol") || readLineArray[i].equals("por") || readLineArray[i].equals("rum")
                                            || readLineArray[i].equals("rus") || readLineArray[i].equals("spa") || readLineArray[i].equals("swe") || readLineArray[i].equals("tur")
                                            || readLineArray[i].equals("vie") || readLineArray[i].equals("")) {
                                        rating = Double.parseDouble(readLineArray[i + 1]);
                                        break;
                                    }//end of if statement
                                }//end of for loop
                            //No comma in title
                            } else {
                                for (int o = 10; o < readLineArray.length; o++){
                                    temp = readLineArray[o];
                                    indexForLastQuoteOriginalTitle = o;
                                    //Encounter the second quote before title
                                    if (temp.charAt(temp.length() - 1) == '"'){
                                        title = readLineArray[indexForLastQuoteOriginalTitle + 1];
                                        rating = Double.parseDouble(readLineArray[indexForLastQuoteOriginalTitle + 3]);
                                        break;
                                    }
                                }//end of for loop
                            }//end of if-else statement
                        }//end of for loop

                    //original title is empty
                    } else if (readLineArray[9].equals("")){
                        for (int y = 10)
                    //No comma (quote) in original title
                    } else {
                        title = readLineArray[10];


                    }//end of if - else if - else statement

                }//end of if-else statement

            }//end of while loop
            book = new Book(title, author, year, rating);
            books.add(book);
        } catch (java.io.FileNotFoundException e){
            e.printStackTrace();
            System.out.println("file not found. Return to main menu");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found. Return to main menu");
        }//end of try catch
    }


    /**
     * Returns a String containing books that are available for checkout
     * @return string with books that are not checked out
     */
    public String getAvailableBooks() {
        // FILL IN CODE
        String str = "";
        for (int i = 0; i < books.size(); i++){
            if (!books.get(i).isCheckedOut()) {
                str = str + books.get(i).toString() + System.lineSeparator();
            }//end of if statement
        }//end of for loop
        return str; // remember to change it
    }

    /**
     * Returns a string that contains information about all books
     * from the catalog; one book per line in the same format as in
     * toString method in class Book.
     * Important: instead of adding newline character using
        "\n", add System.lineSeparator() - it will return the system-dependent line separator

     * @return string representation of the library catalog
     */
    public String toString() {
        // FILL IN CODE
        String str = "";
        for (int i = 0; i < books.size(); i++){
            str = str + books.get(i).toString() + System.lineSeparator();
        }//end of for loop
        return str; // remember to change it
    }

}
