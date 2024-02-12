package com.kingmartinien.springbootblogapi.data;

import com.github.javafaker.Faker;
import com.kingmartinien.springbootblogapi.entity.Post;
import com.kingmartinien.springbootblogapi.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataLoader implements CommandLineRunner {
    private final PostRepository postRepository;
    private final Faker faker;

    public DataLoader(PostRepository postRepository, Faker faker) {
        this.postRepository = postRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) {
        System.out.println("COMMANDLINE RUNNER");

//        Create 50 rows of post in the database
        List<Post> posts = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new Post(
                        null,
                        faker.bothify(faker.book().title() + "????"),
                        faker.bothify(faker.lorem().sentence() + "????"),
                        faker.lorem().paragraph(2)

                ))
                .toList();
//        postRepository.saveAll(posts);

    }
}
