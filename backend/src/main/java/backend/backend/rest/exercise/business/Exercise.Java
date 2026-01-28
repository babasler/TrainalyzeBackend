package backend.backend.rest.exercise.business;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercise {
    private Long id;
    private String name;
    private String[] muscles;
    private float maxWeight;
    private int maxRepetitions;
}
