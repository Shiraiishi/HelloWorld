package com.example.helloworld;

public class Skill {
    private int id;
    private String name;
    private String desc;
    private int level;
    private int exp;

    public Skill(int id, String name, String desc, int level, int exp) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.level = level;
        this.exp = exp;
    }

    // Getters and setters for each field
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
