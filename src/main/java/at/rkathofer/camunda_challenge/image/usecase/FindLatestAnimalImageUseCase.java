package at.rkathofer.camunda_challenge.image.usecase;

import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.model.AnimalType;
import at.rkathofer.camunda_challenge.image.store.AnimalImageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindLatestAnimalImageUseCase {

    private final AnimalImageStore animalImageStore;

    public Optional<AnimalImage> execute(AnimalType animalType) {
        if (animalType == null) {
            return animalImageStore.findLatest();
        }
        return animalImageStore.findLatestByType(animalType);
    }
}
