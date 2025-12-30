package backend.backend.rest.workout.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.backend.common.Util.Duration;
import backend.backend.rest.exercise.ExerciseService;
import backend.backend.rest.workout.DTO.BaseSectionDTO;
import backend.backend.rest.workout.DTO.ExerciseSectionDTO;
import backend.backend.rest.workout.DTO.MobilitySectionDTO;
import backend.backend.rest.workout.DTO.PauseSectionDTO;
import backend.backend.rest.workout.DTO.TrainingSectionDTO;
import backend.backend.rest.workout.DTO.WarmUpSectionDTO;
import backend.backend.rest.workout.DTO.WorkoutDTO;
import backend.backend.rest.workout.Workout;
import backend.backend.rest.workout.section.BaseSection;
import backend.backend.rest.workout.section.ExerciseSection;
import backend.backend.rest.workout.section.MobilitySection;
import backend.backend.rest.workout.section.PauseSection;
import backend.backend.rest.workout.section.TrainingSection;
import backend.backend.rest.workout.section.WarumUpSection;

@Component
public class WorkoutDtoToWorkoutMapper {

    @Autowired
    private ExerciseService exerciseService;

    private final Logger logger = LoggerFactory.getLogger(WorkoutDtoToWorkoutMapper.class);

    public Workout map(WorkoutDTO dto) {
        Workout workout = new Workout();
        workout.setId(dto.getId());
        workout.setWorkoutName(dto.getName());
        workout.getSections().addAll(mapSections(dto.getSections()));
        return workout;
    }

    private List<BaseSection> mapSections(List<BaseSectionDTO> sectionDTOs) {
        List<BaseSection> sections = new ArrayList<>();
        if (sectionDTOs == null) return sections;
        for (BaseSectionDTO sectionDTO : sectionDTOs) {
            if (sectionDTO instanceof PauseSectionDTO pauseDTO) {
                sections.add(mapPauseSectionDtoToPauseSection(pauseDTO));
            } else if (sectionDTO instanceof MobilitySectionDTO mobilityDTO) {
                sections.add(mapMobilitySectionDtoToMobilitySection(mobilityDTO));
            } else if (sectionDTO instanceof WarmUpSectionDTO warmUpDTO) {
                sections.add(mapWarmUpSectionDtoToWarmUpSection(warmUpDTO));
            } else if (sectionDTO instanceof TrainingSectionDTO trainingDTO) {
                sections.add(mapTrainingsSectionDtoToTrainingSection(trainingDTO));
            } else {
                logger.warn("Unknown sectionDTO type: {}", sectionDTO.getClass());
            }
        }
        return sections;
    }

    private BaseSection mapTrainingsSectionDtoToTrainingSection(TrainingSectionDTO dto) {
        TrainingSection trainingSection = new TrainingSection();
        trainingSection.setId(dto.getId());
        trainingSection.getExerciseSections().addAll(mapExerciseSectionDtoToExerciseSection(dto.getExerciseSections()));
        return trainingSection;
    }

    private Collection<? extends ExerciseSection> mapExerciseSectionDtoToExerciseSection(
            List<ExerciseSectionDTO> exerciseSections) {
        List<ExerciseSection> sections = new ArrayList<>();
        if (exerciseSections == null) return sections;
        for (ExerciseSectionDTO dto : exerciseSections) {
            ExerciseSection exerciseSection = new ExerciseSection();
            exerciseSection.setId(dto.getId());
            if (dto.getExerciseId() != null) {
                exerciseSection.setExercise(exerciseService.getExerciseById(dto.getExerciseId()));
            }
            exerciseSection.setReps(dto.getRepetitions());
            exerciseSection.setSets(dto.getSets());
            exerciseSection.setWeight(dto.getWeight());
            if (dto.getPauseSection() != null) {
                exerciseSection.setPauseAfterSet(mapPauseSectionDtoToPauseSection(dto.getPauseSection()));
            }

            logger.info("Mapped ExerciseSectionDTO to ExerciseSection: {}", exerciseSection.toString());

            sections.add(exerciseSection);
        }
        return sections;
    }

    private BaseSection mapWarmUpSectionDtoToWarmUpSection(WarmUpSectionDTO dto) {
        WarumUpSection warmUpSection = new WarumUpSection();
        warmUpSection.setId(dto.getId());
        warmUpSection.setDuration(Duration.of(dto.getDuration()));
        warmUpSection.setDurationWarmUp(dto.isDurationWarmUp());

        logger.info("Mapped WarmUpSectionDTO to WarmUpSection: {}", warmUpSection.toString());
        return warmUpSection;
    }

    private BaseSection mapMobilitySectionDtoToMobilitySection(MobilitySectionDTO dto) {
        MobilitySection mobilitySection = new MobilitySection();
        mobilitySection.setId(dto.getId());
        mobilitySection.setMobilityExercise(exerciseService.getExerciseById(dto.getMobilityExerciseId()));
        mobilitySection.setSets(dto.getSets());
        mobilitySection.setReps(dto.getReps());
        
        logger.info("Mapped MobilitySectionDTO to MobilitySection: {}", mobilitySection.toString());
        return mobilitySection;
    }

    private PauseSection mapPauseSectionDtoToPauseSection(PauseSectionDTO dto) {
        PauseSection pauseSection = new PauseSection();
        pauseSection.setDuration(dto.getDuration());
        pauseSection.setDurationPause(dto.isDurationPause());
        logger.info("Mapped PauseSectionDTO to PauseSection: {}", pauseSection.toString());
        return pauseSection;
    }
}
