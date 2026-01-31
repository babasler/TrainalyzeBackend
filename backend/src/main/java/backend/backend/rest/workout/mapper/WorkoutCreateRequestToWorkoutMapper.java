package backend.backend.rest.workout.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import backend.backend.rest.workout.business.ExercisePlan;
import backend.backend.rest.workout.business.ExerciseRef;
import backend.backend.rest.workout.business.ExerciseSet;
import backend.backend.rest.workout.business.Pause;
import backend.backend.rest.workout.business.Section;
import backend.backend.rest.workout.business.SectionType;
import backend.backend.rest.workout.business.SupersetPlan;
import backend.backend.rest.workout.business.TrainingElement;
import backend.backend.rest.workout.business.TrainingSection;
import backend.backend.rest.workout.business.WarmupSection;
import backend.backend.rest.workout.business.Workout;
import backend.backend.rest.workout.view.WorkoutCreateRequest;
import backend.backend.rest.workout.view.DTO.ExercisePlanDTO;
import backend.backend.rest.workout.view.DTO.ExerciseRefDTO;
import backend.backend.rest.workout.view.DTO.ExerciseSetDTO;
import backend.backend.rest.workout.view.DTO.PauseDTO;
import backend.backend.rest.workout.view.DTO.SectionDTO;
import backend.backend.rest.workout.view.DTO.TrainingDTO;
import backend.backend.rest.workout.view.DTO.TrainingElementDTO;
import backend.backend.rest.workout.view.DTO.WarmupDTO;

public class WorkoutCreateRequestToWorkoutMapper {
    public Workout toBusiness(WorkoutCreateRequest request) {
        List<Section> sections = mapSections(request.getSections());
        return new Workout(null, request.getName(), sections);
    }

    private List<Section> mapSections(List<SectionDTO> sectionDTOs) {
        if (sectionDTOs == null) return new ArrayList<>();
        List<Section> result = new ArrayList<>();
        for (SectionDTO dto : sectionDTOs) {
            if (dto.getSectionType() == SectionType.WARMUP) {
                result.add(mapWarmup(dto.getWarmup()));
            } else if (dto.getSectionType() == SectionType.TRAINING) {
                result.add(mapTraining(dto.getTraining()));
            }
        }
        return result;
    }

    private WarmupSection mapWarmup(WarmupDTO warmup) {
        if (warmup == null) return new WarmupSection(false, null, null);
        return new WarmupSection(warmup.isDurationBased(), warmup.getDurationMinutes(), warmup.getDurationSeconds());
    }

    private TrainingSection mapTraining(TrainingDTO training) {
        List<TrainingElement> elements = new ArrayList<>();
        if (training != null && training.getElements() != null) {
            for (TrainingElementDTO te : training.getElements()) {
                if (te.getType() == TrainingElementDTO.Type.EXERCISE_PLAN && te.getExercisePlan() != null) {
                    elements.add(mapExercisePlan(te.getExercisePlan()));
                } else if (te.getType() == TrainingElementDTO.Type.SUPERSET_PLAN && te.getSupersetPlan() != null) {
                    elements.add(mapSupersetPlan(te.getSupersetPlan()));
                }
            }
        }
        return new TrainingSection(elements);
    }

    private ExercisePlan mapExercisePlan(ExercisePlanDTO dto) {
        List<ExerciseSet> sets = dto.getSets() != null ? dto.getSets().stream()
                .map(this::mapExerciseSet)
                .collect(Collectors.toList()) : new ArrayList<>();
        return new ExercisePlan(dto.getId(), dto.getName(), dto.getMaxWeight(), dto.getMaxRepetitions(), dto.getMuscleGroups(), dto.getNumberOfSets(), sets);
    }

    private ExerciseSet mapExerciseSet(ExerciseSetDTO dto) {
        return new ExerciseSet(dto.getSetNumber(), dto.getWeight(), dto.getRepetitions(), mapPause(dto.getPause()));
    }

    private Pause mapPause(PauseDTO dto) {
        if (dto == null) return null;
        return new Pause(dto.isDurationBased(), dto.getDurationMinutes(), dto.getDurationSeconds());
    }

    private SupersetPlan mapSupersetPlan(backend.backend.rest.workout.view.DTO.SupersetPlanDTO dto) {
        ExerciseRef first = mapExerciseRef(dto.getFirstExercise());
        ExerciseRef second = mapExerciseRef(dto.getSecondExercise());
        Pause pause = mapPause(dto.getPause());
        return new SupersetPlan(dto.getNumberOfSets(), first, second, pause);
    }

    private ExerciseRef mapExerciseRef(ExerciseRefDTO dto) {
        if (dto == null) return null;
        return new ExerciseRef(dto.getId(), dto.getName(), dto.getMaxWeight(), dto.getMaxRepetitions(), dto.getMuscleGroups());
    }
}
