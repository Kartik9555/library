package de.onpier.library.bootstrap;

import de.onpier.library.domain.Book;
import de.onpier.library.domain.Borrowed;
import de.onpier.library.domain.Users;
import de.onpier.library.model.csv.BookCsvDto;
import de.onpier.library.model.csv.BorrowedCsvDto;
import de.onpier.library.model.csv.UserCsvDto;
import de.onpier.library.repository.BookRepository;
import de.onpier.library.repository.BorrowedRepository;
import de.onpier.library.repository.UserRepository;
import de.onpier.library.service.CsvService;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@RequiredArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowedRepository borrowedRepository;
    private final CsvService csvService;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadBookData();
        loadBorrowedData();
    }


    private void loadUserData() throws FileNotFoundException {
        if (userRepository.count() == 0) {
            File file = ResourceUtils.getFile("classpath:csvdata/user.csv");
            List<UserCsvDto> records = csvService.convertUserCSV(file);
            records.forEach(record -> {
                Users user = Users.builder()
                    .firstName(record.getFirstName())
                    .name(record.getName())
                    .gender(record.getGender())
                    .memberSince(record.getMemberSince())
                    .memberTill(record.getMemberTill())
                    .build();

                userRepository.save(user);
            });
        }
    }


    private void loadBookData() throws FileNotFoundException {
        if (bookRepository.count() == 0) {
            File file = ResourceUtils.getFile("classpath:csvdata/books.csv");
            List<BookCsvDto> records = csvService.convertBookCSV(file);
            records.forEach(record -> {
                Book book = Book.builder()
                    .title(record.getTitle())
                    .author(record.getAuthor())
                    .genre(record.getGenre())
                    .publisher(record.getPublisher())
                    .build();
                bookRepository.save(book);
            });
        }
    }


    private void loadBorrowedData() throws FileNotFoundException {
        if (borrowedRepository.count() == 0) {
            File file = ResourceUtils.getFile("classpath:csvdata/borrowed.csv");
            List<BorrowedCsvDto> records = csvService.convertBorrowedCSV(file);
            records.forEach(record -> {
                Book book = bookRepository.findBookByTitle(record.getBook());
                String[] users = record.getBorrower().split(",");
                Users user = userRepository.findUsersByNameAndFirstName(users[0], users[1]);
                Borrowed borrowed = Borrowed.builder()
                    .book(book)
                    .borrower(user)
                    .borrowedFrom(record.getBorrowedFrom())
                    .borrowedTo(record.getBorrowedTo())
                    .build();
                borrowedRepository.save(borrowed);

                user.getBorrowedBooks().add(borrowed);
                book.getBorrowedBooks().add(borrowed);
                userRepository.save(user);
                bookRepository.save(book);
            });
        }
    }
}
