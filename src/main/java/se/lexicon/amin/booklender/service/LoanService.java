package se.lexicon.amin.booklender.service;

import se.lexicon.amin.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {

    LoanDto findById(long loanId);

    List<LoanDto> findByBookId(int bookId);

    List<LoanDto> findByUserId(int userId);

    List<LoanDto> findByTerminated(boolean terminated);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(long loanId);
}
