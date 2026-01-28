package backend.backend.rest.exercise.business;

import java.time.Instant;
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
        User user = userService.findByUsername(username);
        ExerciseEntity existingEntity = exerciseRepository
                .findByIdAndUserUsername(exercise.getId(), username)
                .orElse(null);
        if (existingEntity == null) {
            return null;
        }

        ExerciseEntity newData = mapper.businessToPersistence(exercise, user);
        existingEntity = updateEntity(existingEntity, newData);
        ExerciseEntity saved = exerciseRepository.save(existingEntity);
        return entityToBiz.entityToBusiness(saved);
    }

    public void deleteExerciseForCurrentUser(Long id) {
        String username = userService.getCurrentUsername();
        exerciseRepository.findById(id)
                .filter(e -> e.getUser() != null && e.getUser().getUsername().equals(username))
                .ifPresent(exerciseRepository::delete);
    }

    private ExerciseEntity updateEntity(ExerciseEntity toUpdate, ExerciseEntity newData) {
        toUpdate.setName(newData.getName());
        toUpdate.setWeight(newData.getWeight());
        toUpdate.setRepetitions(newData.getRepetitions());
        toUpdate.setMuscles(newData.getMuscles());
        toUpdate.setUpdatedAt(Instant.now());
        return toUpdate;
    }
}