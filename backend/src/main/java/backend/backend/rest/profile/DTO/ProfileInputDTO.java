package backend.backend.rest.profile.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProfileInputDTO {
    private String username;
    private String weightIncreaseType;
    private float increaseWeight;
    private int increaseAtReps;
    private String workoutSelection;
    private String selectedTrainingsplan;
    private String handleMissingWorkout;
    private float bodyHeight;
    private float bodyWeight;

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the weightIncreaseType
     */
    public String getWeightIncreaseType() {
        return weightIncreaseType;
    }

    /**
     * @param weightIncreaseType the weightIncreaseType to set
     */
    public void setWeightIncreaseType(String weightIncreaseType) {
        this.weightIncreaseType = weightIncreaseType;
    }

    /**
     * @return float return the increaseWeight
     */
    public float getIncreaseWeight() {
        return increaseWeight;
    }

    /**
     * @param increaseWeight the increaseWeight to set
     */
    public void setIncreaseWeight(float increaseWeight) {
        this.increaseWeight = increaseWeight;
    }

    /**
     * @return int return the increaseAtReps
     */
    public int getIncreaseAtReps() {
        return increaseAtReps;
    }

    /**
     * @param increaseAtReps the increaseAtReps to set
     */
    public void setIncreaseAtReps(int increaseAtReps) {
        this.increaseAtReps = increaseAtReps;
    }

    /**
     * @return String return the workoutSelection
     */
    public String getWorkoutSelection() {
        return workoutSelection;
    }

    /**
     * @param workoutSelection the workoutSelection to set
     */
    public void setWorkoutSelection(String workoutSelection) {
        this.workoutSelection = workoutSelection;
    }

    /**
     * @return String return the selectedTrainingsplan
     */
    public String getSelectedTrainingsplan() {
        return selectedTrainingsplan;
    }

    /**
     * @param selectedTrainingsplan the selectedTrainingsplan to set
     */
    public void setSelectedTrainingsplan(String selectedTrainingsplan) {
        this.selectedTrainingsplan = selectedTrainingsplan;
    }

    /**
     * @return String return the handleMissingWorkout
     */
    public String getHandleMissingWorkout() {
        return handleMissingWorkout;
    }

    /**
     * @param handleMissingWorkout the handleMissingWorkout to set
     */
    public void setHandleMissingWorkout(String handleMissingWorkout) {
        this.handleMissingWorkout = handleMissingWorkout;
    }

    /**
     * @return float return the bodyHeight
     */
    public float getBodyHeight() {
        return bodyHeight;
    }

    /**
     * @param bodyHeight the bodyHeight to set
     */
    public void setBodyHeight(float bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    /**
     * @return float return the bodyWeight
     */
    public float getBodyWeight() {
        return bodyWeight;
    }

    /**
     * @param bodyWeight the bodyWeight to set
     */
    public void setBodyWeight(float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }
}
