package backend.backend.Training.Mapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import backend.backend.common.Util.Duration;
import backend.backend.rest.exercise.Exercise;
import backend.backend.rest.exercise.ExerciseService;
import backend.backend.rest.training.DTO.ExerciseSectionDTO;
import backend.backend.rest.training.DTO.MobilitySectionDTO;
import backend.backend.rest.training.DTO.PauseSectionDTO;
import backend.backend.rest.training.DTO.TrainingDTO;
import backend.backend.rest.training.DTO.TrainingSectionDTO;
import backend.backend.rest.training.Mapper.TrainingDtoToEntityMapper;
import backend.backend.rest.training.Training;
import backend.backend.rest.training.section.MobilitySection;
import backend.backend.rest.training.section.SectionType;
import backend.backend.rest.training.section.TrainingSection;

@ExtendWith(MockitoExtension.class)
class TrainingDtoToEntityMapperTest {
    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private TrainingDtoToEntityMapper mapper;

    @Test
    void testMapMobilitySectionDtoToEntity() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setName("Schulterkreisen");
        when(exerciseService.getExerciseByName("Schulterkreisen")).thenReturn(exercise);

        MobilitySectionDTO mobilitySectionDTO = new MobilitySectionDTO();
        mobilitySectionDTO.setType(SectionType.MOBILITY);
        mobilitySectionDTO.setMobilityExercise("Schulterkreisen");
        mobilitySectionDTO.setReps(15);
        mobilitySectionDTO.setSets(2);

        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingName("Mobility Day");
        trainingDTO.setSections(List.of(mobilitySectionDTO));

        Training training = mapper.mapToEntity(trainingDTO);

        assertEquals("Mobility Day", training.getTrainingName());
        assertTrue(training.getSections().get(0) instanceof MobilitySection);
        MobilitySection ms = (MobilitySection) training.getSections().get(0);
        assertEquals("Schulterkreisen", ms.getMobilityExercise().getName());
        assertEquals(15, ms.getReps());
        assertEquals(2, ms.getSets());
    }

    @Test
    void testMapTrainingSectionDtoToEntity() throws Exception {
        Duration duration = Duration.of(1, 30);
        PauseSectionDTO pauseSectionDTO = new PauseSectionDTO();
        pauseSectionDTO.setDuration(duration);
        pauseSectionDTO.setDurationPause(true);

        Exercise exercise = new Exercise();
        exercise.setName("Bankdrücken");
        when(exerciseService.exerciseExists("Bankdrücken")).thenReturn(true);
        when(exerciseService.getExerciseByName("Bankdrücken")).thenReturn(exercise);

        ExerciseSectionDTO exerciseSectionDTO = new ExerciseSectionDTO("Bankdrücken", 3, 12, 80.0f, pauseSectionDTO);

        TrainingSectionDTO trainingSectionDTO = new TrainingSectionDTO(List.of(exerciseSectionDTO));

        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingName("Strength Day");
        trainingDTO.setSections(List.of(trainingSectionDTO));

        Training training = mapper.mapToEntity(trainingDTO);

        assertEquals("Strength Day", training.getTrainingName());
        assertTrue(training.getSections().get(0) instanceof TrainingSection);
        TrainingSection ts = (TrainingSection) training.getSections().get(0);
        assertEquals("Bankdrücken", ts.getExerciseSection(0).getExercise().getName());
        assertEquals(3, ts.getExerciseSection(0).getSets());
        assertEquals(12, ts.getExerciseSection(0).getReps());
        assertEquals(80.0f, ts.getExerciseSection(0).getWeight());
        assertEquals(pauseSectionDTO.getDuration(), ts.getExerciseSection(0).getPauseAfterSet().getDuration());
        assertEquals(pauseSectionDTO.isDurationPause(), ts.getExerciseSection(0).getPauseAfterSet().isDurationPause());

    }
}