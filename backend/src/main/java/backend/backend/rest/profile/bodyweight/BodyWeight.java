package backend.backend.rest.profile.bodyweight;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
q
@Entity
public class BodyWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float bodyWeight;
    private Date date;

    public float getBodyWeight() {
        return bodyWeight;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setBodyWeight(float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }
}
