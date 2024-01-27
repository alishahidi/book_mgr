package com.alishahidi.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookList {
  Node nil;

  public BookList() {
    nil = Node.builder().book(Book.builder().build()).build();
    nil.setPrev(nil);
    nil.setNext(nil);
  }

  public void addBook(Node newBookNode) {
    Node current = nil.getNext();
    while (current != nil &&
        (current.getBook().getName().compareTo(newBookNode.getBook().getName()) < 0 ||
            (current.getBook().getName().equals(newBookNode.getBook().getName()) &&
                current.getBook().getRating() > newBookNode.getBook().getRating()))) {
      current = current.getNext();
    }
    newBookNode.setNext(current);
    newBookNode.setPrev(current.getPrev());
    current.getPrev().setNext(newBookNode);
    current.setPrev(newBookNode);
  }

  public void removeBook(String bookName) {
    Node current = nil.getNext();

    while (current != nil && !current.getBook().getName().equals(bookName)) {
      current = current.getNext();
    }

    if (current != nil) {
      current.setPrev(current.getNext().getPrev());
      current.setNext(current.getNext().getNext());
    }
  }

  public List<Node> searchAllByRating(int rating) {
    List<Node> result = new ArrayList<>();
    Node current = nil.getNext();

    while (current != nil) {
      if (current.getBook().getRating() == rating) {
        result.add(current);
      }
      current = current.getNext();
    }

    return result;
  }

  public List<Node> searchAllByName(String bookName) {
    List<Node> result = new ArrayList<>();
    Node current = nil.getNext();

    while (current != nil) {
      if (current.getBook().getName().equals(bookName)) {
        result.add(current);
      }
      current = current.getNext();
    }

    return result;
  }

  public int getNumberOfBooks() {
    int count = 0;
    Node current = nil.getNext();

    while (current != nil) {
      count++;
      current = current.getNext();
    }

    return count;
  }

  public void printBookDetails(String bookName) {
    List<Node> matchingBooks = searchAllByName(bookName);

    if (!matchingBooks.isEmpty()) {
      for (Node bookNode : matchingBooks) {
        System.out.println(bookNode.getBook());
      }
    } else {
      System.out.println("No books found with the name: " + bookName);
    }
  }

  public void printAllBooks() {
    Node current = nil.getNext();
    System.out.println("--------------------------");
    while (current != nil) {
      System.out.println(current.getBook());
      System.out.println("--------------------------");
      current = current.getNext();
    }
  }

  // Add additional methods as needed
}
