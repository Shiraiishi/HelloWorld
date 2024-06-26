package com.example.helloworld;
public class Skill {
    private long id;
    private String name;
    private String description;
    private int level;
    private int exp;

    public Skill(long id, String name, String description, int level, int exp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.exp = exp;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
}
