package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeService) {
        this.recipeRepository = recipeService;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        recipeRepository.findById(recipeId)
                .map(r -> {
                    try {
                        byte[] bytes = file.getBytes();
                        Byte[] byteObjects = new Byte[bytes.length];
                        for (int i = 0; i < bytes.length; i++) {
                            byteObjects[i] = bytes[i];
                        }

                        r.setImage(byteObjects);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return r;
                }).publish(r -> recipeRepository.save(Objects.requireNonNull(r.block())));

        return Mono.empty();
    }
}
