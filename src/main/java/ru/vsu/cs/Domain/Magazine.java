package ru.vsu.cs.Domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.MAGAZINE;
public class Magazine extends Paper {
    private int number, numPages;
    private String date;
    public Magazine(String name, int number, String date, int numPages) {
        super(name,IN_STOCK);
        this.number = number;
        this.date = date;
        this.numPages = numPages;
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
        return "Журнал: " + ID + " " + name + " "+ number + " " + date + " " + numPages;
    }

    @Override
    public Type getType() {
        return MAGAZINE;
    }
}
