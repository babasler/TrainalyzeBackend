package backend.backend.rest.exercise.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.backend.rest.exercise.mapper.ExerciseToExerciseEntityMapper;
import backend.backend.rest.exercise.mapper.ExerciseEntityToExerciseMapper;
import backend.backend.rest.exercise.persistence.ExerciseEntity;
import backend.backend.rest.exercise.persistence.ExerciseRepository;
import backend.backend.rest.user.User;
import backend.backend.rest.user.UserService;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserService userService;

    private final ExerciseEntityToExerciseMapper entityToBiz = new ExerciseEntityToExerciseMapper();

    private final ExerciseToExerciseEntityMapper mapper = new ExerciseToExerciseEntityMapper();

    public List<Exercise> getCurrentUserExercises() {
        String username = userService.getCurrentUsername();
        List<ExerciseEntity> entities = exerciseRepository.findByUserUsername(username);
        return entities.stream().map(entityToBiz::entityToBusiness).collect(Collectors.toList());
    }

    public Exercise getExerciseByIdForCurrentUser(Long id) {
        String username = userService.getCurrentUsername();
        return exerciseRepository.findById(id)
                .filter(e -> e.getUser() != null && e.getUser().getUsername().equals(username))
                .map(entityToBiz::entityToBusiness)
                .orElse(null);
    }

    public Exercise createExerciseForCurrentUser(Exercise exercise) {
        String username = userService.getCurrentUsername();
        User user = userService.findByUsername(username);

        ExerciseEntity entity = mapper.businessToPersistence(exercise, user);
        ExerciseEntity saved = exerciseRepository.save(entity);
        return entityToBiz.entityToBusiness(saved);
    }

    public Exercise updateExerciseForCurrentUser(Exercise exercise) {
        String username = userService.getCurrentUsername();

        if (exercise == null || exercise.getId() == null) {
            // Cannot update without a valid ID
            return null;
        }

        return exerciseRepository.findById(exercise.getId())
                .filter(existing -> existing.getUser() != null
                        && existing.getUser().getUsername().equals(username))
                .map(existing -> {
                    // Reuse mapping logic to populate fields from the business object
                    User user = existing.getUser();
                    ExerciseEntity updated = mapper.businessToPersistence(exercise, user);

                    // Preserve persistence-related fields and timestamps
                    updated.setId(existing.getId());
                    updated.setCreatedAt(existing.getCreatedAt());
                    updated.touchUpdatedAt();

                    ExerciseEntity saved = exerciseRepository.save(updated);
                    return entityToBiz.entityToBusiness(saved);
                })
                .orElse(null);
    }

    public void deleteExerciseForCurrentUser(Long id) {
        String username = userService.getCurrentUsername();
        exerciseRepository.findById(id)
                .filter(e -> e.getUser() != null && e.getUser().getUsername().equals(username))
                .ifPresent(exerciseRepository::delete);
    }

}