package task.jokes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.jokes.Domain.Joke;
import task.jokes.Repository.JokeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class JokeService {

    @Autowired
    JokeRepository jokeRepository;

    @Autowired
    CategoryService categoryService;

    public Page<Joke> getJokesSortedByLikeDislikeDiff(Pageable pageable) {

        Page<Joke> jokesPage = jokeRepository.findAllByOrderByLikesDescDislikesAsc(pageable);

        return jokesPage;
    }

    public void saveJoke(Joke joke) {
        jokeRepository.save(joke);
    }

    public void likePost(Integer id) {
        Joke joke = jokeRepository.findOne(id);
        joke.setLikes(joke.getLikes() + 1);
        jokeRepository.save(joke);
    }

    public void dislikePost(Integer id) {
        Joke joke = jokeRepository.findOne(id);
        joke.setDislikes(joke.getDislikes() + 1);
        jokeRepository.save(joke);
    }
}
