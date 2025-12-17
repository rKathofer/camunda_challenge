package at.rkathofer.camunda_challenge.image.adapter.in.rest;

import java.util.List;
import java.util.UUID;

public record CreateAnimalImagesResponse(
        List<UUID> imageIds
) {
}
