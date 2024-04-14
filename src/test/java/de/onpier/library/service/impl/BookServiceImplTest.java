package de.onpier.library.service.impl;

import de.onpier.library.domain.Book;
import de.onpier.library.mapper.BookMapper;
import de.onpier.library.model.dto.BookDto;
import de.onpier.library.repository.BookRepository;
import de.onpier.library.service.BookService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BookService.class)
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookMapper bookMapper;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, bookMapper);
    }

    @Test
    void getAvailableBooks() {
        Book book = getBook();
        when(bookRepository.findAllAvailableBooks(any())).thenReturn(Collections.singletonList(book));
        List<BookDto> availableBooks = bookService.getAvailableBooks();
        assertNotNull(availableBooks);
        assertEquals(1, availableBooks.size());
    }


    @Test
    void getBooksBorrowedByUserBetween() {
        Book book = getBook();
        when(bookRepository.findAllBooksBorrowedByUserBetween(any(), any(), any(), any())).thenReturn(Collections.singletonList(book));
        LocalDate today = LocalDate.now();
        List<BookDto> availableBooks = bookService.getBooksBorrowedByUserBetween("Test,Test", today.minusMonths(1), today);
        assertNotNull(availableBooks);
        assertEquals(1, availableBooks.size());
    }

    @Test
    void getBooksBorrowedByUserBetweenThrowsExceptionWithNullUserName() {
        LocalDate today = LocalDate.now();
        assertThrows(
            IllegalArgumentException.class,
            () -> bookService.getBooksBorrowedByUserBetween(null, today.minusMonths(1), today)
        );
    }

    @Test
    void getBooksBorrowedByUserBetweenThrowsExceptionWithNullFromDate() {
        LocalDate today = LocalDate.now();
        assertThrows(
            IllegalArgumentException.class,
            () -> bookService.getBooksBorrowedByUserBetween(null, null, today)
        );
    }

    @Test
    void getBooksBorrowedByUserBetweenThrowsExceptionWithNullToDate() {
        LocalDate today = LocalDate.now();
        assertThrows(
            IllegalArgumentException.class,
            () -> bookService.getBooksBorrowedByUserBetween(null, today.minusMonths(1), null)
        );
    }

    @Test
    void getBooksBorrowedByUserBetweenThrowsExceptionWithInvalidDates() {
        LocalDate today = LocalDate.now();
        assertThrows(
            IllegalArgumentException.class,
            () -> bookService.getBooksBorrowedByUserBetween(null, today, today.minusMonths(1))
        );
    }

    @Test
    void getBooksBorrowedByUserBetweenThrowsExceptionWithInvalidUserName() {
        LocalDate today = LocalDate.now();
        assertThrows(
            IllegalArgumentException.class,
            () -> bookService.getBooksBorrowedByUserBetween("Test", today.minusMonths(1), today)
        );
    }

    private Book getBook(){
        return Book.builder()
            .id(1L)
            .title("Test Title")
            .author("Test Author")
            .publisher("Test Publisher")
            .genre("Test Genre")
            .build();
    }
}