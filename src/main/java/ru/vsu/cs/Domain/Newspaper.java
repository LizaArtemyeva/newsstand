package ru.vsu.cs.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.NEWSPAPER;

@Entity
@Table(name = "newspaper")
public class Newspaper extends Paper {
    @Column(name = "number")
    private int number;
    @Column(name = "date")
    private String date;
    public Newspaper(String name, int number, String date) {
        super(name,IN_STOCK);
        this.number=number;
        this.date=date;
    }

    public Newspaper(String name, Status status) {
        super(name, status);
    }

    public Newspaper() {
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
        return "Газета: " + id + " " + name + " "+ number + " " + date;
    }

    @Override
    public Type getType() {
        return NEWSPAPER;
    }
}
