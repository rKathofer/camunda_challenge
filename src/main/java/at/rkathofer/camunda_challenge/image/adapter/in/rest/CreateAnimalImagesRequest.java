package at.rkathofer.camunda_challenge.image.adapter.in.rest;

import at.rkathofer.camunda_challenge.image.model.AnimalType;

public record CreateAnimalImagesRequest(
        AnimalType animalType,
        int count
) {
}
