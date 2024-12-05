package sa.dto;

import lombok.Getter;
import sa.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreDetailResDto {

    private Long storeId;
    private String storeName;
    private int deliveryPrice;
    private int minimumOrderPrice;
    private List<MenuResDto> menuList = new ArrayList<>();

    public StoreDetailResDto(Store store) {
        this.storeId = store.getId();
        this.storeName = store.getStoreName();
        this.deliveryPrice = store.getDeliveryPrice();
        this.minimumOrderPrice = store.getMinimumOrderPrice();

        store.getMenuList().forEach((m) -> {
            menuList.add(new MenuResDto(m));
        });
    }
}
