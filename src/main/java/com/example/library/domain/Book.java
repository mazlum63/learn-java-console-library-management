package com.example.library.domain;

public class Book {
    private int id;
    private String title;
    private String author;
    private BookStatus status;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean borrow() {
        if (status == BookStatus.BORROWED)
            return false;

        this.status = BookStatus.BORROWED;

        return true;
    }

    public boolean returnBook() {
        if (status == BookStatus.AVAILABLE)
            return false;

        this.status = BookStatus.AVAILABLE;

        return true;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public BookStatus getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "%d - %s - %s - %s".formatted(this.id, this.title, this.author, this.status.toString());
    }
}
