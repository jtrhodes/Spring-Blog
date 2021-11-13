package com.codeup.springblog;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupSpringBlogApplication.class)
@AutoConfigureMockMvc
public class PostIntergrationTests {
    private User testUser;

    private HttpSession session;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception{
        testUser = userDao.findByUsername("testUser");
        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }
        session = this.mvc.perform(post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }
    @Test
    public void testSessionNotNull(){
        assertNotNull(session);
    }

    @Test
    public void testPostIndex()throws Exception{
        Post firstPost = postDao.findAll().get(0);
        mvc.perform(get("/posts"))
                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Posts")))
                .andExpect(content().string(containsString(firstPost.getTitle())));
    }
    @Test
    public void testPostShow()throws Exception{
        Post firstPost = postDao.findAll().get(0);
        mvc.perform(get("/posts" + firstPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Show Post")))
                .andExpect(content().string(containsString(firstPost.getTitle())));
    }
    @Test
    public void testPostCreate() throws Exception{
        mvc.perform(post("/posts/create").with(csrf())
                        .session((MockHttpSession) session)
                .param("title", "new created post title")
                .param("body", "This is the new body"))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    public void testPostEdit() throws Exception{
        Post firstPost = postDao.findAll().get(0);
        mvc.perform(post("/posts/" + firstPost.getId() + "/edit").with(csrf())
                .session((MockHttpSession) session)
                .param("title", "This is the edited title")
                .param("body", "This is the edited body"))
                .andExpect(status().is3xxRedirection());

        this.mvc.perform(get("/posts/" + firstPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")))
                .andExpect(content().string(containsString("body")));
    }
    @Test
    public void testDeletePost() throws Exception {
        // Creates a test Ad to be deleted
        this.mvc.perform(
                        post("/posts/create").with(csrf())
                                .session((MockHttpSession) session)
                                .param("title", "ad to be deleted")
                                .param("body", "won't last long"))
                .andExpect(status().is3xxRedirection());

        // Get the recent Ad that matches the title
        Post existingPost = postDao.findByTitle("ad to be deleted");

        // Makes a Post request to /ads/{id}/delete and expect a redirection to the Ads index
        this.mvc.perform(
                        post("/posts/" + existingPost.getId() + "/delete").with(csrf())
                                .session((MockHttpSession) session)
                                .param("id", String.valueOf(existingPost.getId())))
                .andExpect(status().is3xxRedirection());
    }

}
