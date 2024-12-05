package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.OrderAddDto;
import sa.dto.OrderMenuDto;
import sa.dto.OrderResDto;
import sa.event.OrderEventPublisher;
import sa.kafka.DeliveryStatus;
import sa.kafka.KafkaProducer;
import sa.kafka.KafkaTopic;
import sa.kafka.PaymentRequestMsg;
import sa.repository.OrderRepository;
import sa.repository.StoreRepository;
import sa.repository.UserRepository;
import sa.scheduler.OrderScheduler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    private final KafkaProducer kafkaProducer;
    private final OrderScheduler orderScheduler;
    private final OrderEventPublisher orderEventPublisher;

    @Transactional
    public Long requestOrder(Long userId, OrderAddDto orderAddDto) {
        User user = userRepository.findById(userId).orElseThrow();
        Store store = storeRepository.findById(orderAddDto.getStoreId()).orElseThrow();

        List<Long> menuIdList = orderAddDto.getMenuList().stream()
                .map(OrderMenuDto::getMenuId)
                .toList();
        Map<Long, Menu> menuMap = orderRepository.findMenuListById(menuIdList);

        Map<Menu, Integer> orderMenuMap = orderAddDto.getMenuList().stream()
                .collect(Collectors.toMap(
                        (m) -> menuMap.get(m.getMenuId()),
                        (m) -> m.getCount()
                ));

        Order order = Order.create(user, store, orderAddDto.getLocation(), orderMenuMap);
        checkMinimumOrderPrice(order.getTotalPrice(), store.getDeliveryPrice(), store.getMinimumOrderPrice());

        orderRepository.save(order);

        kafkaProducer.sendMessage(KafkaTopic.payment_request, new PaymentRequestMsg(order.getId(), userId, orderAddDto.getPaymentMethodType(),order.getTotalPrice()));

        return order.getId();
    }

    public OrderResDto getOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.getStore();
        return new OrderResDto(order);
    }

    @Transactional
    public void setPaymentStatus(Long orderId, boolean success) {
        System.out.println("에에에??" + success + " " + orderId);
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (success) {
            order.setOrderStatus(OrderStatus.PAYMENT_SUCCESS);
        } else {
            order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
            orderScheduler.reserve(orderId, () -> orderEventPublisher.publishOrderEvent(orderId));
        }
    }

    @Transactional
    public void setDeliveryStatus(Long orderId, DeliveryStatus deliveryStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (deliveryStatus.equals(DeliveryStatus.START)) {
            order.setOrderStatus(OrderStatus.DELIVERY);
        } else {
            order.setOrderStatus(OrderStatus.FINISH);
        }
    }

    @Transactional
    public void checkOrderAcceptAndCancel(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if(order.getOrderStatus().equals(OrderStatus.PAYMENT_SUCCESS)){
            order.setOrderStatus(OrderStatus.CANCEL);
        }
    }

    @Transactional
    public void acceptOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setOrderStatus(OrderStatus.ACCEPT);
    }

    @Transactional
    public void denyOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setOrderStatus(OrderStatus.DENY);
    }

    public List<OrderResDto> getWaitOrderList(Long userId, Long storeId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);

        List<Order> waitOrderList = orderRepository.findByStoreIdAndOrderStatus(storeId, OrderStatus.PAYMENT_SUCCESS);
        return waitOrderList.stream()
                .map(OrderResDto::new)
                .toList();
    }

    private void checkMinimumOrderPrice(int totalPrice, int deliveryPrice, int minimumOrderPrice){
        if(totalPrice - deliveryPrice < minimumOrderPrice){
            throw new RuntimeException("배달 최소 금액 미만");
        }
    }

    private void checkStore(User user){
        if(user.getUserRole() != UserRole.STORE){
            throw new RuntimeException();
        }
    }

}
