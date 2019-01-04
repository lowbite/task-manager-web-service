package com.transcendencesoft.taskmanager.services.impl;

import com.transcendencesoft.taskmanager.enitites.User;
import com.transcendencesoft.taskmanager.exception.InvalidInputDataException;
import com.transcendencesoft.taskmanager.repositories.UserRepository;
import com.transcendencesoft.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User addUser(User user) {
        Optional<User> userCandidate = userRepository.findUserByUsername(user.getUsername());
        if (userCandidate.isPresent()) {
            throw new InvalidInputDataException("Such user already exist!");
        }

        userCandidate = userRepository.findUserByEmail(user.getEmail());

        if (userCandidate.isPresent()) {
            throw new InvalidInputDataException("Such email already in use!");
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void deleteUser(User user) {
        User userCandidate = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new InvalidInputDataException("There is no such user!"));
        userRepository.delete(userCandidate);
    }

    @Override
    public User updateUser(User user) {
        User userCandidate = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new InvalidInputDataException("There is no such user!"));
        user.setId(userCandidate.getId());
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidInputDataException("There is no such user!"));
    }
}
