package com.library;

import java.io.*;
import java.util.*;

public class LibraryManagerNative {
    private static final String filename = "books.dat";

    public static void saveBooks(List<Book> books) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            for (Book book : books) {
                // Перевірка, чи є автор. Якщо автора немає, пропускаємо цю книгу.
                if (book.getAuthor() != null) {
                    objectOut.writeObject(book);
                }
            }
            System.out.println("Серіалізація завершена");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
