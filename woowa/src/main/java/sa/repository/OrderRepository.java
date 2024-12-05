package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.Order;
import sa.domain.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {

    List<Order> findByStoreIdAndOrderStatus(Long storeId, OrderStatus orderStatus);

}
