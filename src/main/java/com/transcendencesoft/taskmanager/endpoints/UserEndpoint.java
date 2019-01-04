package com.transcendencesoft.taskmanager.endpoints;

import com.transcendencesoft.taskmanager.enitites.User;
import com.transcendencesoft.taskmanager.mappers.UserMapper;
import com.transcendencesoft.taskmanager.model.*;
import com.transcendencesoft.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private final UserService userService;

    private UserMapper userMapper;

    @Autowired
    public UserEndpoint(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PayloadRoot(namespace = "http://taskmanager.transcendencesoft.com/user", localPart = "AddUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest addUserRequest) {
        User user = userService.addUser(userMapper.userDtoToUser(addUserRequest.getUser()));

        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setUser(userMapper.userToUserDto(user));
        return addUserResponse;
    }

    @PayloadRoot(namespace = "http://taskmanager.transcendencesoft.com/user", localPart = "GetUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest getUserRequest) {
        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setUser(
                userMapper.userToUserDto(
                        userService.getUser(getUserRequest.getId())
                )
        );
        return getUserResponse;
    }

    @PayloadRoot(namespace = "http://taskmanager.transcendencesoft.com/user", localPart = "UpdateUserRequest")
    @ResponsePayload
    public UpdateUserResponse getUser(@RequestPayload UpdateUserRequest updateUserRequest) {
        User user = userService.addUser(userMapper.userDtoToUser(updateUserRequest.getUser()));

        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setUser(
                userMapper.userToUserDto(
                        userService.updateUser(user)
                )
        );
        return updateUserResponse;
    }

    @PayloadRoot(namespace = "http://taskmanager.transcendencesoft.com/user", localPart = "RemoveUserRequest")
    @ResponsePayload
    public void getUser(@RequestPayload RemoveUserRequest removeUserRequest) {
        userService.deleteUser(userMapper.userDtoToUser(removeUserRequest.getUser()));
    }
}
