package at.rkathofer.camunda_challenge.image.adapter.out.persistence.animalimage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalImageRepository extends JpaRepository<AnimalImageEntity, UUID> {
}
