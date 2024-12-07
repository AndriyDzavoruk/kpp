package com.library;

import java.io.*;
import java.util.*;

public class LibraryManagerIO {
    private static final String titlesFile = "titles.dat";
    private static final String authorFile = "authors.dat";

    // Метод для серіалізації списку книг
    public static void saveBooks(List<Book> books) {
        try (BufferedOutputStream titleOut = new BufferedOutputStream(new FileOutputStream(titlesFile));
             FileOutputStream authorOut = new FileOutputStream(authorFile)) {

            for (Book book : books) {
                // Записуємо назву книги в буферизований потік
                titleOut.write(book.getTitle().getBytes());
                titleOut.write('\n'); // Розділяємо назви новим рядком

                // Записуємо автора в небуферизований потік
                ObjectOutputStream authorStream = new ObjectOutputStream(authorOut);
                authorStream.writeObject(book.getAuthor());
            }

            System.out.println("Список книг збережено успішно");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для десеріалізації списку книг
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedInputStream titleIn = new BufferedInputStream(new FileInputStream(titlesFile));
             FileInputStream authorIn = new FileInputStream(authorFile)) {

            BufferedReader titleReader = new BufferedReader(new InputStreamReader(titleIn));
            String title;
            while ((title = titleReader.readLine()) != null) {
                // Зчитуємо автора з небуферизованого потоку
                ObjectInputStream authorStream = new ObjectInputStream(authorIn);
                Author author = (Author) authorStream.readObject();

                // Створюємо об'єкт книги та додаємо його до списку
                Book book = new Book(title, author, 0); // Поле publicationYear не серіалізується, тому встановлюємо його як 0
                books.add(book);
            }

            System.out.println("Список книг завантажено успішно");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return books;
    }
}
