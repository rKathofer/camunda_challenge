package at.rkathofer.camunda_challenge.image.adapter.out.persistence.animalimage;

import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.store.AnimalImageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

        return new AnimalImage(
                saved.getId(),
                saved.getData(),
                saved.getContentType(),
                saved.getAnimalType(),
                saved.getCreatedAt()
        );
    }
}
