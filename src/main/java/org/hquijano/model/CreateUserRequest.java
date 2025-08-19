package org.hquijano.model;
// This is a POJO (Plain Old Java Object) representing a request to create a new employee

public class CreateUserRequest {
    private String name;
    private String job;

    public CreateUserRequest() {}
    public CreateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() { return name; }
    public String getJob() { return job; }

    public void setName(String name) { this.name = name; }
    public void setJob(String job) { this.job = job; }

    @Override
    public String toString() {
        return "CreateUserRequest{name='" + name + "', job='" + job + "'}";
    }
}
