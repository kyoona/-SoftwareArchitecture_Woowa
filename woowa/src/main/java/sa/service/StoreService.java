package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.StoreRequestInfo;
import sa.domain.User;
import sa.dto.StoreRequestDto;
import sa.repository.StoreRequestInfoRepository;
import sa.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreService {

    private final UserRepository userRepository;
    private final StoreRequestInfoRepository storeRequestInfoRepository;

    @Transactional
    public Long requestStore(Long userId, StoreRequestDto storeRequestDto) {
        StoreRequestInfo storeRequestInfo = StoreRequestInfo.create(storeRequestDto.getMinimumOrderPrice(), storeRequestDto.getDeliveryPrice(), storeRequestDto.getLocation(), storeRequestDto.getStoreName());
        storeRequestInfoRepository.save(storeRequestInfo);

        return storeRequestInfo.getId();
    }
}
