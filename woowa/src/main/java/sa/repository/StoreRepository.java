package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
