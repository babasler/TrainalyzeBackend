package backend.backend.rest.training.Mapper;

import org.springframework.beans.factory.annotation.Autowired;

import backend.backend.common.Util.Duration;
import backend.backend.rest.exercise.Exercise;
import backend.backend.rest.exercise.ExerciseService;
import backend.backend.rest.training.DTO.BaseSectionDTO;
import backend.backend.rest.training.DTO.ExerciseSectionDTO;
import backend.backend.rest.training.DTO.MobilitySectionDTO;
import backend.backend.rest.training.DTO.PauseSectionDTO;
import backend.backend.rest.training.DTO.TrainingDTO;
import backend.backend.rest.training.DTO.TrainingSectionDTO;
import backend.backend.rest.training.Training;
import backend.backend.rest.training.section.BaseSection;
import backend.backend.rest.training.section.ExerciseSection;
import backend.backend.rest.training.section.MobilitySection;
import backend.backend.rest.training.section.PauseSection;
import backend.backend.rest.training.section.TrainingSection;

public class TrainingDtoToEntityMapper {
    @Autowired
    private ExerciseService exerciseService;

    public Training mapToEntity(TrainingDTO trainingDTO) {
        Training training = new Training();
        training.setTrainingName(trainingDTO.getTrainingName());
        for (BaseSectionDTO sectionDTO : trainingDTO.getSections()) {
            BaseSection section = mapSectionToEntity(sectionDTO);
            training.addSection(section);
        }
        return training;
    }

    private BaseSection mapSectionToEntity(BaseSectionDTO sectionDTO) {
        switch (sectionDTO.getType()) {
            case TRAINING -> {
                return mapTrainingSectionToEntity((TrainingSectionDTO) sectionDTO);
            }
            case MOBILITY -> {
                return mapMobilitySectionToEntity((MobilitySectionDTO) sectionDTO);
            }
            case PAUSE -> {
                return mapPauseSectionToEntity((PauseSectionDTO) sectionDTO);
            }
            default -> throw new IllegalArgumentException("Unknown section type: " + sectionDTO.getType());
        }
    }

    private TrainingSection mapTrainingSectionToEntity(TrainingSectionDTO sectionDTO) {
        TrainingSection trainingSection = new TrainingSection();
        for (ExerciseSectionDTO exerciseSectionDTO : sectionDTO.getExercises()) {
            ExerciseSection exerciseSection = mapExerciseSectionToEntity(exerciseSectionDTO);
            trainingSection.addExercise(exerciseSection);
        }
        return trainingSection;
    }

    private MobilitySection mapMobilitySectionToEntity(MobilitySectionDTO sectionDTO) {
        MobilitySection mobilitySection = new MobilitySection();
        Exercise mobilityExercise = this.exerciseService.getExerciseByName(sectionDTO.getMobilityExercise());
        mobilitySection.setMobilityExercise(mobilityExercise);
        mobilitySection.setReps(sectionDTO.getReps());
        mobilitySection.setSets(sectionDTO.getSets());
        return mobilitySection;
    }

    private PauseSection mapPauseSectionToEntity(PauseSectionDTO sectionDTO) {
        PauseSection pauseSection = new PauseSection();
        pauseSection.setDurationPause(sectionDTO.isDurationPause());
        if(sectionDTO.isDurationPause()) {
            pauseSection.setDuration(sectionDTO.getDuration());
        }
        else {
            pauseSection.setDuration(Duration.of(0,0));
        }
        return pauseSection;
    }

    private ExerciseSection mapExerciseSectionToEntity(ExerciseSectionDTO sectionDTO) {
        if(!this.exerciseService.exerciseExists(sectionDTO.getExercise())) {
            throw new IllegalArgumentException("Exercise does not exist: " + sectionDTO.getExercise());
        }
        Exercise exercise = this.exerciseService.getExerciseByName(sectionDTO.getExercise());
        
        ExerciseSection exerciseSection = new ExerciseSection();
        exerciseSection.setExercise(exercise);
        exerciseSection.setSets(sectionDTO.getSets());
        exerciseSection.setReps(sectionDTO.getReps());
        exerciseSection.setWeight(sectionDTO.getWeight());
        exerciseSection.setPauseAfterSet(mapPauseSectionToEntity(sectionDTO.getPauseAfterSet()));
        return exerciseSection;
    }

}
