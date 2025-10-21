package backend.backend.rest.profile.bodyweight;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import backend.backend.rest.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BodyWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private float bodyWeight;
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonIgnore
    private Profile profile;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
