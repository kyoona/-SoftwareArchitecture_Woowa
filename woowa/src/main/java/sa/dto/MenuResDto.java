package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sa.domain.Menu;
import sa.domain.OrderMenu;

@Getter
public class MenuResDto {

    private Long menuId;
    private String menuName;
    private int menuPrice;
    private int count;

    public MenuResDto(OrderMenu orderMenu) {
        Menu menu = orderMenu.getMenu();
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
        this.count = orderMenu.getCount();
    }
}
