package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
