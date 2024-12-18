package sa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.MenuAddDto;
import sa.dto.StoreRequestDto;
import sa.repository.StoreRepository;
import sa.repository.StoreRequestInfoRepository;
import sa.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class StoreServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreRequestInfoRepository storeRequestInfoRepository;

    private StoreService storeService;

    @BeforeEach
    void setup() {
        storeService = new StoreService(userRepository, storeRequestInfoRepository, storeRepository);
    }

    @Test
    @DisplayName("가게 요청 성공 테스트")
    void requestStore_success() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User user = User.create("storeUser", location, UserRole.STORE);
        userRepository.save(user);

        StoreRequestDto storeRequestDto = new StoreRequestDto("Test Store", location, 3000, 15000);

        // When
        Long requestId = storeService.requestStore(user.getId(), storeRequestDto);

        // Then
        assertThat(requestId).isNotNull();
        StoreRequestInfo requestInfo = storeRequestInfoRepository.findById(requestId).orElseThrow();
        assertThat(requestInfo.getStoreName()).isEqualTo(storeRequestDto.getStoreName());
        assertThat(requestInfo.getLocation()).isEqualTo(location);
    }

    @Test
    @DisplayName("가게 요청 시 권한이 없는 경우 예외 발생")
    void requestStore_invalidRole_exception() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User user = User.create("customerUser", location, UserRole.CUSTOMER);
        userRepository.save(user);

        StoreRequestDto storeRequestDto = new StoreRequestDto("Test Store", location, 3000, 15000);

        // When & Then
        assertThrows(RuntimeException.class, () -> storeService.requestStore(user.getId(), storeRequestDto));
    }

    @Test
    @DisplayName("가게 요청 승인 성공 테스트")
    void acceptStore_success() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User manager = User.create("managerUser", location, UserRole.MANAGER);
        userRepository.save(manager);

        StoreRequestInfo storeRequestInfo = StoreRequestInfo.create(15000, 3000, location, "Test Store");
        storeRequestInfoRepository.save(storeRequestInfo);

        // When
        Long storeId = storeService.acceptStore(manager.getId(), storeRequestInfo.getId());

        // Then
        assertThat(storeId).isNotNull();
        Store store = storeRepository.findById(storeId).orElseThrow();
        assertThat(store.getStoreName()).isEqualTo(storeRequestInfo.getStoreName());
    }

    @Test
    @DisplayName("가게 요청 거절 성공 테스트")
    void denyStore_success() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User manager = User.create("managerUser", location, UserRole.MANAGER);
        userRepository.save(manager);

        StoreRequestInfo storeRequestInfo = StoreRequestInfo.create(15000, 3000, location, "Test Store");
        storeRequestInfoRepository.save(storeRequestInfo);

        // When
        Long requestId = storeService.denyStore(manager.getId(), storeRequestInfo.getId());

        // Then
        StoreRequestInfo deniedRequest = storeRequestInfoRepository.findById(requestId).orElseThrow();
        assertThat(deniedRequest.getStatus()).isEqualTo(StoreRequestStatus.DENY);
    }

    @Test
    @DisplayName("가게 요청 승인 시 권한이 없는 경우 예외 발생")
    void acceptStore_invalidRole_exception() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User customer = User.create("customerUser", location, UserRole.CUSTOMER);
        userRepository.save(customer);

        StoreRequestInfo storeRequestInfo = StoreRequestInfo.create(15000, 3000, location, "Test Store");
        storeRequestInfoRepository.save(storeRequestInfo);

        // When & Then
        assertThrows(RuntimeException.class, () -> storeService.acceptStore(customer.getId(), storeRequestInfo.getId()));
    }

    @Test
    @DisplayName("메뉴 추가 성공 테스트")
    void addMenu_success() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User storeUser = User.create("storeUser", location, UserRole.STORE);
        userRepository.save(storeUser);

        Store store = new Store("Test Store", location, 3000, 15000);
        storeRepository.save(store);

        MenuAddDto menuAddDto = new MenuAddDto("Test Menu", 10000);

        // When
        Long storeId = storeService.addMenu(storeUser.getId(), store.getId(), menuAddDto);

        // Then
        Store updatedStore = storeRepository.findById(storeId).orElseThrow();
        assertThat(updatedStore.getMenuList()).isNotEmpty();
        assertThat(updatedStore.getMenuList().get(0).getMenuName()).isEqualTo(menuAddDto.getMenuName());
    }

    @Test
    @DisplayName("메뉴 추가 시 권한이 없는 경우 예외 발생")
    void addMenu_invalidRole_exception() {
        // Given
        Location location = new Location("서울시 강남구", 37.498, 127.027);
        User customer = User.create("customerUser", location, UserRole.CUSTOMER);
        userRepository.save(customer);

        Store store = new Store("Test Store", location, 3000, 15000);
        storeRepository.save(store);

        MenuAddDto menuAddDto = new MenuAddDto("Test Menu", 10000);

        // When & Then
        assertThrows(RuntimeException.class, () -> storeService.addMenu(customer.getId(), store.getId(), menuAddDto));
    }
}
