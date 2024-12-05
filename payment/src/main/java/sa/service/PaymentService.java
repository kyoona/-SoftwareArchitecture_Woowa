package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.Payment;
import sa.domain.PaymentMethod;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;
import sa.dto.PaymentDto;
import sa.kafka.KafkaProducer;
import sa.kafka.KafkaTopic;
import sa.kafka.PaymentResponseMsg;
import sa.repository.PaymentMethodRepository;
import sa.repository.PaymentRepository;

import java.util.EnumMap;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final EnumMap<PaymentMethodType, PaymentMethodRepository> paymentMethodRepositoryMap;

    private final KafkaProducer kafkaProducer;

    @Transactional
    public void processPayment(PaymentDto paymentDto) {
        if(paymentDto.getPaymentStatus() != PaymentStatus.ACCEPT)
            throw new RuntimeException("검증되지 않은 결제 정보");

        PaymentMethod paymentMethod = getPaymentMethod(paymentDto);

        int paid = paymentMethod.pay(paymentDto.getTotalPrice());
        boolean result = paymentDto.getTotalPrice() == paid;

        if (!result)
            throw new RuntimeException("잔액 부족");

        paymentDto.setPaymentStatus(PaymentStatus.PAID);
        Payment payment = paymentRepository.save(PaymentDto.getPayment(paymentDto));

        paymentDto.setPaymentId(payment.getId());

        kafkaProducer.sendMessage(KafkaTopic.payment_response, new PaymentResponseMsg(paymentDto.getOrderId(), result));
    }

    @Transactional
    public void cancelPayment(PaymentDto paymentDto) {
        Payment payment = paymentRepository.findById(paymentDto.getPaymentId()).orElseThrow();

        if(payment.getPaymentStatus() != PaymentStatus.PAID) {
            throw new RuntimeException("완료되지 않은 결제");
        }

        PaymentMethod paymentMethod = getPaymentMethod(paymentDto);

        int refund = paymentMethod.refund(payment.getTotalPrice());
        if (payment.getTotalPrice() == refund)
            payment.setPaymentStatus(PaymentStatus.REFUND);

        paymentRepository.save(payment);
        paymentDto.setPaymentStatus(PaymentStatus.REFUND);

    }

    private PaymentMethod getPaymentMethod(PaymentDto paymentDto) {
        PaymentMethodRepository paymentMethodRepository = paymentMethodRepositoryMap.get(paymentDto.getPaymentMethodType());
        return (PaymentMethod) paymentMethodRepository.findByUserId(paymentDto.getUserId());
    }

}
