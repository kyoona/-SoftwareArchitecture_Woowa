package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.repository.DeliveryRepository;
import sa.repository.StoreRepository;
import sa.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long requestDelivery(Long userId, DeliveryAddDto deliveryAddDto) {
        User user = userRepository.findById(userId).orElseThrow();
        Store store = storeRepository.findById(deliveryAddDto.getStore().getId()).orElseThrow();

        Delivery delivery = Delivery.create(user, store, deliveryAddDto.getLocation(), deliveryAddDto.getDeliveryPrice());
        deliveryRepository.save(delivery);

        return delivery.getId();
    }

    @Transactional
    public void cancelDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        if (delivery.getDeliveryStatus() != DeliveryStatus.WAIT) {
            throw new RuntimeException();
        }

        delivery.setDeliveryStatus(DeliveryStatus.CANCEL);
    }

    public DeliveryResDto getDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        return new DeliveryResDto(delivery);
    }

    public List<DeliveryResDto> getWaitDeliveryList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);

        List<Delivery> deliveryWaitList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.WAIT),
                deliveryDenyList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.DENY);
        deliveryWaitList.addAll(deliveryDenyList);

        return deliveryWaitList.stream()
                .map(DeliveryResDto::new)
                .toList();
    }

    @Transactional
    public void acceptDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        delivery.setDeliveryStatus(DeliveryStatus.ACCEPT);
    }

    @Transactional
    public void denyDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        delivery.setDeliveryStatus(DeliveryStatus.DENY);
    }

    @Transactional
    public void doneDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        delivery.setDeliveryStatus(DeliveryStatus.DONE);
    }

    private void checkStore(User user){
        if(user.getUserRole() != UserRole.STORE){
            throw new RuntimeException();
        }
    }
}