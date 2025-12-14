package com.example.project;

public class Task {
    private long id;
    private String name;
    private String description;
    private String category;
    private String status;

    // Default constructor
    public Task() {}

    // Parameterized constructor
    public Task(String name, String description, String category, String status) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
