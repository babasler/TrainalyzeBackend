package backend.backend.rest.workout.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import backend.backend.rest.workout.section.SectionType;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "sectionType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = WarmUpSectionDTO.class, name = "WARMUP"),
    @JsonSubTypes.Type(value = TrainingSectionDTO.class, name = "TRAINING"),
    @JsonSubTypes.Type(value = MobilitySectionDTO.class, name = "MOBILITY"),
    @JsonSubTypes.Type(value = PauseSectionDTO.class, name = "PAUSE")
})
public class BaseSectionDTO {
    private Long id;
    private SectionType sectionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }
}
