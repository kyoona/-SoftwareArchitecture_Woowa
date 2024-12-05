package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Location;
import sa.domain.PaymentMethod;
import sa.domain.PaymentMethodType;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDto {

    private String phoneNumber;
    private Long storeId;
    private List<OrderMenuDto> menuList;
    private Location location;
    private PaymentMethodType paymentMethodType;
}
