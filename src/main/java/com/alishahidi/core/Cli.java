package com.alishahidi.core;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Cli {
  BookList bookList;
  Scanner scanner;

  public void displayMenu() {
    System.out.println();
    System.out.println("Menu:");
    System.out.println("1. Add a book");
    System.out.println("2. Remove a book");
    System.out.println("3. Search by rating");
    System.out.println("4. Search by name");
    System.out.println("5. Number of books");
    System.out.println("6. Print book details");
    System.out.println("7. Print all books");
    System.out.println("8. Exit");

    System.out.print("Enter your choice: ");
  }

  public int getUserChoice() {
    int n = scanner.nextInt();
    scanner.nextLine();
    return n;
  }

  public void clearTerminal() {
    // Check if the system supports ANSI escape codes
    if (System.console() == null) {
      // ANSI escape codes are not supported, so print a newline
      System.out.println();
    } else {
      // Use ANSI escape codes to clear the terminal
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }

  public void printCopyright() {
    clearTerminal();
    System.out.println("***********************************************");
    System.out.println("*                                             *");
    System.out.println("*          Book Manager CLI Application       *");
    System.out.println("*                                             *");
    System.out.println("***********************************************");
  }

  public void addSamples() {
    List<Book> sampleBooks = List.of(
        Book.builder().name("Book 1").author("Author 1").rating(8).build(),
        Book.builder().name("Book 1").author("Author 1").rating(1).build(),
        Book.builder().name("Book 1").author("Author 1").rating(10).build(),
        Book.builder().name("Book 1").author("Author 1").rating(6).build(),
        Book.builder().name("Book 2").author("Author 2").rating(7).build(),
        Book.builder().name("Book 6").author("Author 2").rating(7).build(),
        Book.builder().name("Book 7").author("Author 2").rating(7).build(),
        Book.builder().name("Book 3").author("Author 3").rating(9).build());

    sampleBooks.forEach(book -> bookList.addBook(Node.builder().book(book).build()));
  }

  public void addBook() {
    System.out.print("Enter book name: ");
    String name = scanner.nextLine();
    System.out.print("Enter author name: ");
    String author = scanner.nextLine();
    System.out.print("Enter year of publication: ");
    int year = scanner.nextInt();
    System.out.print("Enter rating: ");
    int rating = scanner.nextInt();
    System.out.print("Enter shelf number: ");
    int shelf = scanner.nextInt();

    Book newBook = Book.builder()
        .name(name)
        .author(author)
        .year(year)
        .rating(rating)
        .bookshelf(shelf)
        .build();
    Node newBookNode = Node.builder().book(newBook).build();
    bookList.addBook(newBookNode);
  }

  public void removeBook() {
    System.out.print("Enter book name to remove: ");
    String bookToRemove = scanner.nextLine();
    bookList.removeBook(bookToRemove);
  }

  public List<Node> searchAllByRating() {
    System.out.print("Enter rating to search: ");
    int ratingToSearch = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character
    List<Node> foundByRating = bookList.searchAllByRating(ratingToSearch);

    if (!foundByRating.isEmpty()) {
      System.out.println("Books found:");
      for (Node node : foundByRating) {
        System.out.println(node.getBook());
      }
    } else {
      System.out.println("No books found.");
    }

    return foundByRating;
  }

  public List<Node> searchAllByName() {
    System.out.print("Enter book name to search: ");
    String nameToSearch = scanner.nextLine();
    List<Node> foundByName = bookList.searchAllByName(nameToSearch);

    if (!foundByName.isEmpty()) {
      System.out.println("Books found:");
      for (Node node : foundByName) {
        System.out.println(node.getBook());
      }
    } else {
      System.out.println("No books found.");
    }

    return foundByName;
  }

  public void displayNumberOfBooks() {
    System.out.println("Number of books: " + bookList.getNumberOfBooks());
  }

  public void printBookDetails() {
    System.out.print("Enter book name to print details: ");
    String bookNameToPrint = scanner.nextLine();
    bookList.printBookDetails(bookNameToPrint);
  }

  public void printAllBooks() {
    bookList.printAllBooks();
  }

  public void exit() {
    System.out.println("Exiting program. Goodbye!");
    System.exit(0);
  }
}
