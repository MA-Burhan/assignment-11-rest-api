package se.lexicon.amin.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.amin.booklender.data.LoanRepository;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.entity.Loan;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private DtoEntityConversionService conversionService;

    @Override
    public LoanDto findById(long loanId) {
        Optional<Loan> optionalLoan = repository.findById(loanId);

        Loan loan = optionalLoan.orElseThrow(() -> new RuntimeException("Requested loan does not exist"));

        return conversionService.loanToDto(loan);
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        List<Loan> loans = repository.findByBookBookId(bookId);

        List<LoanDto> loanDtos = loans.stream().map(conversionService::loanToDto).collect(Collectors.toList());

        return loanDtos;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        List<Loan> loans = repository.findByLoanTakerUserId(userId);

        List<LoanDto> loanDtos = loans.stream().map(conversionService::loanToDto).collect(Collectors.toList());

        return loanDtos;
    }

    @Override
    public List<LoanDto> findByTerminated(boolean terminated) {
        List<Loan> loans = repository.findByTerminated(terminated);

        List<LoanDto> loanDtos = loans.stream().map(conversionService::loanToDto).collect(Collectors.toList());

        return loanDtos;
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loans = repository.findAll();

        List<LoanDto> loanDtos = loans.stream().map(conversionService::loanToDto).collect(Collectors.toList());

        return loanDtos;
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if(loanDto.getLoanId() != 0){
            throw new IllegalArgumentException("Loan had invalid id: " + loanDto.getLoanId());
        }

        Loan newLoan = conversionService.dtoToLoan(loanDto);

        newLoan = repository.save(newLoan);
        return conversionService.loanToDto(newLoan);
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        if(loanDto.getLoanId() == 0){
            throw new IllegalArgumentException("Loan had invalid id: " + loanDto.getLoanId());
        }

        Optional<Loan> optionalLoan = repository.findById(loanDto.getLoanId());
        Loan loan = optionalLoan.orElseThrow(() -> new RuntimeException("Requested loan does not exist"));

        loan.setLoanTaker(conversionService.dtoToLibraryUser(loanDto.getLoanTaker()));
        loan.setBook(conversionService.dtoToBook(loanDto.getBook()));
        loan.setTerminated(loanDto.isTerminated());
        loan.setLastReturnDate(loanDto.getLastReturnDate());

        repository.save(loan);

        return loanDto;
    }

    @Override
    public boolean delete(long loanId) {
        Optional<Loan> optionalLoan = repository.findById(loanId);

        if(optionalLoan.isPresent()) {
            repository.deleteById(loanId);
            return true;
        }

        return false;
    }
}
