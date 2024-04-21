package de.onpier.library.web.controller;

import de.onpier.library.model.dto.BookDto;
import de.onpier.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Tag(name = "BookService")
public class BookController {

    private final BookService bookService;


    @Operation(description = "Returns all books borrowed by a given user in a given date range")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Found the books",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = BookDto.class))
                    )
                }
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input parameters",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
        }
    )
    @GetMapping("/books-borrowed")
    public ResponseEntity<List<BookDto>> getBooksBorrowedByUserBetween(
        @RequestParam String userName,
        @RequestParam LocalDate from,
        @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(bookService.getBooksBorrowedByUserBetween(userName, from, to));
    }


    @Operation(description = "Returns all available (not borrowed) books")
    @ApiResponses(
        value = @ApiResponse(
            responseCode = "200",
            description = "Found the books",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class))
                )
            }
        )
    )
    @GetMapping("/available-books")
    public ResponseEntity<List<BookDto>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }
}
