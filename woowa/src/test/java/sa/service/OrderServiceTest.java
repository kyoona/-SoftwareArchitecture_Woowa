package sa.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.OrderAddDto;
import sa.dto.OrderMenuDto;
import sa.repository.OrderRepository;
import sa.repository.StoreRepository;
import sa.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문 요청 성공 테스트")
    void requestOrder_success() {
        // Given
        Location userLocation = new Location("서울시 강남구", 37.498, 127.027);
        User user = User.create("testUser", userLocation, UserRole.CUSTOMER);
        userRepository.save(user);

        Location storeLocation = new Location("서울시 종로구", 37.573, 126.979);
        Store store = new Store("testStore", storeLocation, 2000, 15000);
        store.addMenu("menu1", 10000);
        store.addMenu("menu2", 10000);
        storeRepository.save(store);

        OrderAddDto orderAddDto = new OrderAddDto(
                "010-1234-5678", // 전화번호
                store.getId(),
                List.of(new OrderMenuDto(store.getMenuList().get(0).getId(), 2)),
                userLocation,
                PaymentMethodType.CREDIT_CARD
        );

        // When
        Long orderId = orderService.requestOrder(user.getId(), orderAddDto);

        // Then
        assertThat(orderId).isNotNull();
        Order savedOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(savedOrder.getStore().getId()).isEqualTo(store.getId());
        assertThat(savedOrder.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("주문 최소 금액 미달 시 예외 발생")
    void requestOrder_minimumOrderPrice_exception() {
        // Given
        Location userLocation = new Location("서울시 강남구", 37.498, 127.027);
        User user = User.create("testUser", userLocation, UserRole.CUSTOMER);
        userRepository.save(user);

        Location storeLocation = new Location("서울시 종로구", 37.573, 126.979);
        Store store = new Store("testStore", storeLocation, 2000, 15000);
        storeRepository.save(store);

        // OrderAddDto에 전화번호 추가
        OrderAddDto orderAddDto = new OrderAddDto(
                "010-1234-5678", // 전화번호
                store.getId(),
                List.of(new OrderMenuDto(1L, 1)), // Mock된 Menu ID로 최소 금액 미달
                userLocation,
                PaymentMethodType.CREDIT_CARD
        );

        // When & Then
        assertThrows(RuntimeException.class, () -> orderService.requestOrder(user.getId(), orderAddDto));
    }

    @Test
    @DisplayName("주문할 때 사용자가 존재하지 않으면 예외 발생")
    void requestOrder_userNotFound_exception() {
        // Given
        Location userLocation = new Location("서울시 강남구", 37.498, 127.027);
        Location storeLocation = new Location("서울시 종로구", 37.573, 126.979);
        Store store = new Store("testStore", storeLocation, 2000, 15000);
        storeRepository.save(store);

        OrderAddDto orderAddDto = new OrderAddDto(
                "010-1234-5678", // 전화번호
                store.getId(),
                List.of(new OrderMenuDto(1L, 2)), // Mock된 Menu ID
                userLocation,
                PaymentMethodType.CREDIT_CARD
        );

        // When & Then
        assertThrows(RuntimeException.class, () -> orderService.requestOrder(999L, orderAddDto));  // 존재하지 않는 userId
    }

    @Test
    @DisplayName("주문 시 메뉴가 없는 경우 예외 발생")
    void requestOrder_menuNotFound_exception() {
        // Given
        Location userLocation = new Location("서울시 강남구", 37.498, 127.027);
        User user = User.create("testUser", userLocation, UserRole.CUSTOMER);
        userRepository.save(user);

        Location storeLocation = new Location("서울시 종로구", 37.573, 126.979);
        Store store = new Store("testStore", storeLocation, 2000, 15000);
        storeRepository.save(store);

        // 존재하지 않는 메뉴 ID로 주문
        OrderAddDto orderAddDto = new OrderAddDto(
                "010-1234-5678", // 전화번호
                store.getId(),
                List.of(new OrderMenuDto(999L, 2)), // 존재하지 않는 Menu ID
                userLocation,
                PaymentMethodType.CREDIT_CARD
        );

        // When & Then
        assertThrows(RuntimeException.class, () -> orderService.requestOrder(user.getId(), orderAddDto));  // 존재하지 않는 메뉴
    }

}
