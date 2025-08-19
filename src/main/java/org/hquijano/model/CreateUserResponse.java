package org.hquijano.model;
// This is a POJO (Plain Old Java Object) representing a response from the API when creating a new user.

public class CreateUserResponse {
    private String id;
    private String name;
    private String job;
    private String createdAt;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getJob() { return job; }
    public String getCreatedAt() { return createdAt; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setJob(String job) { this.job = job; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
