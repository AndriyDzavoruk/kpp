package com.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення книг та авторів
        Author Rowling = new Author("J.K.","Rowling", "British");
        Book book1 = new Book("Harry Potter and the Philosopher's Stone", Rowling, 1997);
        Book book2 = new Book("Harry Potter and the Chamber of Secrets", Rowling, 1998);


        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        ArrayList<Author> authors;


        // Серіалізація в файл
        LibraryManagerIO.saveBooks(books);

        List<Book> deserializedBooks =  LibraryManagerIO.loadBooks();
        for (Book book : deserializedBooks) {
            System.out.println(book.toString());
        }

        List<Book> books2 = new ArrayList<>();
        books2.add(new Book("Pride and Prejudice", new Author("Jane", "Austen", "British"), 1813));
        books2.add(new Book("1984", new Author("George", "Orwell", "British"), 1949));
        books2.add(new Book("Untitled", null, 2020)); // Книга без автора

        LibraryManagerNative.saveBooks(books2);

        LibraryManagerJson.saveBooks(books2);
        List<Book> deserializedBooksJson = LibraryManagerJson.loadBooks();
        for (Book book : deserializedBooksJson) {
            System.out.println(book.toString());
        }


        LibraryManagerYaml.saveBooks(books2);
        List<Book> deserializedBooksYaml = LibraryManagerYaml.loadBooks();
        for (Book book : deserializedBooksYaml) {
            System.out.println(book.toString());
        }

    }
}
