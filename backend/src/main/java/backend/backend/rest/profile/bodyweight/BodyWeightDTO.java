package backend.backend.rest.profile.bodyweight;



public class BodyWeightDTO {
    private float bodyWeight;
    private String date;

    public float getBodyWeight() {
        return bodyWeight;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setBodyWeight(float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }
}
