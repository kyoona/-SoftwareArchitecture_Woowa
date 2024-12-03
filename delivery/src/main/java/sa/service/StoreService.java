package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreService {
    private final UserRepository userRepository;
}
