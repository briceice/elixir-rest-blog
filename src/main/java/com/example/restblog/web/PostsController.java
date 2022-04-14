package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "Blah"));
        posts.add(new Post(2L, "Post 2", "Blah blah"));
        posts.add(new Post(3L, "Post 3", "Blah blah blah"));
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id){
        return new Post(id, "Post " + id, "Blah blah blah");
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost){
        System.out.println("Ready to add post: " + newPost);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable Long id, @RequestBody Post updatedPost){
        System.out.println("Updated post: " + id);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable Long id){
        System.out.println("Deleted post: " + id);
    }
}
