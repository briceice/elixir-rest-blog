package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    private List<User> getAll(){
        return usersRepository.findAll();
    }

    @GetMapping("{id}")
    private Optional<User> getById(@PathVariable Long id){
        return usersRepository.findById(id);
    }

    @GetMapping("me")
    private User getMyInfo(OAuth2Authentication auth){
        String email = auth.getName();
        return usersRepository.findUserByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser){
        newUser.setCreatedAt(LocalDate.now());
        newUser.setRole(User.Role.USER);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersRepository.save(newUser);
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
        User userToUpdate = usersRepository.getById(id);
        userToUpdate.setPassword(newPassword);
        usersRepository.save(userToUpdate);
        System.out.printf("Trying to update user password of id: %d, old password: %s, new password: %s\n", id, oldPassword, newPassword);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable Long id){
        usersRepository.deleteById(id);
        System.out.println("User deleted");
    }
}
