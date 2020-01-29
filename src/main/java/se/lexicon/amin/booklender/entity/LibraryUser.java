package se.lexicon.amin.booklender.entity;

import java.time.LocalDate;
import java.util.Objects;

public class LibraryUser {

    private int userId;
    private LocalDate regDate;
    private String name;
    private String email;

    public LibraryUser(LocalDate regDate, String name, String email) {
        if (regDate == null) throw new IllegalArgumentException("regDate was null");
        this.regDate = regDate;
        setName(name);
        setEmail(email);
    }

    public LibraryUser() {
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
        return userId == that.userId &&
                Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, regDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LibraryUser{");
        sb.append("userId=").append(userId);
        sb.append(", regDate=").append(regDate);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
