package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
        users.add(new User(1L, "user1", "email1", "password1", LocalDate.now(), User.Role.USER, null));
        users.add(new User(2L, "user2", "email2", "password2", LocalDate.now(), User.Role.USER, null));
        users.add(new User(3L, "user3", "email3", "password3", LocalDate.now(), User.Role.ADMIN, null));
        return users;
    }

    @GetMapping("{id}")
    private User getById(@PathVariable Long id){
        User user = new User(id, "username", "email", "password", LocalDate.now(), User.Role.USER, null);
        return user;
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){
        System.out.println("Ready to add user: " + newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        System.out.println("Updated user: " + updatedUser + " id: " + id);
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword( @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        System.out.printf("Trying to update user password of id: %d, old password: %s, new password: %s\n", id, oldPassword, newPassword);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id){
        System.out.println("Deleted user: " + id);
    }
}
