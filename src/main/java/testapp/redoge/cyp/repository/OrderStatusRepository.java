package testapp.redoge.cyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.redoge.cyp.entity.OrderStatus;
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
