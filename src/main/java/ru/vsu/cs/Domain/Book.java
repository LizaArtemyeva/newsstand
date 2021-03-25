package ru.vsu.cs.Domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.BOOK;
public class Book extends Paper {
    private String author, publishingHouse;
    private int numPages;
    public Book(String name, String author, String publishingHouse, int numPages) {
        super(name,IN_STOCK);
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.numPages = numPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "Книга: " + ID + " " + name + " "+ author + " " + publishingHouse + " " + numPages;
    }

    @Override
    public Type getType() {
        return BOOK;
    }
}
