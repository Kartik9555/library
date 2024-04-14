package de.onpier.library.service.impl;

import de.onpier.library.domain.Users;
import de.onpier.library.mapper.UsersMapper;
import de.onpier.library.model.dto.UserDto;
import de.onpier.library.repository.UserRepository;
import de.onpier.library.service.UserService;
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

@WebMvcTest(UserService.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UsersMapper usersMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, usersMapper);
    }

    @Test
    void getAllUsersBorrowedAtleastOneBook() {
        Users user = getUser();
        when(userRepository.findAllByBorrowedBooksIsNotEmpty()).thenReturn(Collections.singletonList(user));
        List<UserDto> users = userService.getAllUsersBorrowedAtleastOneBook();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void getAllNonTerminatedUsersNotCurrentlyBorrowedAnything() {
        Users user = getUser();
        when(userRepository.getAllNonTerminatedUsersNotCurrentlyBorrowedAnything()).thenReturn(Collections.singletonList(user));
        List<UserDto> users = userService.getAllNonTerminatedUsersCurrentlyBorrowedNothing();
        assertNotNull(users);
        assertEquals(1, users.size());
    }


    @Test
    void getAllUsersBorrowedBookOnGivenDate() {
        Users user = getUser();
        when(userRepository.findAllUsersByBorrowedBooksOnGivenDate(any())).thenReturn(Collections.singletonList(user));
        LocalDate date = LocalDate.of(2012, 12, 29);
        List<UserDto> users = userService.getAllUsersBorrowedBookOnGivenDate(date);
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void getAllUsersBorrowedBookOnGivenDateThrowsExceptionWhenNullGivenDate() {
        assertThrows(
            IllegalArgumentException.class,
            () -> userService.getAllUsersBorrowedBookOnGivenDate(null)
        );
    }

    private Users getUser(){
        return Users.builder()
            .id(1L)
            .name("Test Name")
            .firstName("Test First Name")
            .gender("M")
            .memberSince(LocalDate.now().minusYears(1))
            .memberTill(null)
            .build();
    }
}