package de.onpier.library.web.controller;

import de.onpier.library.model.dto.BookDto;
import de.onpier.library.service.BookService;
import java.time.LocalDate;
import java.util.Collections;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void getBooksBorrowedByUserBetween() throws Exception {
        BookDto bookDto = getBook();
        String user = "Test,Test";
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 2, 1);
        when(bookService.getBooksBorrowedByUserBetween(user, from, to)).thenReturn(Collections.singletonList(bookDto));
        mockMvc.perform(get("/api/v1/book/books-borrowed")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("userName", user)
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", Is.is(1)));
    }


    @Test
    void getAvailableBooks() throws Exception {
        BookDto bookDto = getBook();
        when(bookService.getAvailableBooks()).thenReturn(Collections.singletonList(bookDto));
        mockMvc.perform(get("/api/v1/book/available-books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", Is.is(1)));
    }


    private BookDto getBook() {
        return BookDto.builder()
            .title("Test Title")
            .author("Test Author")
            .publisher("Test Publisher")
            .genre("Test Genre")
            .build();
    }
}