// package backend.backend.rest.workout;

// import java.util.ArrayList;
// import java.util.List;

// import backend.backend.rest.workout.section.BaseSection;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;

// @Entity
// public class Workout {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String workoutName;
//     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//     private final List<BaseSection> sections;


//     public Workout() {
//         this.sections = new ArrayList<>();
//     }

//     public Workout(String trainingName) {
//         this.workoutName = trainingName;
//         this.sections = new ArrayList<>();
//     }

//     public Long getId() {
//         return this.id;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getWorkoutName() {
//         return this.workoutName;
//     }

//     public void setWorkoutName(String trainingName) {
//         this.workoutName = trainingName;
//     }

//     public List<BaseSection> getSections() {
//         return this.sections;
//     }

//     public void addSection(BaseSection section) {
//         if (section != null) {
//             this.sections.add(section);
//         } else {
//             throw new IllegalArgumentException("Section cannot be null");
//         }
//     }

//     public void removeSection(BaseSection section) {
//         this.sections.remove(section);
//     }

//     @Override
//     public String toString() {
//         return "Training{" +
//                 "id=" + id +
//                 ", trainingName='" + workoutName + '\'' +
//                 ", sections=" + sections +
//                 '}';
//     }

// }