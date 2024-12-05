package sa.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sa.dto.PaymentDto;
import sa.service.PaymentService;
import sa.service.ValidatorService;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;
    private final ValidatorService validatorService;

    @KafkaListener(topics = {"payment_request"}, groupId = "payment", concurrency = "1")
    public void consumePaymentStatus(ConsumerRecord<String, String> data){
        try {
            PaymentRequestMsg message = objectMapper.readValue(data.value(), PaymentRequestMsg.class);
            PaymentDto paymentDto = new PaymentDto(message.getOrderId(), message.getUserId(), message.getPaymentMethodType(), message.getTotalPrice());
            validatorService.validate(paymentDto);
            paymentService.processPayment(paymentDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
