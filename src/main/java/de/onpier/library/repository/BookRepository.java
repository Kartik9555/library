package de.onpier.library.repository;

import de.onpier.library.domain.Book;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, Long> {
    Book findBookByTitle(String name);

    @Query(value = "SELECT distinct b.* FROM book b LEFT JOIN borrowed bw ON b.id = bw.book_id " +
        "AND :today BETWEEN bw.borrowed_from AND bw.borrowed_to WHERE bw.book_id IS NULL",
        nativeQuery = true)
    List<Book> findAllAvailableBooks(LocalDate today);

    @Query(value = "SELECT distinct b.* FROM book b JOIN borrowed bw ON b.id = bw.book_id JOIN users u ON bw.borrower_id = u.id WHERE upper(u.name) = :name and upper(u.first_name) = " +
        ":firstName AND bw.borrowed_from <= :to AND bw.borrowed_to >= :from",
        nativeQuery = true)
    List<Book> findAllBooksBorrowedByUserBetween(String name, String firstName, LocalDate from, LocalDate to);
}
