package ru.liga.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.response.RestaurantByStatusResponse;
import ru.liga.dto.response.RestaurantResponse;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.enums.StatusRestaurant;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapper.JpaRestaurantMapper;
import ru.liga.mapper.JpaRestaurantOrderMapper;
import ru.liga.repository.JpaOrderItemRepository;
import ru.liga.repository.JpaOrderRepository;
import ru.liga.repository.JpaRestaurantRepository;
import ru.liga.service.RestaurantService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JpaRestaurantService implements RestaurantService {
    @Autowired
    JpaRestaurantRepository jpaRestaurantRepository;
    @Autowired
    JpaOrderRepository jpaOrderRepository;
    @Autowired
    JpaOrderItemRepository jpaOrderItemRepository;

    @Transactional
    public RestaurantResponse findRestaurantById(Long id) {
        Restaurant restaurant = jpaRestaurantRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return JpaRestaurantMapper.map(restaurant);
    }
    @Transactional(readOnly = true)
    public List<RestaurantByStatusResponse> findRestaurantsByStatus(StatusRestaurant status) {
        List<Restaurant> restaurants = jpaRestaurantRepository.findByStatus(status);


        List<Order> orders = new ArrayList<>();
        for (Restaurant rest : restaurants) {
            Order order = jpaOrderRepository.findByRestaurantId(rest.getId());
            orders.add(order);
        }

        HashMap<Order, List<OrderItem>> orderItemsMapOrder = new HashMap<>();
        for (Order ord : orders) {
            List<OrderItem> items = jpaOrderItemRepository.findByOrderId(ord.getId());
            orderItemsMapOrder.put(ord, items);
        }
        return JpaRestaurantOrderMapper.map(orderItemsMapOrder);

    }
    @Transactional(readOnly = true)
    public List<RestaurantResponse> findAllRestaurants() {
        List<Restaurant> restaurants = jpaRestaurantRepository.findAll();//firstPageWithTenElements);

        return JpaRestaurantMapper.mapList(restaurants);
    }
}
