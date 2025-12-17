package at.rkathofer.camunda_challenge.image.adapter.in.rest;

import at.rkathofer.camunda_challenge.image.usecase.CreateAnimalImagesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animal-images")
@RequiredArgsConstructor
public class AnimalImageController {

    private final CreateAnimalImagesUseCase createAnimalImagesUseCase;

    @PostMapping
    public ResponseEntity<CreateAnimalImagesResponse> createAnimalImages(@RequestBody CreateAnimalImagesRequest request) {
        List<UUID> imageIds = createAnimalImagesUseCase.execute(request.animalType(), request.count());

        return ResponseEntity.ok(new CreateAnimalImagesResponse(imageIds));
    }
}
