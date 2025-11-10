package dev.engripaye.shoppingfeedback.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact;
    private Integer rating; // 1-5
    @Column(columnDefinition = "Text")
    private String itemsNotFound;
    @Column(columnDefinition = "Text")
    private String priceToReduce;
    @Column(columnDefinition = "Text")
    private String improvementSuggestion;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Feedback(){

    }

    public Feedback(Long id, String name, String contact, Integer rating, String itemsNotFound, String priceToReduce, String improvementSuggestion) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.rating = rating;
        this.itemsNotFound = itemsNotFound;
        this.priceToReduce = priceToReduce;
        this.improvementSuggestion = improvementSuggestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
