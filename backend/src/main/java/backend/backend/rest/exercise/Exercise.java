package backend.backend.rest.exercise;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;


@Entity
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private String motionSymmetry;
    @ElementCollection
    private List<String> muscleGroups;
    private String equipment;

    public Exercise() {
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
     * @return List<String> return the muscleGroups
     */
    public List<String> getMuscleGroups() {
        return muscleGroups;
    }

    /**
     * @param muscleGroups the muscleGroups to set
     */
    public void setMuscleGroups(List<String> muscleGroups) {
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

}