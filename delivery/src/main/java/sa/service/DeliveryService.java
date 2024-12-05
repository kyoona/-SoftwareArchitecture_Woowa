package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.Delivery;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.repository.DeliveryRepository;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public void cancelDelivery(Long deliveryId) {
        deliveryRepository.deleteById(deliveryId);
    }

    @Transactional
    public DeliveryResDto getDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        return new DeliveryResDto(delivery);
    }

    @Transactional
    public List<DeliveryResDto> getDeliveries() {
        List<DeliveryResDto> deliveryResDtoList = new ArrayList<>();
        deliveryRepository.findAll().forEach(delivery -> deliveryResDtoList.add(new DeliveryResDto(delivery)));

        return deliveryResDtoList;
    }
}