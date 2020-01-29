package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long > {

    List<Loan> findByLoanTakerUserId(int userId);

    List<Loan> findByBookBookId(int bookId);

    List<Loan> findByTerminated(boolean terminated);
}
