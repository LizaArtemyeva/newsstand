package ru.vsu.cs.Domain;

public abstract class Paper{
    protected int ID;
    protected String name;
    protected Status status;

    public Paper(String name, Status status) {
        this.status = status;
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Type getType();
}
