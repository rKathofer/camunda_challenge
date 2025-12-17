package at.rkathofer.camunda_challenge.image.adapter.out.persistence.animalimage;

import at.rkathofer.camunda_challenge.image.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnimalImageRepository extends JpaRepository<AnimalImageEntity, UUID> {

    Optional<AnimalImageEntity> findTopByAnimalTypeOrderByCreatedAtDesc(AnimalType animalType);

    Optional<AnimalImageEntity> findTopByOrderByCreatedAtDesc();
}
