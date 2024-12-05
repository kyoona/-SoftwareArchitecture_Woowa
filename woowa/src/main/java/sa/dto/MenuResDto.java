package sa.dto;

import lombok.Getter;
import sa.domain.Menu;

@Getter
public class MenuResDto {

    private Long menuId;
    private String menuName;
    private int menuPrice;

    public MenuResDto(Menu menu) {
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
        this.menuPrice = menu.getMenuPrice();
    }
}
