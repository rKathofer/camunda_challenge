package at.rkathofer.camunda_challenge.image.adapter.in.rest;

import at.rkathofer.camunda_challenge.image.model.AnimalType;
import at.rkathofer.camunda_challenge.image.usecase.CreateAnimalImagesUseCase;
import at.rkathofer.camunda_challenge.image.usecase.FindLatestAnimalImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animal-images")
@RequiredArgsConstructor
public class AnimalImageController {

    private final CreateAnimalImagesUseCase createAnimalImagesUseCase;
    private final FindLatestAnimalImageUseCase findLatestAnimalImageUseCase;

    @PostMapping
    public ResponseEntity<CreateAnimalImagesResponse> createAnimalImages(@RequestBody CreateAnimalImagesRequest request) {
        List<UUID> imageIds = createAnimalImagesUseCase.execute(request.animalType(), request.count());

        return ResponseEntity.ok(new CreateAnimalImagesResponse(imageIds));
    }

    @GetMapping("/latest")
    public ResponseEntity<byte[]> getLatest(@RequestParam(required = false) AnimalType animalType) {
        return findLatestAnimalImageUseCase.execute(animalType)
                .map(image -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(image.contentType()))
                        .body(image.data()))
                .orElse(ResponseEntity.notFound().build());
    }
}
