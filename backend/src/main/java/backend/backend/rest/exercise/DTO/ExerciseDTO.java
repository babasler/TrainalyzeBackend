package backend.backend.rest.exercise.DTO;

import java.util.List;

public class ExerciseDTO {
    private long id;
    private String name;
    private String type;
    private String motionSymmetry;
    private List<String> muscleGroups;
    private String equipment;

    public ExerciseDTO() {
        // Default constructor
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return String return the motionSymmetry
     */
    public String getMotionSymmetry() {
        return motionSymmetry;
    }

    /**
     * @param motionSymmetry the motionSymmetry to set
     */
    public void setMotionSymmetry(String motionSymmetry) {
        this.motionSymmetry = motionSymmetry;
    }

    /**
     * @return java.util.List<String> return the muscleGroups
     */
    public java.util.List<String> getMuscleGroups() {
        return muscleGroups;
    }

    /**
     * @param muscleGroups the muscleGroups to set
     */
    public void setMuscleGroups(java.util.List<String> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    /**
     * @return String return the equipment
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}