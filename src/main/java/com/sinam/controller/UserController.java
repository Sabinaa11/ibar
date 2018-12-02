package com.sinam.controller;

import com.sinam.model.User;
import com.sinam.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("black_list")
    public List<User> getBlackList(@RequestParam Long currentUserId) {
        return service.getBlackList(currentUserId);
    }

    @PutMapping("activate/{id}")
    public void activateUser(@PathVariable Long id, @RequestParam Long currentUserId) {
        service.activateUser(id, currentUserId);
    }

    @PostMapping("save")
    public User create(@RequestBody User user, @RequestParam Long currentUserId) {
        return service.create(user, currentUserId);
    }

}