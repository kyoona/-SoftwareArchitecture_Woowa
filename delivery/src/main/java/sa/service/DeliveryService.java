package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.Delivery;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.repository.DeliveryRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    @Transactional
    public Long requestDelivery(Long userId, DeliveryAddDto deliveryAddDto) {
        // request delivery
        return 1L; // return deliveryId
    }

    public DeliveryResDto getDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();
        return new DeliveryResDto(delivery);
    }
}