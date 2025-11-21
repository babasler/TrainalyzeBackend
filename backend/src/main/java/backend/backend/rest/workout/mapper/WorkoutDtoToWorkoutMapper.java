package backend.backend.rest.workout.mapper;

import java.util.ArrayList;
import java.util.List;

import backend.backend.rest.workout.Workout;
import backend.backend.rest.workout.DTO.BaseSectionDTO;
import backend.backend.rest.workout.DTO.PauseSectionDTO;
import backend.backend.rest.workout.section.BaseSection;
import backend.backend.rest.workout.section.MobilitySection;
import backend.backend.rest.workout.section.PauseSection;

public class WorkoutDtoToWorkoutMapper {
    public Workout map(Workout dto) {
        Workout workout = new Workout();
        workout.setId(dto.getId());
        workout.setWorkoutName(dto.getWorkoutName());
        workout.getSections().addAll(mapSections(dto.getSections()));
        return workout;
    }

    private List<BaseSection> mapSections(List<BaseSectionDTO> dto) {
        List<BaseSection> sections = new ArrayList<>();
        for (BaseSectionDTO sectionDTO : dto) {
            if (dto.getClass().equals(PauseSectionDTO.class)) {
                sections.add(mapPauseSectionDtoToPuaseSection((PauseSection) dto));
            }
            else if (dto.getClass().equals(MobilitySectionDTO.class)) {
                
            }

        }
        return sections;
    }

    private PauseSection mapPauseSectionDtoToPuaseSection(PauseSection dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapPauseSectionDtoToPuaseSection'");
    }
}
