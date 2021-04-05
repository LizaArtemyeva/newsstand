package ru.vsu.cs.Domain;

import javax.persistence.*;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.BOOK;

@Entity
@Table(name = "book")
public class Book extends Paper {

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "publishingHouse")
    private String publishingHouse;

    @Column(name = "numPages")
    private int numPages;

    public Book(String name, String author, String publishingHouse, int numPages) {
        super(name,IN_STOCK);
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.numPages = numPages;
    }

    public Book(String name, Status status) {
        super(name, status);
    }

    public Book() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        return "Книга: " + id + " " + name + " "+ author + " " + publishingHouse + " " + numPages;
    }

    @Override
    public Type getType() {
        return BOOK;
    }
}
