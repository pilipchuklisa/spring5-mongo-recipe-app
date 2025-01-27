package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
