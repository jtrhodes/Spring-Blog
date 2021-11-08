package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final EmailService emailService;
    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
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
    public String updatePost(@ModelAttribute Post post){
        Post editedPost = postsDao.getById(post.getId());
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        postsDao.save(editedPost);
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

    @GetMapping("/posts/create")
    public String create(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        User user = usersDao.getById(1L); // just use the first user in the db
        post.setUser(user);
        emailService.prepareAndSend(post, "is this going to work", "yes it will");
        postsDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String insert(@RequestParam String title,@RequestParam String body,@RequestParam List<String> urls) {
//        List<PostImage> images = new ArrayList<>();
//        for(String url : urls){
//            PostImage postImage(url);
//            images.add(postImage);
//        }
//        Post post = new Post(title, body,images);
//        postsDao.save(post)
//
//        return "redirect:/posts";
//    }

}
