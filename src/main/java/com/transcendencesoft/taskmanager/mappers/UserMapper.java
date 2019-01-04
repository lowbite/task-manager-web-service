package com.transcendencesoft.taskmanager.mappers;

import com.transcendencesoft.taskmanager.endpoints.UserEndpoint;
import com.transcendencesoft.taskmanager.enitites.User;
import com.transcendencesoft.taskmanager.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
