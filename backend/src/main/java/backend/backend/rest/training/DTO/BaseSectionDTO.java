package backend.backend.rest.training.DTO;
import backend.backend.rest.training.section.SectionType;

public class BaseSectionDTO {
    private SectionType type;

    public BaseSectionDTO(SectionType type) {
        this.type = type;
    }

    public SectionType getType() {
        return type;
    }

    public void setType(SectionType type) {
        this.type = type;
    }
}
