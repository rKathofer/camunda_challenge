package at.rkathofer.camunda_challenge.image.usecase;

import at.rkathofer.camunda_challenge.image.client.ImageClient;
import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.model.AnimalType;
import at.rkathofer.camunda_challenge.image.model.Image;
import at.rkathofer.camunda_challenge.image.store.AnimalImageStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreateAnimalImagesUseCase {

    private final ImageClient catImageClient;
    private final ImageClient dogImageClient;
    private final ImageClient bearImageClient;
    private final AnimalImageStore animalImageStore;

    public CreateAnimalImagesUseCase(
            @Qualifier("catImageClient") ImageClient catImageClient,
            @Qualifier("dogImageClient") ImageClient dogImageClient,
            @Qualifier("bearImageClient") ImageClient bearImageClient,
            AnimalImageStore animalImageStore
    ) {
        this.catImageClient = catImageClient;
        this.dogImageClient = dogImageClient;
        this.bearImageClient = bearImageClient;
        this.animalImageStore = animalImageStore;
    }

    public List<UUID> execute(AnimalType animalType, int count) {
        ImageClient client = selectClient(animalType);
        List<UUID> savedIds = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Image image = client.fetchRandomImage();

            AnimalImage animalImage = new AnimalImage(
                    UUID.randomUUID(),
                    image.data(),
                    image.contentType(),
                    animalType,
                    null
            );

            AnimalImage saved = animalImageStore.save(animalImage);
            savedIds.add(saved.id());
        }

        return savedIds;
    }

    private ImageClient selectClient(AnimalType animalType) {
        return switch (animalType) {
            case CAT -> catImageClient;
            case DOG -> dogImageClient;
            case BEAR -> bearImageClient;
        };
    }
}
