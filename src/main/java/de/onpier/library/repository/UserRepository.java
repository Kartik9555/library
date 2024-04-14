package de.onpier.library.repository;

import de.onpier.library.domain.Users;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<Users, Long> {
    Users findUsersByNameAndFirstName(String name, String firstName);

    List<Users> findAllByBorrowedBooksIsNotEmpty();

    @Query(value = "SELECT distinct u.* FROM users u JOIN borrowed bw ON u.id = bw.borrower_id " +
        "AND :date BETWEEN bw.borrowed_from AND bw.borrowed_to",
        nativeQuery = true)
    List<Users> findAllUsersByBorrowedBooksOnGivenDate(LocalDate date);

    @Query(value = "SELECT distinct u.* FROM users u JOIN borrowed bw ON u.id = bw.borrower_id " +
        "AND (u.member_till is null OR CURRENT_DATE <= u.member_till) " +
        "AND CURRENT_DATE NOT BETWEEN bw.borrowed_from AND bw.borrowed_to",
        nativeQuery = true)
    List<Users> getAllNonTerminatedUsersNotCurrentlyBorrowedAnything();
}
