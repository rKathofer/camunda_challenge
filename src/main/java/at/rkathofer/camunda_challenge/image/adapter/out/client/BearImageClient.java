package at.rkathofer.camunda_challenge.image.adapter.out.client;

import at.rkathofer.camunda_challenge.image.client.ImageClient;
import at.rkathofer.camunda_challenge.image.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class BearImageClient implements ImageClient {
    private static final String BEAR_API_URL = "https://placebear.com/500/500";

    private final RestClient restClient;

    @Override
    public Image fetchRandomImage() {
        ResponseEntity<byte[]> response = restClient.get()
                .uri(BEAR_API_URL)
                .retrieve()
                .toEntity(byte[].class);

        String contentType = response.getHeaders().getContentType() != null
                ? response.getHeaders().getContentType().toString()
                : MediaType.IMAGE_JPEG_VALUE;

        return new Image(response.getBody(), contentType, "placebear.com");
    }
}
