package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class OrderMenu {

    @Column(name = "orderMenuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String menuName;
    private int menuPrice;

    @JoinColumn(name = "menuId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @JoinColumn(name = "orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    protected static OrderMenu create(Menu menu, Order order){
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.menu = menu;
        orderMenu.menuName = menu.getMenuName();
        orderMenu.menuPrice = menu.getMenuPrice();
        orderMenu.order = order;

        return orderMenu;
    }
}
