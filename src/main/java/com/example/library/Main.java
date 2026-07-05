package com.example.library;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.example.library.domain.*;
import com.example.library.exception.LibraryException;
import com.example.library.service.LibraryService;

public class Main {
    public static void main(String[] arg) {

        LibraryService libService = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("========================================");
            System.out.println("1 - Add Book");
            System.out.println("2 - Add Member");
            System.out.println("3 - List Books");
            System.out.println("4 - List Members");
            System.out.println("5 - Borrow Book");
            System.out.println("6 - Return Book");
            System.out.println("7 - List Loans");
            System.out.println("0 - Exit");

            int selected = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (selected) {
                    case 1:
                        System.out.println("==Add Book==");

                        System.out.println("Title");
                        String title = scanner.nextLine();

                        System.out.println("Author");
                        String author = scanner.nextLine();

                        Book newBook = libService.addBook(title, author);
                        System.out.println(newBook);
                        break;

                    case 2:
                        System.out.println("==Add Member==");

                        System.out.println("Member Name");
                        String name = scanner.nextLine();

                        Member newMember = libService.addMember(name);
                        System.out.println(newMember);
                        break;

                    case 3:
                        System.out.println("==Books==");
                        Collection<Book> books = libService.getBooks();
                        books.forEach(b -> System.out.println(b.toString()));
                        break;

                    case 4:
                        Collection<Member> members = libService.getMembers();
                        members.forEach(m -> System.out.println(m.toString()));
                        break;

                    case 5: {
                        System.out.println("==Borrow Book==");
                        System.out.println("Book ID");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Member ID");
                        int memberId = scanner.nextInt();

                        Loan loan = libService.borrowBook(bookId, memberId);

                        System.out.println(loan);
                        break;
                    }

                    case 6: {
                        System.out.println("==Return Book==");
                        System.out.println("Book ID");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();

                        libService.returnBook(bookId);

                        break;
                    }

                    case 7:
                        System.out.println("==Loans==");
                        List<Loan> loans = libService.getLoans();
                        loans.forEach(l -> System.out.println(l.toString()));
                        break;

                    case 0:
                        System.out.println("==Exit==");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid Selection");
                        break;
                }

            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}