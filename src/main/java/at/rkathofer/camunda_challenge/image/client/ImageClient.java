package at.rkathofer.camunda_challenge.image.client;

import at.rkathofer.camunda_challenge.image.model.Image;

public interface ImageClient {

    Image fetchRandomImage();
}
