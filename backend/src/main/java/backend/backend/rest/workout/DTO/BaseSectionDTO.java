package backend.backend.rest.workout.DTO;

import backend.backend.rest.workout.section.SectionType;

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
