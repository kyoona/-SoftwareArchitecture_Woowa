package sa.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sa.service.OrderService;

@RequiredArgsConstructor
@Component
public class OrderEventHandler {

    private OrderService orderService;

    @EventListener
    public void checkOrderAccept(OrderEvent orderEvent){
        orderService.checkOrderAcceptAndCancel(orderEvent.getOrderId());
    }
}
