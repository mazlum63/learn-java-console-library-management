package com.example.library.exception;

public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public static void throwBookNotFound() {
        throw new LibraryException("Book not found");
    }

    public static void throwMemberNotFound() {
        throw new LibraryException("Member not found");
    }

    public static void throwBookAlreadyBorrowed() {
        throw new LibraryException("Book already borrowed");
    }

    public static void throwActiveLoanNotFound() {
        throw new LibraryException("Active loan not found");
    }

    public static void throwBookIsNotBorrowed() {
        throw new LibraryException("Book is not borrowed");
    }
}
