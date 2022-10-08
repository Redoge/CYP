package testapp.redoge.cyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.redoge.cyp.entity.Street;
@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
}
