package de.onpier.library.service;

import de.onpier.library.model.dto.BookDto;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<BookDto> getAvailableBooks();

    List<BookDto> getBooksBorrowedByUserBetween(String user, LocalDate from, LocalDate to);
}
