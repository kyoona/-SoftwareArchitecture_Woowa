package sa.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishOrderEvent(Long orderId){
        OrderEvent event = new OrderEvent(orderId);
        publisher.publishEvent(event);
    }
}
