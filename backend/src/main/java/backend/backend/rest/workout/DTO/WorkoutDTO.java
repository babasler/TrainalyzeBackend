package backend.backend.rest.workout.DTO;

import java.util.List;

public class WorkoutDTO {
    private Long id;
    private String name;
    private List<BaseSectionDTO> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<BaseSectionDTO> getSections() {
        return sections;
    }
    public void setSections(List<BaseSectionDTO> sections) {
        this.sections = sections;
    }
}
