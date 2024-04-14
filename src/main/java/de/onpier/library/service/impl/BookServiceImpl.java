package de.onpier.library.service.impl;

import de.onpier.library.mapper.BookMapper;
import de.onpier.library.model.dto.BookDto;
import de.onpier.library.repository.BookRepository;
import de.onpier.library.service.BookService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Override
    public List<BookDto> getAvailableBooks() {
        LocalDate today = LocalDate.now();
        return bookRepository.findAllAvailableBooks(today)
            .stream()
            .map(bookMapper::toBookDto)
            .toList();
    }


    @Override
    public List<BookDto> getBooksBorrowedByUserBetween(String user, LocalDate from, LocalDate to) {
        if (!StringUtils.hasText(user) || !user.contains(",") || from == null || to == null || from.isAfter(to)) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        String[] users = user.split(",");
        return bookRepository.findAllBooksBorrowedByUserBetween(users[0].toUpperCase(), users[1].toUpperCase(), from, to)
            .stream()
            .map(bookMapper::toBookDto)
            .toList();
    }
}
