package sa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;
import sa.dto.PaymentDto;
import sa.repository.PaymentMethodRepository;

import java.util.EnumMap;

@RequiredArgsConstructor
@Service
public class ValidatorService {

    private final EnumMap<PaymentMethodType, PaymentMethodRepository> repositoryMap;

    public boolean validate(PaymentDto paymentDto) {
        boolean result = (repositoryMap.get(paymentDto.getPaymentMethodType()).findByUserId(paymentDto.getUserId()) != null);

        if (result)
            paymentDto.setPaymentStatus(PaymentStatus.ACCEPT);
        else
            paymentDto.setPaymentStatus(PaymentStatus.DENY);

        return result;
    }

}
