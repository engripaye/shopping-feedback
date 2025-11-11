package dev.engripaye.shoppingfeedback.repository;

import dev.engripaye.shoppingfeedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
