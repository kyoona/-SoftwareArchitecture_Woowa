package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.event.DeliveryEventPublisher;
import sa.kafka.DeliveryStatusMsg;
import sa.kafka.KafkaProducer;
import sa.kafka.KafkaTopic;
import sa.repository.DeliveryRepository;
import sa.repository.StoreRepository;
import sa.repository.UserRepository;
import sa.scheduler.DeliveryScheduler;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    private final KafkaProducer kafkaProducer;
    private final DeliveryScheduler deliveryScheduler;
    private final DeliveryEventPublisher deliveryEventPublisher;

    @Transactional
    public Long requestDelivery(Long userId, DeliveryAddDto deliveryAddDto) {
        User user = userRepository.findById(userId).orElseThrow();
        Store store = storeRepository.findById(deliveryAddDto.getStore().getId()).orElseThrow();

        Delivery delivery = Delivery.create(user, store, deliveryAddDto.getLocation(), deliveryAddDto.getDeliveryPrice());
        deliveryRepository.save(delivery);

        return delivery.getId();
    }

    @Transactional
    public void sendDeliveryRequest(Long deliveryId) {
        deliveryScheduler.cancel(deliveryId);
        deliveryScheduler.reserve(deliveryId, () -> deliveryEventPublisher.publishDeliveryEvent(deliveryId));
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

        List<Delivery> deliveryWaitList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.WAIT);

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

        kafkaProducer.sendMessage(KafkaTopic.deliver_status, new DeliveryStatusMsg()); // todo: Fill Delivery Status Msg Format
    }

    public void denyDelivery(Long userId, Long deliveryId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();

        // todo: 해당 함수 이후에는 다음 기사에게 요청 진행해야 함.
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

    @Transactional
    public void checkDeliveryAcceptAndCancel(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();
        if(delivery.getDeliveryStatus().equals(DeliveryStatus.WAIT)) {
            // todo: 1분 안에 요청을 수락 / 거절 하지 않은 경우 다음 기사에게 요청 진행해야 함.
        }
    }
}