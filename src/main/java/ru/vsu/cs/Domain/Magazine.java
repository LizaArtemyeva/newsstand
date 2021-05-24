package ru.vsu.cs.Domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import static ru.vsu.cs.Domain.Status.IN_STOCK;
import static ru.vsu.cs.Domain.Type.MAGAZINE;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Paper implements Serializable {
    @Column(name = "number")
    private int number;
    @Column(name = "numPages")
    private int numPages;
    @Column(name = "date")
    private String date;

    public Magazine(String name, int number, String date, int numPages) {
        super(name, IN_STOCK);
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
        return "Журнал: " + id + " " + name + " " + number + " " + date + " " + numPages;
    }

    @Override
    public Type getType() {
        return MAGAZINE;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Magazine){
            Magazine magazine = ((Magazine) obj);
            return magazine.getName().equals(getName()) &&
                    magazine.getNumber() == getNumber() &&
                    magazine.getDate().equals(getDate()) &&
                    magazine.getNumPages() == getNumPages();
        }else {
            return false;
        }
    }
}
