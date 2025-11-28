package backend.backend.rest.workout.section;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type" // oder z.B. "@type"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = TrainingSection.class, name = "TRAINING"),
  @JsonSubTypes.Type(value = WarumUpSection.class, name = "WARMUP"),
  @JsonSubTypes.Type(value = PauseSection.class, name = "PAUSE"),
  @JsonSubTypes.Type(value = MobilitySection.class, name = "MOBILITY"),
  // weitere Section-Typen hier ergänzen
})

public abstract class BaseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private SectionType type;

    public BaseSection() {
        // Default constructor for JPA
    }

    public BaseSection(SectionType type) {
        this.type = type;
    }
    public SectionType getType() {
        return type;
    }
    public void setType(SectionType type) {
        this.type = type;
    }
    
}
