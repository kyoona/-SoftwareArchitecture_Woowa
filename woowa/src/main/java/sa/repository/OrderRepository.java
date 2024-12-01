package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
}
