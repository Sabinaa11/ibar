package com.sinam.service.impl;

import com.sinam.exception.WrongPermissionException;
import com.sinam.model.Role;
import com.sinam.model.User;
import com.sinam.repository.UserRepository;
import com.sinam.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("Duplicates")
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    private final UserRepository repository;

    @Override
    public List<User> getBlackList(Long currentUserId) {
        Optional<User> user = repository.findById(currentUserId);

        if (user.get().getRole().equals(Role.ADMIN)) {
            return repository.findByActiveIsFalse();
        } else {
            throw new WrongPermissionException("You don't have permission to proceed this operation");
        }

    }

    @Override
    public void activateUser(Long userId, Long currentUserId) {
        Optional<User> user = repository.findById(currentUserId);

        if (user.get().getRole().equals(Role.ADMIN)) {
            repository.changeUserStatus(true, userId);
        } else {
            throw new WrongPermissionException("You don't have permission to proceed this operation");
        }
    }

    @Override
    public User create(User user, Long currentUserId) {
        Optional<User> currentUser = repository.findById(currentUserId);

        if (currentUser.get().getRole().equals(Role.ADMIN)) {
            return repository.save(user);
        } else {
            throw new WrongPermissionException("You don't have permission to proceed this operation");
        }
    }

}