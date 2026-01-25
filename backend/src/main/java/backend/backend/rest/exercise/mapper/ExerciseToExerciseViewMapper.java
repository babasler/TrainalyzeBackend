package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.view.ExerciseView;

public class ExerciseToExerciseViewMapper {
    public ExerciseView businessToView(Exercise exercise) {
        ExerciseView view = new ExerciseView();
        view.setId(exercise.getId());
        view.setName(exercise.getName());
        view.setWeight(exercise.getWeight());
        view.setRepetitions(exercise.getRepetitions());
        view.setMuscleGroups(exercise.getMuscles());
        return view;
    }
}
