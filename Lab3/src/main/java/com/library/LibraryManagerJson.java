package com.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class LibraryManagerJson {
    private static final String filename = "books.json";
    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation() // Ігноруємо поля без анотації @Expose
            .setPrettyPrinting() // Форматуємо JSON для зручності
            .create();

    public static void saveBooks(List<Book> books) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(books, writer); // Перетворюємо список книг у JSON і записуємо у файл
            System.out.println("Книги серіалізовано в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {
        try (FileReader reader = new FileReader(filename)) {
            Type bookListType = new TypeToken<List<Book>>(){}.getType();
            List<Book> books = gson.fromJson(reader, bookListType); // Відновлюємо список книг з JSON
            System.out.println("Книги завантажено з файлу.");
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
