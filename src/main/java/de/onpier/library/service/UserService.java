package de.onpier.library.service;

import de.onpier.library.model.dto.UserDto;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsersBorrowedAtleastOneBook();

    List<UserDto> getAllNonTerminatedUsersCurrentlyBorrowedNothing();

    List<UserDto> getAllUsersBorrowedBookOnGivenDate(LocalDate borrowedDate);
}
