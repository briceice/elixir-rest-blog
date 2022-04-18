package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    @GetMapping
    private List<User> getAll(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "user1", "email1", "password1", LocalDate.now(), User.Role.USER));
        users.add(new User(2, "user2", "email2", "password2", LocalDate.now(), User.Role.USER));
        users.add(new User(3, "user3", "email3", "password3", LocalDate.now(), User.Role.ADMIN));
        return users;
    }

    @GetMapping("{id}")
    private User getById(@PathVariable Long id){
        return new User(id, "username", "email", "password", LocalDate.now(), User.Role.USER);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){
        System.out.println("Ready to add user: " + newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        System.out.println("Updated user: " + updatedUser + " id: " + id);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id){
        System.out.println("Deleted user: " + id);
    }
}
