package backend.backend.rest.profile.bodyweight;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.rest.profile.Profile;

@Service
public class BodyWeightService {
    @Autowired
    private BodyWeightRepository bodyWeightRepository;

    public void saveBodyWeight(BodyWeight bodyWeight) {
        bodyWeightRepository.save(bodyWeight);
    }
    
    /**
     * Speichert ein neues BodyWeight für ein bestimmtes Profil
     */
    public BodyWeight saveBodyWeightForProfile(Profile profile, float weight, Date date) {
        BodyWeight bodyWeight = new BodyWeight();
        bodyWeight.setProfile(profile);
        bodyWeight.setBodyWeight(weight);
        bodyWeight.setDate(date);
        return bodyWeightRepository.save(bodyWeight);
    }

    public BodyWeight getLastBodyWeight() {
        return bodyWeightRepository.findTopByOrderByIdDesc();
    }
    
    /**
     * Holt das neueste BodyWeight für ein bestimmtes Profil
     */
    public BodyWeight getCurrentBodyWeightForProfile(Profile profile) {
        return bodyWeightRepository.findFirstByProfileOrderByDateDesc(profile);
    }
    
    /**
     * Holt alle BodyWeight Einträge für ein Profil
     */
    public List<BodyWeight> getAllBodyWeightsForProfile(Profile profile) {
        return bodyWeightRepository.findByProfileOrderByDateDesc(profile);
    }

    public List<BodyWeight> getBodyWeightsByPeriod(String period) {
        // Könnte 7,14,30,90 oder 365 Tage sein
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(period));
        Date startDate = cal.getTime();
        Date endDate = Calendar.getInstance().getTime();
        return bodyWeightRepository.findByDateBetween(startDate, endDate);
    }
    
    /**
     * Holt BodyWeight Einträge für ein Profil innerhalb eines Zeitraums
     */
    public List<BodyWeight> getBodyWeightsByPeriodForProfile(Profile profile, String period) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(period));
        Date startDate = cal.getTime();
        Date endDate = Calendar.getInstance().getTime();
        return bodyWeightRepository.findByProfileAndDateBetweenOrderByDateDesc(profile, startDate, endDate);
    }
}
