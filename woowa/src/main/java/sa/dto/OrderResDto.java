package sa.dto;

import lombok.Getter;
import sa.domain.Location;
import sa.domain.Order;
import sa.domain.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResDto {

    private Long storeId;
    private String storeName;
    private int totalPrice;
    private List<MenuResDto> menuList = new ArrayList<>();
    private Location location;
    private OrderStatus orderStatus;

    public OrderResDto(Order order) {
        this.storeId = order.getStore().getId();
        this.storeName = order.getStore().getStoreName();
        this.totalPrice = order.getTotalPrice();
        this.location = order.getLocation();
        this.orderStatus = order.getOrderStatus();

        order.getOrderMenuList().forEach((om) -> {
            menuList.add(new MenuResDto(om));
        });
    }
}
