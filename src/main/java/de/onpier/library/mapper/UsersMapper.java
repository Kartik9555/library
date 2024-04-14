package de.onpier.library.mapper;

import de.onpier.library.domain.Users;
import de.onpier.library.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UsersMapper {
    UserDto toUsersDto(Users user);
}
