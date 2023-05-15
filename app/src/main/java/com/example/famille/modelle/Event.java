package com.example.famille.modelle;

public class Event {
    private int id;
    private String name;
    private String date;
    private String  description;
    private int caniote;
    private Person person;

    public Event() {
    }

    public Event(int id, String name, String date, String description, int caniote, Person person) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.caniote = caniote;
        this.person = person;
    }

    public Event(int id, String name, String date, String description, int caniote) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.caniote = caniote;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCaniote() {
        return caniote;
    }

    public void setCaniote(int caniote) {
        this.caniote = caniote;
    }
}
