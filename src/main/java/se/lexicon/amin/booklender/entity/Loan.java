package se.lexicon.amin.booklender.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {

    private long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;

    private LocalDate lastReturnDate;



    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        setLoanTaker(loanTaker);
        setBook(book);
        this.loanDate = loanDate;
        setTerminated(terminated);

        this.lastReturnDate = loanDate.plusDays(book.getMaxLoanDays());
    }

    public Loan() {
    }


    public boolean isOverdue(){

        return (LocalDate.now().isAfter(lastReturnDate)) ? true : false;

    }

    public BigDecimal getFine() {

        int noOfDaysOverdue;
        BigDecimal fine = null;

        if(isOverdue()) {

            noOfDaysOverdue= (int) ChronoUnit.DAYS.between(lastReturnDate, LocalDate.now());

            fine = BigDecimal.valueOf(noOfDaysOverdue * this.book.getFinePerDay().doubleValue());

        } else {

            fine = BigDecimal.valueOf(0);
        }

        return fine;

    }

    public boolean extendLoan(int days){

        lastReturnDate = lastReturnDate.plusDays(days);

        return true;
    }


    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;

    }


    public LocalDate getLastReturnDate() {
        return lastReturnDate;
    }

    public void setLastReturnDate(LocalDate lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId &&
                Objects.equals(loanTaker, loan.loanTaker) &&
                Objects.equals(book, loan.book) &&
                Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loan{");
        sb.append("loanId=").append(loanId);
        sb.append(", loanTaker=").append(loanTaker);
        sb.append(", book=").append(book);
        sb.append(", loanDate=").append(loanDate);
        sb.append(", terminated=").append(terminated);
        sb.append(", lastReturnDate=").append(lastReturnDate);
        sb.append('}');
        return sb.toString();
    }
}
