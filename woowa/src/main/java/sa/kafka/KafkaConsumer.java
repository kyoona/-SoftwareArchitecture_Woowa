package sa.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sa.service.OrderService;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(topics = {"payment_response"}, groupId = "woowa", concurrency = "1")
    public void consumePaymentStatus(ConsumerRecord<String, String> data){
        try {
            PaymentResponseMsg message = objectMapper.readValue(data.value(), PaymentResponseMsg.class);
            orderService.setPaymentStatus(message.getOrderId(), message.isSuccess());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = {"deliver_status"}, groupId = "woowa", concurrency = "1")
    public void consumeDeliveryStatus(ConsumerRecord<String, String> data){
        try {
//            PaymentResponseMsg message = objectMapper.readValue(data.value(), PaymentResponseMsg.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
