package ru.vsu.cs.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paper")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Paper implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "name")
    protected String name;
    @Column(name = "status")
    protected Status status;

    public Paper(String name, Status status) {
        this.status = status;
        this.name = name;
    }

    public Paper() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Type getType();
}
