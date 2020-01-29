package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long > {
}
