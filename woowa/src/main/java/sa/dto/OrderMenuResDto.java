package sa.dto;

import lombok.Getter;
import sa.domain.Menu;
import sa.domain.OrderMenu;

@Getter
public class OrderMenuResDto {

    private Long menuId;
    private String menuName;
    private int menuPrice;
    private int count;

    public OrderMenuResDto(OrderMenu orderMenu) {
        Menu menu = orderMenu.getMenu();
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.count = orderMenu.getCount();
    }
}
