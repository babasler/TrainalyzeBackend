package backend.backend.rest.training;

import java.util.ArrayList;
import java.util.List;

import backend.backend.rest.training.section.BaseSection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainingName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BaseSection> sections;


    public Training() {
        this.sections = new ArrayList<>();
    }

    public Training(String trainingName) {
        this.trainingName = trainingName;
        this.sections = new ArrayList<>();
    }

    public String getTrainingName() {
        return this.trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public List<BaseSection> getSections() {
        return this.sections;
    }

    public void addSection(BaseSection section) {
        if (section != null) {
            this.sections.add(section);
        } else {
            throw new IllegalArgumentException("Section cannot be null");
        }
    }

    public void removeSection(BaseSection section) {
        this.sections.remove(section);
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingName='" + trainingName + '\'' +
                ", sections=" + sections +
                '}';
    }

}