package se.lexicon.amin.booklender.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bookId;

    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

    public Book(String title, int maxLoanDays, BigDecimal finePerDay, String description) {
        setTitle(title);
        setMaxLoanDays(maxLoanDays);
        setFinePerDay(finePerDay);
        setDescription(description);
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", available=").append(available);
        sb.append(", reserved=").append(reserved);
        sb.append(", maxLoanDays=").append(maxLoanDays);
        sb.append(", finePerDay=").append(finePerDay);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


