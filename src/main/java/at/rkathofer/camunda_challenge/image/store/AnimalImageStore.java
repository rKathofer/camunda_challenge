package at.rkathofer.camunda_challenge.image.store;

import at.rkathofer.camunda_challenge.image.model.AnimalImage;

public interface AnimalImageStore {

    AnimalImage save(AnimalImage image);
}
