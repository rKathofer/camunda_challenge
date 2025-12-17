package at.rkathofer.camunda_challenge.image.adapter.out.persistence.animalimage;

import at.rkathofer.camunda_challenge.image.model.AnimalType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "animal_images")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class AnimalImageEntity {

    @Id
    private UUID id;

    private byte[] data;

    private String contentType;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @CreatedDate
    private LocalDateTime createdAt;

    public AnimalImageEntity(UUID id, byte[] data, String contentType, AnimalType animalType) {
        this.id = id;
        this.data = data;
        this.contentType = contentType;
        this.animalType = animalType;
    }

}
