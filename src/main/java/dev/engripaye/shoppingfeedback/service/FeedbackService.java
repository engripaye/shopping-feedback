package dev.engripaye.shoppingfeedback.service;

import dev.engripaye.shoppingfeedback.dto.FeedbackRequest;
import dev.engripaye.shoppingfeedback.model.Feedback;
import dev.engripaye.shoppingfeedback.repository.FeedbackRepository;

import java.util.List;

public class FeedbackService {

    private final FeedbackRepository repository;


    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback createFeedback(FeedbackRequest request){
        Feedback f = new Feedback(
                request.getName(),
                request.getContact(),
                request.getRating(),
                request.getItemNotFound(),
                request.getPriceToReduce(),
                request.getImprovementSuggestion()
        );
        return repository.save(f);
    }

    public List<Feedback> getAll(){
        return repository.findAll();
    }

    public Feedback getById(Long id){
        return repository.findById(id).orElse(null);
    }
}
