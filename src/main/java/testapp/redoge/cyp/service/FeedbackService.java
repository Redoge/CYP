package testapp.redoge.cyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.repository.FeedbackRepository;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
}
