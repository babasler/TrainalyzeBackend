package backend.backend.rest.training.DTO;
import java.util.ArrayList;
import java.util.List;

public class TrainingDTO {
    private String trainingName;

    private List<BaseSectionDTO> sections;

    public TrainingDTO() {
        this.trainingName = "";
        this.sections = new ArrayList<>();
    }


    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public List<BaseSectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<BaseSectionDTO> sections) {
        this.sections = sections;
    }
    public void addSection(BaseSectionDTO section) {
        this.sections.add(section);
    }
}
