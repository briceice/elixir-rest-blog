package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final EmailService emailService;


    public PostsController(PostsRepository postsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository, EmailService emailService) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.emailService = emailService;
    }

    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Post> getById(@PathVariable Long id){
        return postsRepository.findById(id);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth){
        String email = auth.getName();
        User user = usersRepository.findUserByEmail(email);
        newPost.setAuthor(user);
        List<Category> categories = new ArrayList<>();
        categories.add(categoriesRepository.findCategoryByName("food"));
        categories.add(categoriesRepository.findCategoryByName("music"));
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        System.out.println("Post created");
        emailService.prepareAndSend(newPost, "New Post", "A new post was created");
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post updatedPost){
        Post postToUpdate = postsRepository.getById(id);
        postToUpdate.setTitle(updatedPost.getTitle());
        postToUpdate.setContent(updatedPost.getContent());
        postsRepository.save(postToUpdate);
        System.out.println("Post updated");
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id){
        postsRepository.deleteById(id);
        System.out.println("Post deleted");
    }

    @GetMapping("searchByTitle")
    private List<Post> searchByTitle(@RequestParam String term){
        return postsRepository.searchByTitleLike(term);
    }
}
