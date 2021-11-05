package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postsDao;

    public PostController(PostRepository postDao) {
        this.postsDao = postDao;
    }
//    @GetMapping("/posts")
//    public String index(Model model) {
//        model.addAttribute("posts", postDao.findAll());
//        return "posts/index";
//    }
    @GetMapping("/posts")

    public String index(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }
    @GetMapping("/posts/{id}/edit")
    public String editView(@PathVariable long id, Model model){
        model.addAttribute("post",postsDao.getById(id));
        return "posts/edit";
    }
@PostMapping("/posts/{id}/edit")
    public String updatPost(@PathVariable long id, @RequestParam String title,@RequestParam String body){
        Post post = postsDao.getById(id);
        post.setBody(title);
        post.setTitle(body);
        postsDao.save(post);
        return "redirect:/posts";
}
    @PostMapping("/posts/{id}/delete")
public String deletepost(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "Here is the post " + id;
    }

    @GetMapping("/posts/show")
    @ResponseBody
    public String create() {
        return "Here is the post create form...";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert() {
        return "New post saved...";
    }

}
