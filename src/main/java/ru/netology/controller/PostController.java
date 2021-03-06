package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Controller
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final List<Post> data = service.all();
        final Gson gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        final Gson gson = new Gson();
        try {
            final Post data = service.getById(id);
            response.setContentType(APPLICATION_JSON);
            response.getWriter().print(gson.toJson(data));
        } catch (NotFoundException e) {
            final int status = HttpServletResponse.SC_NOT_FOUND;
            response.setStatus(status);
            response.setContentType("text/plain");
            response.getWriter().print("ERROR " + status + "\nPost " + id + " not found");
        }
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final Gson gson = new Gson();
        final Post post = gson.fromJson(body, Post.class);
        final Post data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id) {
        service.removeById(id);
    }
}