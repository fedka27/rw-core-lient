package me.rocketwash.client.data.dto;

public class InformationAndRecommendationCompletable {

    private String recommendation;
    private String information;

    public InformationAndRecommendationCompletable(String recommendation,
                                                   String information) {
        this.recommendation = recommendation;
        this.information = information;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getInformation() {
        return information;
    }
}
