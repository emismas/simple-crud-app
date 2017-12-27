package task.jokes.Repository;

import org.springframework.data.repository.CrudRepository;
import task.jokes.Domain.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findAll();

}
