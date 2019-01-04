package com.transcendencesoft.taskmanager;

import com.transcendencesoft.taskmanager.endpoints.UserEndpoint;
import com.transcendencesoft.taskmanager.enitites.User;
import com.transcendencesoft.taskmanager.mappers.UserMapper;
import com.transcendencesoft.taskmanager.model.AddUserRequest;
import com.transcendencesoft.taskmanager.model.UserDto;
import com.transcendencesoft.taskmanager.services.UserService;
import com.transcendencesoft.taskmanager.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEndpointTests {
    private AddUserRequest addUserRequest;
    @InjectMocks
    private UserEndpoint userEndpoint;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserService userService;


    @Before
    public void init() {
        UserDto addUserDtoEntity = new UserDto();
        addUserDtoEntity.setUsername("Some_User");
        addUserDtoEntity.setPassword("password356");
        addUserDtoEntity.setEmail("sample@email.com");
        addUserRequest = new AddUserRequest();
        addUserRequest.setUser(addUserDtoEntity);

        User user = new User();
        user.setUsername("Some_User");
        user.setPassword("password356");
        user.setEmail("sample@email.com");
        when(userMapper.userDtoToUser(addUserDtoEntity)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(addUserDtoEntity);
        when(userService.addUser(user)).thenReturn(user);
    }

    @Test
    public void testAddUserReturnCorrectUser() {
        assertEquals(userEndpoint.addUser(addUserRequest).getUser(),addUserRequest.getUser());
    }
}
