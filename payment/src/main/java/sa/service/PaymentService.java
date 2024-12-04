package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.PaymentDto;
import sa.repository.PaymentMethodRepository;
import sa.repository.PaymentRepository;

import java.util.EnumMap;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final EnumMap<PaymentMethodType, PaymentMethodRepository> paymentMethodRepositoryMap;

    @Transactional
    public Long processPayment(PaymentDto paymentDto) {
        if(paymentDto.getPaymentStatus() != PaymentStatus.ACCEPT)
            throw new RuntimeException("검증되지 않은 결제 정보");

        PaymentMethod paymentMethod = getPaymentMethod(paymentDto);

        int paid = paymentMethod.pay(paymentDto.getTotalPrice());
        if (paymentDto.getTotalPrice() == paid)
            paymentDto.setPaymentStatus(PaymentStatus.PAID);

        return paymentRepository.save(PaymentDto.getPayment(paymentDto)).getId();
    }

    @Transactional
    public PaymentDto cancelPayment(PaymentDto paymentDto) {
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

        return paymentDto;
    }

    private PaymentMethod getPaymentMethod(PaymentDto paymentDto) {
        PaymentMethodRepository paymentMethodRepository = paymentMethodRepositoryMap.get(paymentDto.getPaymentMethodType());
        return (PaymentMethod) paymentMethodRepository.findByUserId(paymentDto.getUserId());
    }

}
