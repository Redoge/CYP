package testapp.redoge.cyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.redoge.cyp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
