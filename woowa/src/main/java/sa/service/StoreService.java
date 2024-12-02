package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.MenuAddDto;
import sa.dto.StoreDetailResDto;
import sa.dto.StoreRequestDto;
import sa.dto.StoreResDto;
import sa.repository.StoreRepository;
import sa.repository.StoreRequestInfoRepository;
import sa.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreService {

    private final UserRepository userRepository;
    private final StoreRequestInfoRepository storeRequestInfoRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long requestStore(Long userId, StoreRequestDto storeRequestDto) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);

        StoreRequestInfo storeRequestInfo = StoreRequestInfo.create(storeRequestDto.getMinimumOrderPrice(), storeRequestDto.getDeliveryPrice(), storeRequestDto.getLocation(), storeRequestDto.getStoreName());
        storeRequestInfoRepository.save(storeRequestInfo);

        return storeRequestInfo.getId();
    }

    @Transactional
    public Long acceptStore(Long userId, Long requestId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkManager(user);

        StoreRequestInfo storeRequestInfo = storeRequestInfoRepository.findById(requestId).orElseThrow();
        storeRequestInfo.setStatus(StoreRequestStatus.ACCEPT);

        Store store = storeRequestInfo.createStore();
        storeRepository.save(store);

        return store.getId();
    }

    @Transactional
    public Long denyStore(Long userId, Long requestId) {
        User user = userRepository.findById(userId).orElseThrow();
        checkManager(user);

        StoreRequestInfo storeRequestInfo = storeRequestInfoRepository.findById(requestId).orElseThrow();
        storeRequestInfo.setStatus(StoreRequestStatus.DENY);

        return requestId;
    }

    @Transactional
    public Long addMenu(Long userId, Long storeId, MenuAddDto menuAddDto) {
        User user = userRepository.findById(userId).orElseThrow();
        checkStore(user);

        Store store = storeRepository.findById(storeId).orElseThrow();
        store.addMenu(menuAddDto.getMenuName(), menuAddDto.getMenuPrice());

        return storeId;
    }

    public List<StoreResDto> getStoreList(Long userId) {
        return storeRepository.findAll().stream()
                .map(StoreResDto::new)
                .toList();
    }

    public StoreDetailResDto getStoreDetail(Long userId, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();

        return new StoreDetailResDto(store);
    }

    private void checkManager(User user){
        if(user.getUserRole() != UserRole.MANAGER){
            throw new RuntimeException();
        }
    }

    private void checkStore(User user){
        if(user.getUserRole() != UserRole.STORE){
            throw new RuntimeException();
        }
    }
}
