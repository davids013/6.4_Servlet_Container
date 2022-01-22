package ru.netology.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.configuration.JavaConfiguration;
import ru.netology.controller.PostController;
import ru.netology.exception.NotFoundException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private final String POSTS_PATH = "/api/posts";
    private PostController controller;

    @Override
    public void init() {
//        final ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        final ApplicationContext context = new AnnotationConfigApplicationContext("ru.netology");
        controller = (PostController) context.getBean("postController");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final String path = req.getRequestURI();
            final String method = req.getMethod();
            // primitive routing
            if ((method.equals("GET") || method.equals("POST")) && path.equals(POSTS_PATH)) {
                if (method.equals("GET")) {
                    controller.all(resp);
                } else {
                    controller.save(req.getReader(), resp);
                }
                return;
            }
            if ((method.equals("GET") || method.equals("DELETE")) && path.matches(POSTS_PATH + "/\\d+")) {
                // easy way
                final long id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                if (method.equals("GET")) {
                    controller.getById(id, resp);
                } else {
                    controller.removeById(id);
                }
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (NotFoundException | IOException e) {
            e.printStackTrace();
            final int statusCode = HttpServletResponse.SC_NOT_FOUND;
            resp.setStatus(statusCode);
            resp.setContentType("text/plain");
            try {
                resp.getWriter().print("ERROR " + statusCode + "\n" + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}