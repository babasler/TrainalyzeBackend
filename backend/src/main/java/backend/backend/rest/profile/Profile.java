package backend.backend.rest.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import backend.backend.rest.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore 
    private User user;

    private String username;
    private String weightIncreaseType;
    private float increaseWeight;
    private int increaseAtReps;
    private String workoutSelection;
    private String selectedTrainingsplan;
    private String handleMissingWorkout;

    public Profile(User user,String username) {
        this.username = username;
        this.user = user;
        this.weightIncreaseType = "";
        this.increaseWeight = 0.0f;
        this.increaseAtReps = 0;
        this.workoutSelection = "";
        this.selectedTrainingsplan = "";
        this.handleMissingWorkout = "";
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

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

}
