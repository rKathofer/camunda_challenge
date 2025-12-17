package at.rkathofer.camunda_challenge.image.store;

import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.model.AnimalType;

import java.util.Optional;

public interface AnimalImageStore {

    AnimalImage save(AnimalImage image);

    Optional<AnimalImage> findLatestByType(AnimalType animalType);

    Optional<AnimalImage> findLatest();
}
