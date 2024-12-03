package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.domain.*;
import sa.dto.PaymentDto;
import sa.repository.AccountTransferRepository;
import sa.repository.PaymentMethodRepository;
import sa.repository.PaymentRepository;

import java.util.EnumMap;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final EnumMap<PaymentMethodType, PaymentMethodRepository> repositoryMap;

    @Transactional
    public Long processPayment(PaymentDto paymentDto) {
        PaymentMethod paymentMethod = (PaymentMethod) repositoryMap.get(paymentDto.getPaymentMethodType()).findByUserId(paymentDto.getUserId());
        paymentMethod.pay(paymentDto.getTotalPrice());

        paymentDto.setPaymentStatus(PaymentStatus.PAID);

        Payment payment = paymentRepository.save(new Payment(
                paymentDto.getUserId(),
                paymentDto.getPaymentMethodType(),
                paymentDto.getTotalPrice(),
                paymentDto.getPaymentStatus())
        );

        return payment.getId();
    }

    public void cancelPayment() {
    }
}
