package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {
    Post POST1 = new Post(1L, "Post 1", "Blah", null);
    Post POST2 = new Post(2L, "Post 2", "BlahBlah", null);
    Post POST3 = new Post(3L, "Post 3", "BlahBlahBlah", null);
    Post POST4 = new Post(4L, "Post 4", "Blah", null);
    Post POST5 = new Post(5L, "Post 5", "BlahBlah", null);
    Post POST6 = new Post(6L, "Post 6", "BlahBlahBlah", null);
    Post POST7 = new Post(7L, "Post 7", "Blah", null);

    @GetMapping
    private List<User> getAll(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "user1", "email1", "password1", LocalDate.now(), User.Role.USER, Arrays.asList(POST1, POST3)));
        users.add(new User(2L, "user2", "email2", "password2", LocalDate.now(), User.Role.USER, Arrays.asList(POST2, POST4)));
        users.add(new User(3L, "user3", "email3", "password3", LocalDate.now(), User.Role.ADMIN, Arrays.asList(POST5, POST6, POST7)));
        return users;
    }

    @GetMapping("{id}")
    private User getById(@PathVariable Long id){
        User user = new User(id, "username", "email", "password", LocalDate.now(), User.Role.USER, Arrays.asList(POST1, POST3));
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
