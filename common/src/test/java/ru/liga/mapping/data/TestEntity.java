package ru.liga.mapping.data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.enums.StatusCourier;
import ru.liga.enums.StatusOrder;
import ru.liga.enums.StatusRestaurant;
import static java.util.UUID.randomUUID;

public class TestEntity {
    public Order getOrder() {
        UUID id = randomUUID();
        Customer customer = getCustomer();
        Restaurant restaurant = getRestaurant();
        Courier courier = getCourier();
        return new Order(
            id,
            StatusOrder.KITCHEN_PREPARING,
            OffsetDateTime.now(),
            customer,
            restaurant,
            courier
        );
    }

    public Customer getCustomer() {
        UUID id = randomUUID();
        return new Customer(
            id,
            "88005553535",
            "customer@gmail.com",
            "33.2323, 44.2323"
        );
    }

    public Restaurant getRestaurant() {
        UUID id = randomUUID();
        return new Restaurant(id, "Как у мамы", "53.2553, 23.2443", StatusRestaurant.OPEN);
    }

    public Courier getCourier() {
        UUID id = randomUUID();
        return new Courier(id, StatusCourier.DELIVERY_DENIED, "88005553535", "17.7355, 59.2993");
    }

    public OrderItem getOrderItem() {
        UUID id = randomUUID();
        return new OrderItem(id, getOrder(), getRestaurantMenuItem(), BigDecimal.valueOf(1000), 3);
    }

    public RestaurantMenuItem getRestaurantMenuItem() {
        UUID id = randomUUID();
        return new RestaurantMenuItem(
            id,
            getRestaurant(),
            "сосиски",
            BigDecimal.valueOf(100),
            "urlImage",
            "сосиски обыкновенные"
        );
    }
}
