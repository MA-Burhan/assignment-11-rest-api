package se.lexicon.amin.booklender.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository testRepository;
}
