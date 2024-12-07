package com.library;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.*;

public class Book implements Serializable {
    @SerializedName("title") // Задаємо ім'я поля JSON
    @Expose
    private String title;

    @SerializedName("author")
    @Expose
    private Author author;

    @SerializedName("publication_year")
    @Expose(serialize = false)
    private transient int publicationYear;

    public Book(String title, Author author, int year) {
        this.title = title;
        this.author = author;
        this.publicationYear = year;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Author getAuthorForSerialization() {
        return publicationYear < 2000 ? author : null; // Серіалізуємо author лише для книг до 2000 року
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + publicationYear;
    }

    public HashMap<String, Object> toHashMap(){
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", this.getTitle());
        if (this.getAuthorForSerialization() != null) {
            dataMap.put("author_name", this.getAuthorForSerialization().getName());
            dataMap.put("author_surname", this.getAuthorForSerialization().getSurname());
            dataMap.put("author_nationality", this.getAuthorForSerialization().getNationality());
        }
        dataMap.put("year", this.getPublicationYear());
        return dataMap;
    }

    public static Book fromMap(Map<String, Object> map) {
        String authorName = (String) map.get("author_name");
        String authorSurname = (String) map.get("author_surname");
        String authorNationality = (String) map.get("author_nationality");

        int publicationYear = map.get("year") != null ? (int) map.get("year") : 0;
        String title = (String) map.get("title");

        Author author = new Author(authorName, authorSurname, authorNationality);
        return new Book(title, author, publicationYear);
    }
}