package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
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

    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    private List<User> getAll(){
        return usersRepository.findAll();
    }

    @GetMapping("{id}")
    private User getById(@PathVariable Long id){
        // TODO: getbyid not working, path parameter?
        return usersRepository.getById(id);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){
        User userToAdd = new User(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
        userToAdd.setCreatedAt(LocalDate.now());
        userToAdd.setRole(User.Role.USER);
        usersRepository.save(userToAdd);
        System.out.println("User created");
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        User userToUpdate = usersRepository.getById(id);
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPassword(updatedUser.getPassword());
        userToUpdate.setCreatedAt(updatedUser.getCreatedAt());
        userToUpdate.setRole(updatedUser.getRole());
        usersRepository.save(userToUpdate);
        System.out.println("User updated");
    }

    @PutMapping("{id}/updatePassword")
    private void updatePassword( @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        // TODO: integrate database into updatePassword
        System.out.printf("Trying to update user password of id: %d, old password: %s, new password: %s\n", id, oldPassword, newPassword);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id){
        usersRepository.deleteById(id);
        System.out.println("User deleted");
    }
}
