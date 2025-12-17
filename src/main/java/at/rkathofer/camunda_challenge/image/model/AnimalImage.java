package at.rkathofer.camunda_challenge.image.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnimalImage(
        UUID id,
        byte[] data,
        String contentType,
        AnimalType animalType,
        LocalDateTime createdAt
) {
}
