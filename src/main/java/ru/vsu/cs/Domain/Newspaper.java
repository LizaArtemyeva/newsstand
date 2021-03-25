package ru.vsu.cs.Domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.NEWSPAPER;
public class Newspaper extends Paper {
    private int number;
    private String date;
    public Newspaper(String name, int number, String date) {
        super(name,IN_STOCK);
        this.number=number;
        this.date=date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Газета: " + ID + " " + name + " "+ number + " " + date;
    }

    @Override
    public Type getType() {
        return NEWSPAPER;
    }
}
