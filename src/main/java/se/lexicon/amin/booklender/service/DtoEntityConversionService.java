package se.lexicon.amin.booklender.service;

import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.entity.Book;
import se.lexicon.amin.booklender.entity.LibraryUser;
import se.lexicon.amin.booklender.entity.Loan;

public interface DtoEntityConversionService {

    // Book - BookDto
    Book dtoToBook(BookDto dto);
    BookDto bookToDto(Book book);

    // LibraryUser - LibraryUserDto
    LibraryUser dtoToLibraryUser(LibraryUserDto dto);
    LibraryUserDto libraryUserToDto(LibraryUser libraryUser);

    //Loan - LoanDto
    Loan dtoToLoan(LoanDto dto);
    LoanDto loanToDto(Loan loan);



}
