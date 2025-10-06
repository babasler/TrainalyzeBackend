package backend.backend.Training.Mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import backend.backend.common.Util.Duration;
import backend.backend.rest.exercise.Exercise;
import backend.backend.rest.training.DTO.TrainingSectionDTO;
import backend.backend.rest.training.Mapper.TrainingEntityToDtoMapper;
import backend.backend.rest.training.Training;
import backend.backend.rest.training.section.ExerciseSection;
import backend.backend.rest.training.section.PauseSection;
import backend.backend.rest.training.section.SectionType;
import backend.backend.rest.training.section.TrainingSection;

@ExtendWith(MockitoExtension.class)
class TrainingEntityToDtoMapperTest {
    
    @InjectMocks
    private TrainingEntityToDtoMapper mapper;

    @Test
    void testMapTrainingEntityToDto() {
        Duration duration = Duration.of(1, 30);
        PauseSection pauseSection = new PauseSection();
        pauseSection.setDuration(duration);
        pauseSection.setDurationPause(true);

        Exercise exercise = new Exercise();
        exercise.setName("Kniebeuge");
        //when(exerciseService.exerciseExists("Kniebeuge")).thenReturn(true);
        //when(exerciseService.getExerciseByName("Kniebeuge")).thenReturn(exercise);

        ExerciseSection exerciseSection = new ExerciseSection();
        exerciseSection.setExercise(exercise);
        exerciseSection.setPauseAfterSet(pauseSection);
        exerciseSection.setReps(10);
        exerciseSection.setSets(3);
        exerciseSection.setWeight(50.0f);

        TrainingSection trainingSection = new TrainingSection();
        trainingSection.addExercise(exerciseSection);

        Training training = new Training();
        training.addSection(trainingSection);
        training.setTrainingName("mein Training");

        var dto = mapper.mapTrainingEntityToTrainingDto(training);

        assertNotNull(dto);
        assertEquals("mein Training", dto.getTrainingName());
        assertEquals(1, dto.getSections().size());
        var sectionDto = dto.getSections().get(0);
        assertEquals(SectionType.TRAINING, sectionDto.getType());
        var trainingSectionDto = (TrainingSectionDTO) sectionDto;
        assertEquals(sectionDto, trainingSectionDto);
    }
}