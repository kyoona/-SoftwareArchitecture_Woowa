package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
