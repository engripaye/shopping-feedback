package dev.engripaye.shoppingfeedback.controller;

import dev.engripaye.shoppingfeedback.dto.FeedbackRequest;
import dev.engripaye.shoppingfeedback.model.Feedback;
import dev.engripaye.shoppingfeedback.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@Valid @RequestBody FeedbackRequest request) throws IOException, GeneralSecurityException {
        Feedback saved = service.createFeedback(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> allFeedback(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> get(@PathVariable Long id){
        Feedback f = service.getById(id);
        return f == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(f);
    }
}
