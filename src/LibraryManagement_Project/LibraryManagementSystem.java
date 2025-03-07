package LibraryManagement_Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Book{             // This class represent books in the Library
    String bookName;    // Stores the name of the book
    String bookAuthor;  // Stores the name of the author
    String userName;    // Stores the name of the person who issued the book if null book is not issued
    Date issuedOn;      // Stores date and time of issuing the book if null book is not issued
    // constructor
    Book(String bookName, String bookAuthor){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.userName = null;
        this.issuedOn = null;
        //Initializes a new Book object with the provided bookName and bookAuthor.
        //The userName and issuedOn fields are set to null because the book is not issued when it is added to the library.
    }
    void issueBook(String userName){  // this method issues the book to the user
        this.userName = userName;     // set the name of the user
        this.issuedOn = new Date();   // set the current time and date of issuing
    }
    void returnBook(){
        this.userName = null;     // set the name to null
        this.issuedOn = null;     // set the date and time to null because the book is no longer issued
    }
    @Override
    public String toString(){
        return "Book: "+bookName+", Author: "+bookAuthor+", Issued To: "+
                (userName != null ? userName : "N/A")+", Issued On: "+
                (issuedOn != null ? issuedOn : "N/A");
        // toString Method: Overrides the default toString method to provide a formatted string representation of the Book object.
        // Displays the book's name, author, and issue status (if issued, shows the borrower's name and issue date; otherwise, shows "N/A" and "N/A").
    }
}
class Library{
    ArrayList<Book> books;   // to store all the Book objects in the library
    Library(){               // Constructor: Initializes the books list as an empty ArrayList.
        books = new ArrayList<>();
    }
    public void viewBooks(){
        if(books.isEmpty()){            // Checks if the books list is empty. If empty, prints a message.
            System.out.println("Sorry! Currently Out of Stock:");
        }else{
            for(Book book : books){     // Otherwise, iterates through the books list and prints each book using its toString method.
                System.out.println(book);
            }
        }
    }
    public void addBooks(String bookName, String author){      //addBook Method: Adds a new book to the library.
        books.add(new Book(bookName,author));                  //Creates a new Book object with the provided bookName and bookAuthor.
    }                                                          //Adds the book to the books list.

    public void issueBook(String userName, String bookName){
        for(Book book : books){
            if(book.bookName.equals(bookName) && book.userName == null){  // Iterates through the books list to find a book with
                book.issueBook(userName);                                 // the matching bookName that is not already issued (userName == null).
                System.out.println("Book issued successfully");           // If found, calls the issueBook method on the Book object
                return;
            }
        }
        System.out.println("Sorry! This book is unavailable");
    }
    public void returnBook(String bookName){
        for(Book book : books){
            if(book.bookName.equals(bookName) && book.userName != null){   // Iterates through the books list to find a book with
                book.returnBook();                                         // the matching bookName that is currently issued (userName != null).
                System.out.println("Book returned successfully");          // If found, calls the returnBook method on the Book object
                return;
            }
        }
        System.out.println("Sorry! This book is not issued from SIKSHA Library:");
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();                     // Creates a Library object to manage books.
        // Add some books in stock, so that there are some books in library, before adding new ones.
        library.addBooks("Harry Potter","J.K. Rowling");
        library.addBooks("The Twilight Saga"," Stephenie Meyer");
        library.addBooks("The Lord of the Rings","J.R.R. Tolkien");
        library.addBooks("Gitanjali","Rabindranath Tagore");
        library.addBooks("A Suitable Boy","Vikram Seth");
        Scanner sc = new Scanner(System.in);
        int userInput;
        do{
            System.out.println("<-- Welcome to SIKSHA Library -->");
            System.out.println("1. View Books:");
            System.out.println("2. Add Books:");
            System.out.println("3. Issue Books:");
            System.out.println("4. Return Books:");
            System.out.println("5. Exit.");
            System.out.println("Enter Your Choice:");
            userInput = sc.nextInt();
            sc.nextLine();                                   // Consume new line
            switch(userInput){
                case 1:
                    library.viewBooks();                     // Calls the viewBooks method of the Library class.
                    break;
                case 2:
                    System.out.println("Enter the name of Book: ");
                    String book = sc.nextLine();
                    System.out.println("Enter the name of Author: ");
                    String author = sc.nextLine();
                    library.addBooks(book,author);           // Calls the addBook method of the Library class.
                    System.out.println("Book added successfully:");
                    break;
                case 3:
                    System.out.println("Enter Your Name:");
                    String userName = sc.nextLine();
                    System.out.println("Enter the name of Book: ");
                    String issueBook = sc.nextLine();        // Calls the issueBooks method of the Library class.
                    library.issueBook(userName, issueBook);
                    break;
                case 4:
                    System.out.println("Enter the name of Book: ");
                    String returnBook = sc.nextLine();
                    library.returnBook(returnBook);          // Calls the issueBooks method of the Library class.
                    break;
                case 5:
                    System.out.println("Exiting-----");
                    break;
                default:
                    System.out.println("Invalid Request: || Please try again...");
            }
        }while(userInput != 5);
        sc.close();                                          // sc.close(): Closes the Scanner object to free resources.
    }
}
