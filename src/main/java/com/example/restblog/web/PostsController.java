package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    private List<Post> getAll() {
        return postsRepository.findAll();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id){
        return postsRepository.getById(id);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost){
        Post postToAdd = new Post(newPost.getTitle(), newPost.getContent());
        postsRepository.save(postToAdd);
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
