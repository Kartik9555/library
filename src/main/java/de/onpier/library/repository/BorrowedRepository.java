package de.onpier.library.repository;

import de.onpier.library.domain.Borrowed;
import org.springframework.data.repository.ListCrudRepository;

public interface BorrowedRepository extends ListCrudRepository<Borrowed, Long> {
}
