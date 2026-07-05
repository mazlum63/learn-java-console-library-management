package com.example.library.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.library.domain.*;
import com.example.library.exception.LibraryException;

public class LibraryService {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private List<Loan> loans = new ArrayList<>();

    private int bookIdCounter = 1;
    private int memberIdCounter = 1;
    private int loanIdCounter = 1;

    public Book addBook(String title, String author) {
        Book newBook = new Book(bookIdCounter, title, author);
        books.put(newBook.getId(), newBook);
        bookIdCounter++;
        return newBook;
    }

    public Member addMember(String fullName) {
        Member newMember = new Member(memberIdCounter, fullName);
        members.put(newMember.getId(), newMember);
        memberIdCounter++;
        return newMember;
    }

    public Collection<Book> getBooks() {
        return books.values();
    }

    public Collection<Member> getMembers() {
        return members.values();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public Loan borrowBook(int bookId, int memberId) {
        Optional<Book> bookOpt = this.findBookById(bookId);
        if (bookOpt.isEmpty())
            LibraryException.throwBookNotFound();

        Book book = bookOpt.get();

        if (book.getStatus() != BookStatus.AVAILABLE)
            LibraryException.throwBookAlreadyBorrowed();

        Optional<Member> memberOpt = this.findMemberById(memberId);
        if (memberOpt.isEmpty())
            LibraryException.throwMemberNotFound();

        boolean borrowResult = book.borrow();
        if (!borrowResult)
            LibraryException.throwBookAlreadyBorrowed();

        Loan newLoan = new Loan(loanIdCounter, book, memberOpt.get());
        loans.add(newLoan);

        loanIdCounter++;

        return newLoan;
    }

    public void returnBook(int bookId) {
        Optional<Book> bookOpt = this.findBookById(bookId);
        if (bookOpt.isEmpty())
            LibraryException.throwBookNotFound();

        if (bookOpt.get().getStatus() != BookStatus.BORROWED)
            LibraryException.throwBookIsNotBorrowed();

        Optional<Loan> loan = findActiveLoanByBookId(bookId);

        if (loan.isEmpty())
            LibraryException.throwActiveLoanNotFound();

        boolean returnResult = loan.get().getBook().returnBook();
        if (!returnResult)
            LibraryException.throwActiveLoanNotFound();

        loan.get().markAsReturned();
    }

    private Optional<Book> findBookById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    private Optional<Member> findMemberById(int id) {
        return Optional.ofNullable(members.get(id));
    }

    private Optional<Loan> findActiveLoanByBookId(int bookId) {
        return loans.stream()
                .filter(l -> l.getBook().getId() == bookId && l.getReturnDate() == null)
                .findFirst();
    }
}
