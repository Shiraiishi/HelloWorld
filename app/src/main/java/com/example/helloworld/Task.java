package com.example.helloworld;

public class Task {
    private long id;
    private String name;
    private String description;
    private int reward;
    private String lastCompletion;

    public Task(long id, String name, String description, int reward, String lastCompletion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.lastCompletion = lastCompletion;
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

    public int getReward() {
        return reward;
    }

    public String getLastCompletion() {
        return lastCompletion;
    }
}
