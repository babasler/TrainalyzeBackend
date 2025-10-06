package backend.backend.rest.training.DTO;

import backend.backend.rest.training.section.SectionType;

public class MobilitySectionDTO extends BaseSectionDTO {
    private String mobilityExercise;
    private int sets;
    private int reps;

    public MobilitySectionDTO(){
        super(SectionType.MOBILITY);
    }

    public String getMobilityExercise() {
        return mobilityExercise;
    }

    public void setMobilityExercise(String mobilityExercise) {
        this.mobilityExercise = mobilityExercise;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
