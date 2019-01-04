package com.transcendencesoft.taskmanager.services;

import com.transcendencesoft.taskmanager.enitites.User;

public interface UserService {
    User addUser(User user);

    void deleteUser(User user);

    User updateUser(User user);

    User getUser(long id);
}
