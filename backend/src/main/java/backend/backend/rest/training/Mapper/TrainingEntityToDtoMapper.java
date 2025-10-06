package backend.backend.rest.training.Mapper;

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

public class TrainingEntityToDtoMapper {
    public TrainingDTO mapTrainingEntityToTrainingDto(Training training) {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingName(training.getTrainingName());
        for (BaseSection section : training.getSections()) {
            BaseSectionDTO sectionDTO = mapSectionToDto(section);
            trainingDTO.addSection(sectionDTO);
        }
        return trainingDTO;
    }

    private BaseSectionDTO mapSectionToDto(BaseSection section) {
        switch (section.getType()) {
            case TRAINING -> {
                return mapTrainingSectionToDto((TrainingSection) section);
            }
            case MOBILITY -> {
                return mapMobilitySectionToDto((MobilitySection) section);
            }
            case PAUSE -> {
                return mapPauseSectionToDto((PauseSection) section);
            }
            default -> throw new IllegalArgumentException("Unknown section type: " + section.getType());
        }
    }

    private TrainingSectionDTO mapTrainingSectionToDto(TrainingSection section) {
        TrainingSectionDTO sectionDTO = new TrainingSectionDTO();
        for (ExerciseSection exerciseSection : section.getExerciseSections()) {
            ExerciseSectionDTO exerciseSectionDTO = mapExerciseSectionToDto(exerciseSection);
            sectionDTO.addExercise(exerciseSectionDTO);
        }
        return sectionDTO;
    }

    private MobilitySectionDTO mapMobilitySectionToDto(MobilitySection section) {
        MobilitySectionDTO sectionDTO = new MobilitySectionDTO();
        sectionDTO.setMobilityExercise(section.getMobilityExercise().getName());
        sectionDTO.setReps(section.getReps());
        sectionDTO.setSets(section.getSets());
        return sectionDTO;
    }

    private PauseSectionDTO mapPauseSectionToDto(PauseSection section) {
        PauseSectionDTO sectionDTO = new PauseSectionDTO();
        sectionDTO.setDuration(section.getDuration());
        sectionDTO.setDurationPause(section.isDurationPause());
        return sectionDTO;
    }

    private ExerciseSectionDTO mapExerciseSectionToDto(ExerciseSection section) {
        ExerciseSectionDTO sectionDTO = new ExerciseSectionDTO();
        sectionDTO.setExercise(section.getExercise().getName());
        sectionDTO.setSets(section.getSets());
        sectionDTO.setReps(section.getReps());
        sectionDTO.setWeight(section.getWeight());
        sectionDTO.setPauseAfterSet(mapPauseSectionToDto(section.getPauseAfterSet()));
        return sectionDTO;
    }

}
