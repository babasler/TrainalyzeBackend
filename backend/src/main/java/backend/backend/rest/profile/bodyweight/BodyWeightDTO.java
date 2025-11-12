package backend.backend.rest.profile.bodyweight;



public class BodyWeightDTO {
    private float weight;
    private String date;

    public float getWeight() {
        return weight;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
}
