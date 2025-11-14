package dev.engripaye.shoppingfeedback.service;

import dev.engripaye.shoppingfeedback.dto.FeedbackRequest;
import dev.engripaye.shoppingfeedback.model.Feedback;
import dev.engripaye.shoppingfeedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class FeedbackService {

    private final GoogleSheetsService sheetsService;
    private final FeedbackRepository repository;

    public FeedbackService(GoogleSheetsService sheetsService, FeedbackRepository repository) {
        this.sheetsService = sheetsService;
        this.repository = repository;
    }

    public Feedback createFeedback(FeedbackRequest request) throws IOException, GeneralSecurityException {
        Feedback feedback = new Feedback();
        feedback.setName(request.getName());
        feedback.setContact(request.getContact());

        feedback.setRating(request.getRating() != null ? request.getRating().toString() : null);
        feedback.setItemsNotFound(request.getItemsNotFound());
        feedback.setPriceToReduce(request.getPriceToReduce());
        feedback.setImprovementSuggestion(request.getImprovementSuggestion());
        feedback.setCreatedAt(LocalDateTime.now());

        // Append to Google Sheet
        sheetsService.appendFeedback(feedback);

        return repository.save(feedback);
    }

    public List<Feedback> getAll(){
        return repository.findAll();
    }

    public Feedback getById(Long id){
        return repository.findById(id).orElse(null);
    }
}
