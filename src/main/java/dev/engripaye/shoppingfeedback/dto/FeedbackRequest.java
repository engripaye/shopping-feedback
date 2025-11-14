package dev.engripaye.shoppingfeedback.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FeedbackRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Contact is required")
    @Size(max = 50)
    private String contact;

    @NotBlank(message = "Rating is required")
    @Size(max = 20)
    private String rating;

    private String itemsNotFound; //optional
    private String priceToReduce; // optional
    @Size(max = 2000)
    private String improvementSuggestion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getItemsNotFound() {
        return itemsNotFound;
    }

    public void setItemsNotFound(String itemsNotFound) {
        this.itemsNotFound = itemsNotFound;
    }

    public String getPriceToReduce() {
        return priceToReduce;
    }

    public void setPriceToReduce(String priceToReduce) {
        this.priceToReduce = priceToReduce;
    }

    public String getImprovementSuggestion() {
        return improvementSuggestion;
    }

    public void setImprovementSuggestion(String improvementSuggestion) {
        this.improvementSuggestion = improvementSuggestion;
    }
}
