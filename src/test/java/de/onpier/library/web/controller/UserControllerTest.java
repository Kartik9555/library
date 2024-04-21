package de.onpier.library.web.controller;

import de.onpier.library.model.dto.UserDto;
import de.onpier.library.service.UserService;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void getAllUsersBorrowedAtleastOneBook() throws Exception {
        UserDto userDto = getUser();
        when(userService.getAllUsersBorrowedAtleastOneBook()).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(
                get("/api/v1/user/borrowers")
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(1)));
    }


    @Test
    void getAllNonTerminatedUsersCurrentlyBorrowedNothing() throws Exception {
        UserDto userDto = getUser();
        when(userService.getAllNonTerminatedUsersCurrentlyBorrowedNothing()).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(
                get("/api/v1/user/active-non-borrowers")
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(1)));
    }


    @Test
    void getAllUsersBorrowedBookOnGivenDate() throws Exception {
        UserDto userDto = getUser();
        LocalDate borrowedDate = LocalDate.now();
        when(userService.getAllUsersBorrowedBookOnGivenDate(borrowedDate)).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(
                get("/api/v1/user/borrowers-by-date")
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .queryParam("borrowedDate", borrowedDate.toString())
            ).andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(1)));
    }

    private UserDto getUser(){
        return UserDto.builder()
            .name("Test Name")
            .firstName("Test First Name")
            .gender("M")
            .memberSince(LocalDate.now().minusYears(1))
            .memberTill(null)
            .build();
    }
}