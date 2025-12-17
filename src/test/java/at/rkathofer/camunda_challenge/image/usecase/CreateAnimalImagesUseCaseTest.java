package at.rkathofer.camunda_challenge.image.usecase;

import at.rkathofer.camunda_challenge.image.client.ImageClient;
import at.rkathofer.camunda_challenge.image.model.AnimalImage;
import at.rkathofer.camunda_challenge.image.model.AnimalType;
import at.rkathofer.camunda_challenge.image.model.Image;
import at.rkathofer.camunda_challenge.image.store.AnimalImageStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAnimalImagesUseCaseTest {

    @Mock
    private ImageClient catImageClient;
    @Mock
    private ImageClient dogImageClient;
    @Mock
    private ImageClient bearImageClient;
    @Mock
    private AnimalImageStore animalImageStore;

    @Captor
    private ArgumentCaptor<AnimalImage> animalImageCaptor;

    private CreateAnimalImagesUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateAnimalImagesUseCase(
                catImageClient,
                dogImageClient,
                bearImageClient,
                animalImageStore
        );
    }

    @ParameterizedTest
    @EnumSource(AnimalType.class)
    void execute_selectsCorrectClientForAnimalType(AnimalType animalType) {
        // Arrange
        ImageClient expectedClient = getClientForType(animalType);
        Image image = new Image(new byte[]{1, 2, 3}, "image/jpeg", "source");

        when(expectedClient.fetchRandomImage()).thenReturn(image);
        when(animalImageStore.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        useCase.execute(animalType, 1);

        // Assert
        verify(expectedClient).fetchRandomImage();
    }

    @Test
    void execute_withMultipleCount_fetchesAndSavesCorrectNumberOfImages() {
        // Arrange
        int count = 3;
        Image image = new Image(new byte[]{1, 2, 3}, "image/jpeg", "cataas.com");

        when(catImageClient.fetchRandomImage()).thenReturn(image);
        when(animalImageStore.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        useCase.execute(AnimalType.CAT, count);

        // Assert
        verify(catImageClient, times(count)).fetchRandomImage();
        verify(animalImageStore, times(count)).save(any(AnimalImage.class));
    }

    @Test
    void execute_returnsUniqueIdsForEachSavedImage() {
        // Arrange
        int count = 3;
        Image image = new Image(new byte[]{1, 2, 3}, "image/jpeg", "cataas.com");

        when(catImageClient.fetchRandomImage()).thenReturn(image);
        when(animalImageStore.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        List<UUID> result = useCase.execute(AnimalType.CAT, count);

        // Assert
        assertThat(result)
                .hasSize(count)
                .doesNotHaveDuplicates()
                .allSatisfy(id -> assertThat(id).isNotNull());
    }

    @Test
    void execute_createsAnimalImageWithCorrectProperties() {
        // Arrange
        byte[] imageData = new byte[]{1, 2, 3, 4, 5};
        String contentType = "image/jpeg";
        Image image = new Image(imageData, contentType, "cataas.com");

        when(catImageClient.fetchRandomImage()).thenReturn(image);
        when(animalImageStore.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        useCase.execute(AnimalType.CAT, 1);

        // Assert
        verify(animalImageStore).save(animalImageCaptor.capture());
        AnimalImage capturedImage = animalImageCaptor.getValue();

        assertThat(capturedImage.id()).isNotNull();
        assertThat(capturedImage.data()).isEqualTo(imageData);
        assertThat(capturedImage.contentType()).isEqualTo(contentType);
        assertThat(capturedImage.animalType()).isEqualTo(AnimalType.CAT);
        assertThat(capturedImage.createdAt()).isNull();
    }

    @Test
    void execute_withZeroCount_returnsEmptyListWithoutInteractions() {
        // Arrange
        int count = 0;

        // Act
        List<UUID> result = useCase.execute(AnimalType.CAT, count);

        // Assert
        assertThat(result).isEmpty();
        verifyNoInteractions(catImageClient, dogImageClient, bearImageClient, animalImageStore);
    }

    // Could be improved to avoid logic in tests
    private ImageClient getClientForType(AnimalType animalType) {
        return switch (animalType) {
            case CAT -> catImageClient;
            case DOG -> dogImageClient;
            case BEAR -> bearImageClient;
        };
    }
}