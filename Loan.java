import java.time.LocalDate;

public class Loan {
    private int id;
    private Book book;
    private Member member;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(int id, Book book, Member member) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.loanDate = LocalDate.now();
    }

    public boolean markAsReturned() {
        if (returnDate != null)
            return false;

        this.returnDate = LocalDate.now();

        return true;
    }

    public int getId() {
        return this.id;
    }

    public Book getBook() {
        return this.book;
    }

    public Member getMember() {
        return this.member;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    @Override
    public String toString() {
        return "%d - Book: %s - Member: %s - Loan Date: %s - Return Date: %s".formatted(this.id, this.book.getTitle(),
                this.member.getFullName(), this.loanDate, this.returnDate);
    }
}
