package at.rkathofer.camunda_challenge.image.adapter.out.persistence.animalimage;

import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.model.AnimalType;
import at.rkathofer.camunda_challenge.image.store.AnimalImageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAnimalImageStore implements AnimalImageStore {

    private final AnimalImageRepository repository;

    @Override
    public AnimalImage save(AnimalImage image) {
        AnimalImageEntity entity = new AnimalImageEntity(
                image.id(),
                image.data(),
                image.contentType(),
                image.animalType()
        );

        AnimalImageEntity saved = repository.save(entity);

        return toAnimalImage(saved);
    }

    @Override
    public Optional<AnimalImage> findLatestByType(AnimalType animalType) {
        return repository.findTopByAnimalTypeOrderByCreatedAtDesc(animalType)
                .map(this::toAnimalImage);
    }

    @Override
    public Optional<AnimalImage> findLatest() {
        return repository.findTopByOrderByCreatedAtDesc()
                .map(this::toAnimalImage);
    }

    private AnimalImage toAnimalImage(AnimalImageEntity entity) {
        return new AnimalImage(
                entity.getId(),
                entity.getData(),
                entity.getContentType(),
                entity.getAnimalType(),
                entity.getCreatedAt()
        );
    }
}
