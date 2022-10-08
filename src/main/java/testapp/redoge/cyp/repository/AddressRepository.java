package testapp.redoge.cyp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testapp.redoge.cyp.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
