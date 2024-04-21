package de.onpier.library.web.controller;

import de.onpier.library.model.dto.UserDto;
import de.onpier.library.service.UserService;
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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "UserService")
public class UserController {
    private final UserService userService;

    @Operation(description = "Returns all users who have actually borrowed at least one book")
    @ApiResponses(
        value = @ApiResponse(
            responseCode = "200",
            description = "Found the users",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                )
            }
        )
    )
    @GetMapping("/borrowers")
    public ResponseEntity<List<UserDto>> getAllUsersBorrowedAtleastOneBook() {
        return ResponseEntity.ok(userService.getAllUsersBorrowedAtleastOneBook());
    }

    @Operation(description = "Returns all non-terminated users who have not currently borrowed anything")
    @ApiResponses(
        value = @ApiResponse(
            responseCode = "200",
            description = "Found the users",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                )
            }
        )
    )
    @GetMapping("/active-non-borrowers")
    public ResponseEntity<List<UserDto>> getAllNonTerminatedUsersCurrentlyBorrowedNothing() {
        return ResponseEntity.ok(userService.getAllNonTerminatedUsersCurrentlyBorrowedNothing());
    }

    @Operation(description = "Returns all users who have borrowed a book on a given date")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Found the users",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
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
    @GetMapping("/borrowers-by-date")
    public ResponseEntity<List<UserDto>> getAllUsersBorrowedBookOnGivenDate(
        @RequestParam LocalDate borrowedDate
    ) {
        return ResponseEntity.ok(userService.getAllUsersBorrowedBookOnGivenDate(borrowedDate));
    }
}
