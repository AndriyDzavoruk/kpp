package com.library;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryManagerYaml {
    private static final String filename = "books.yaml";


    public static void saveBooks(List<Book> books) {
        try (FileWriter writer = new FileWriter(filename)) {
            DumperOptions options = new DumperOptions();
            options.setIndent(2);  // Set indentation for readability
            options.setPrettyFlow(true);  // Format nicely for readability
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);  // Use block style for lists

            Yaml yaml = new Yaml(options);


            List<Map<String, Object>> booksAsMaps = books.stream()
                    .map(Book::toHashMap)
                    .collect(Collectors.toList());

            // Serialize the list of books as a single YAML document
            yaml.dump(booksAsMaps, writer);

            System.out.println("Books have been serialized to YAML file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {
        try (FileReader reader = new FileReader(filename)) {
            Yaml yaml = new Yaml();

            // Read the YAML file into a list of maps
            List<Map<String, Object>> booksAsMaps = yaml.load(reader);

            // Convert each map entry to a Book object
            List<Book> books = booksAsMaps.stream()
                    .map(Book::fromMap)
                    .collect(Collectors.toList());

            System.out.println("Books have been loaded from the YAML file.");
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
