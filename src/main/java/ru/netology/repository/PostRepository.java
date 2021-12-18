package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {
  private final List<Post> posts = new CopyOnWriteArrayList<>();
  private long counter;

  public List<Post> all() {
    if (posts.isEmpty())
      return new ArrayList<>();
    return new ArrayList<>(posts);
  }

  public Optional<Post> getById(long id) {
    return posts.stream().filter(p -> p.getId() == id).findFirst();
  }

  public Post save(Post post) {
    final long id = post.getId();
    if (id == 0) {
      final Post newPost = new Post(++counter, post.getContent());
      posts.add(newPost);
      return newPost;
    } else {
      for (Post p : posts) {
        if (p.getId() == id) {
          posts.remove(p);
          posts.add(post);
          return post;
        }
      }
    }
    throw new NotFoundException("Can't override. There is no post with id " + id);
  }

  public void removeById(long id) {
    posts.removeIf(p -> p.getId() == id);
  }
}
