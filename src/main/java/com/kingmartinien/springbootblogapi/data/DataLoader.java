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
    public void run(String... args) throws Exception {
        System.out.println("COMMANDLINE RUNNER");

//        Create 50 rows of post in the database
        List<Post> posts = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new Post(
                        null,
                        // faker post title ( must be unique )
                        faker.bothify("Post Title ????"),
                        // faker post description
                        faker.bothify("Post Description ????").concat(faker.letterify("????")),
                        // faker post content ( must be a paragraph with 2 or more sentences)
                        faker.lorem().paragraph(2)

                ))
                .toList();
        postRepository.saveAll(posts);

    }
}
