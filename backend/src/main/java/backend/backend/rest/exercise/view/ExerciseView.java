package backend.backend.rest.exercise.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExerciseView {
    private Long id;
    private String name;
    private float weight;
    private int repetitions;
    private String[] muscleGroups;
}
