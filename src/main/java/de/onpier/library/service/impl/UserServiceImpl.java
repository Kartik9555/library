package de.onpier.library.service.impl;

import de.onpier.library.mapper.UsersMapper;
import de.onpier.library.model.dto.UserDto;
import de.onpier.library.repository.UserRepository;
import de.onpier.library.service.UserService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UsersMapper usersMapper;


    @Override
    public List<UserDto> getAllUsersBorrowedAtleastOneBook() {
        return userRepository.findAllByBorrowedBooksIsNotEmpty()
            .stream()
            .map(usersMapper::toUsersDto)
            .toList();
    }


    @Override
    public List<UserDto> getAllNonTerminatedUsersCurrentlyBorrowedNothing() {
        return userRepository.getAllNonTerminatedUsersNotCurrentlyBorrowedAnything()
            .stream()
            .map(usersMapper::toUsersDto)
            .toList();
    }


    @Override
    public List<UserDto> getAllUsersBorrowedBookOnGivenDate(LocalDate borrowedDate) {
        if (borrowedDate == null) {
            throw new IllegalArgumentException("borrowedDate must not be null");
        }
        return userRepository.findAllUsersByBorrowedBooksOnGivenDate(borrowedDate)
            .stream()
            .map(usersMapper::toUsersDto)
            .toList();
    }
}
