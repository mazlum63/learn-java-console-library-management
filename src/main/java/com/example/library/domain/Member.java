package com.example.library.domain;

public class Member {
    private int id;
    private String fullName;

    public Member(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override
    public String toString() {
        return "%d - %s".formatted(this.id, this.fullName);
    }
}
