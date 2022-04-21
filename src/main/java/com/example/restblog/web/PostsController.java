package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    public PostsController(PostsRepository postsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
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
    private void createPost(@RequestBody Post newPost){
        newPost.setAuthor(usersRepository.getById(1L));
        postsRepository.save(newPost);
        System.out.println("Post created");
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
}
