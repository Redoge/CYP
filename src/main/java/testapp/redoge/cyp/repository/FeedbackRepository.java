package testapp.redoge.cyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.redoge.cyp.entity.Feedback;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
