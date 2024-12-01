package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.Menu;
import sa.domain.Order;
import sa.domain.User;
import sa.dto.OrderAddDto;
import sa.dto.OrderMenuDto;
import sa.repository.OrderRepository;
import sa.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long requestOrder(Long userId, OrderAddDto orderAddDto) {
        User user = userRepository.findById(userId).orElseThrow();

        List<Long> menuIdList = orderAddDto.getMenuList().stream()
                .map(OrderMenuDto::getMenuId)
                .toList();
        Map<Long, Menu> menuMap = orderRepository.findMenuListById(menuIdList);

        Map<Menu, Integer> orderMenuMap = orderAddDto.getMenuList().stream()
                .collect(Collectors.toMap(
                        (m) -> menuMap.get(m.getMenuId()),
                        (m) -> m.getCount()
                ));

        Order order = Order.create(user, orderAddDto.getLocation(), orderMenuMap);
        orderRepository.save(order);

        //TODO 결제 요청 produce

        return order.getId();
    }
}
