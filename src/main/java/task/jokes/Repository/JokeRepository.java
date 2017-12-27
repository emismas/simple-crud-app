package task.jokes.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import task.jokes.Domain.Joke;

public interface JokeRepository extends PagingAndSortingRepository<Joke, Integer> {

    Page<Joke> findAllByOrderByLikesDescDislikesAsc(Pageable pageable);
}
