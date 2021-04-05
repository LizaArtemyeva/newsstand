package ru.vsu.cs.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.MAGAZINE;

@Entity
@Table(name = "magazine")
public class Magazine extends Paper {
    @Column(name = "number")
    private int number;
    @Column(name = "numPages")
    private int numPages;
    @Column(name = "date")
    private String date;
    public Magazine(String name, int number, String date, int numPages) {
        super(name,IN_STOCK);
        this.number = number;
        this.date = date;
        this.numPages = numPages;
    }

    public Magazine(String name, Status status) {
        super(name, status);
    }

    public Magazine() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Журнал: " + id + " " + name + " "+ number + " " + date + " " + numPages;
    }

    @Override
    public Type getType() {
        return MAGAZINE;
    }
}
