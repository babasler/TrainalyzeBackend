package backend.backend.rest.workout.DTO;

import java.util.List;

public class TrainingSectionDTO {
    private Long id;
    private List<ExerciseSectionDTO> exerciseSections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ExerciseSectionDTO> getExerciseSections() {
        return exerciseSections;
    }

    public void setExerciseSections(List<ExerciseSectionDTO> exerciseSections) {
        this.exerciseSections = exerciseSections;
    }
}
