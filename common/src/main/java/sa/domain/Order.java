package sa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "orders")
@Entity
public class Order {

    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "storeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    private int totalPrice;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.WAIT;

    public static Order create(User user, Store store, Location location, Map<Menu, Integer> menuList){
        Order order = new Order();
        order.user = user;
        order.store = store;
        order.location = location;

        menuList.forEach((menu, count) -> order.addMenu(menu, count));
        order.totalPrice += store.getDeliveryPrice();

        return order;
    }

    private void addMenu(Menu menu, int count){
        OrderMenu orderMenu = OrderMenu.create(menu, count, this);
        this.orderMenuList.add(orderMenu);
        this.totalPrice += menu.getMenuPrice() * count;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
