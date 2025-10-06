package backend.backend.rest.training.DTO;

import java.util.ArrayList;
import java.util.List;

import backend.backend.rest.training.section.SectionType;

public class TrainingSectionDTO extends BaseSectionDTO {
   private List<ExerciseSectionDTO> exercises;

   public TrainingSectionDTO(List<ExerciseSectionDTO> exercises) {
       super(SectionType.TRAINING);
       this.exercises = exercises;
   }

   public TrainingSectionDTO() {
       super(SectionType.TRAINING);
       this.exercises = new ArrayList<>();
   }

   public List<ExerciseSectionDTO> getExercises() {
       return exercises;
   }

   public void setExercises(List<ExerciseSectionDTO> exercises) {
       this.exercises = exercises;
   }
   public void addExercise(ExerciseSectionDTO exercise) {
       this.exercises.add(exercise);
   }
}
