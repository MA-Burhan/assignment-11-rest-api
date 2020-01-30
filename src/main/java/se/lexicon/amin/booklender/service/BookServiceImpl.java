package se.lexicon.amin.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.amin.booklender.data.BookRepository;
import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private DtoEntityConversionService conversionService;

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        List<Book> books = repository.findByReserved(reserved);

        List<BookDto> bookDtos = books.stream().map(conversionService::bookToDto).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        List<Book> books = repository.findByReserved(available);

        List<BookDto> bookDtos = books.stream().map(conversionService::bookToDto).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        List<Book> books = repository.findByTitle(title);

        List<BookDto> bookDtos = books.stream().map(conversionService::bookToDto).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public BookDto findById(int bookId) {
        Optional<Book> optionalBook = repository.findById(bookId);

        Book book = optionalBook.orElseThrow(() -> new RuntimeException("Requested book does not exist"));

        return conversionService.bookToDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = repository.findAll();

        List<BookDto> bookDtos = books.stream().map(conversionService::bookToDto).collect(Collectors.toList());

        return bookDtos;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if(bookDto.getBookId() != 0){
            throw new IllegalArgumentException("Book had invalid id: " + bookDto.getBookId());
        }

        Book newBook = conversionService.dtoToBook(bookDto);

        newBook = repository.save(newBook);
        return conversionService.bookToDto(newBook);
    }

    @Override
    public BookDto update(BookDto bookDto) {

        if(bookDto.getBookId() == 0){
            throw new IllegalArgumentException("Book had invalid id: " + bookDto.getBookId());
        }

        Optional<Book> optionalBook = repository.findById(bookDto.getBookId());
        Book book = optionalBook.orElseThrow(() -> new RuntimeException("Requested book does not exist"));

        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setFinePerDay(bookDto.getFinePerDay());
        book.setMaxLoanDays(bookDto.getMaxLoanDays());
        book.setAvailable(bookDto.isAvailable());
        book.setReserved(bookDto.isReserved());

        repository.save(book);

        return bookDto;
    }

    @Override
    public boolean delete(int bookId) {
        Optional<Book> optionalBook = repository.findById(bookId);

        if(optionalBook.isPresent()) {
            repository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
