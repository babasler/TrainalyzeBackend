package backend.backend.rest.workout.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backend.backend.rest.workout.Workout;
import backend.backend.rest.workout.DTO.BaseSectionDTO;
import backend.backend.rest.workout.DTO.ExerciseSectionDTO;
import backend.backend.rest.workout.DTO.MobilitySectionDTO;
import backend.backend.rest.workout.DTO.PauseSectionDTO;
import backend.backend.rest.workout.DTO.TrainingSectionDTO;
import backend.backend.rest.workout.DTO.WarmUpSectionDTO;
import backend.backend.rest.workout.DTO.WorkoutDTO;
import backend.backend.rest.workout.section.BaseSection;
import backend.backend.rest.workout.section.ExerciseSection;
import backend.backend.rest.workout.section.MobilitySection;
import backend.backend.rest.workout.section.PauseSection;
import backend.backend.rest.workout.section.TrainingSection;

public class WorkoutDtoToWorkoutMapper {
    public Workout map(WorkoutDTO dto) {
        Workout workout = new Workout();
        workout.setId(dto.getId());
        workout.setWorkoutName(dto.getName());
        workout.getSections().addAll(mapSections(dto.getSections()));
        return workout;
    }

    private List<BaseSection> mapSections(List<BaseSectionDTO> dto) {
        List<BaseSection> sections = new ArrayList<>();
        for (BaseSectionDTO sectionDTO : dto) {
            if (dto.getClass().equals(PauseSectionDTO.class)) {
                sections.add(mapPauseSectionDtoToPauseSection((PauseSectionDTO) dto));
            }
            else if (dto.getClass().equals(MobilitySectionDTO.class)) {
                sections.add(mapMobilitySectionDtoToMobilitySection((MobilitySectionDTO) dto));
            }
            else if (dto.getClass().equals(WarmUpSectionDTO.class)) {
                sections.add(mapWarmUpSectionDtoToWarmUpSection((WarmUpSectionDTO) dto));
            }
            else if(dto.getClass().equals(TrainingSectionDTO.class)) {
                sections.add(mapTrainingsSectionDtoToTrainingSection((TrainingSectionDTO) dto));
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

    private Collection<? extends ExerciseSection> mapExerciseSectionDtoToExerciseSection(List<ExerciseSectionDTO> exerciseSections) {
        for(ExerciseSectionDTO dto : exerciseSections) {
            ExerciseSection exerciseSection = new ExerciseSection();
            exerciseSection.setExercise(null); //todo find correct exercise
            exerciseSection.setReps(dto.getRepetitions());
            exerciseSection.setSets(dto.getSets());
            exerciseSection.setWeight(dto.getWeight());
            exerciseSection.setPauseAfterSet(dto.g); //todo find
        }
        return null;
    }

    private BaseSection mapWarmUpSectionDtoToWarmUpSection(WarmUpSectionDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapWarmUpSectionDtoToWarmUpSection'");
    }

    private BaseSection mapMobilitySectionDtoToMobilitySection(MobilitySectionDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapMobilitySectionDtoToMobilitySection'");
    }

    private PauseSection mapPauseSectionDtoToPauseSection(PauseSectionDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapPauseSectionDtoToPauseSection'");
    }
}
