package backend.backend.rest.profile.bodyweight;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodyWeightService {
    @Autowired
    private BodyWeightRepository bodyWeightRepository;

    public void saveBodyWeight(BodyWeight bodyWeight) {
        bodyWeightRepository.save(bodyWeight);
    }

    public BodyWeight getLastBodyWeight() {
        return bodyWeightRepository.findTopByOrderByIdDesc();
    }

    public List<BodyWeight> getBodyWeightsByPeriod(String period) {
        // Könnte 7,14,30,90 oder 365 Tage sein
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(period));
        Date startDate = cal.getTime();
        Date endDate = Calendar.getInstance().getTime();
        return bodyWeightRepository.findByDateBetween(startDate, endDate);
    }
}
