package se.lexicon.amin.booklender.service;

import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.entity.Book;
import se.lexicon.amin.booklender.entity.LibraryUser;
import se.lexicon.amin.booklender.entity.Loan;

public class DtoEntityConversionServiceImpl implements DtoEntityConversionService {

    @Override
    public Book dtoToBook(BookDto dto) {
        return new Book(
                dto.getTitle(),
                dto.getMaxLoanDays(),
                dto.getFinePerDay(),
                dto.getDescription()
        );
    }

    @Override
    public BookDto bookToDto(Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.isAvailable(),
                book.isReserved(),
                book.getMaxLoanDays(),
                book.getFinePerDay(),
                book.getDescription()
        );
    }

    @Override
    public LibraryUser dtoToLibraryUser(LibraryUserDto dto) {
        return new LibraryUser(
                dto.getRegDate(),
                dto.getName(),
                dto.getEmail()
        );
    }

    @Override
    public LibraryUserDto libraryUserToDto(LibraryUser libraryUser) {
        return new LibraryUserDto(
                libraryUser.getUserId(),
                libraryUser.getRegDate(),
                libraryUser.getName(),
                libraryUser.getEmail()
        );
    }

    @Override
    public Loan dtoToLoan(LoanDto dto) {
        return new Loan(
                dtoToLibraryUser(dto.getLoanTaker()),
                dtoToBook(dto.getBook()),
                dto.getLoanDate(),
                dto.isTerminated()
        );
    }

    @Override
    public LoanDto loanToDto(Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                libraryUserToDto(loan.getLoanTaker()),
                bookToDto(loan.getBook()),
                loan.getLoanDate(),
                loan.isTerminated()
        );
    }
}
