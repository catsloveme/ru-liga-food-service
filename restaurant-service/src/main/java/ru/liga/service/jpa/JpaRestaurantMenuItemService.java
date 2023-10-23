package ru.liga.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapper.JpaRestaurantMenuItemMapper;
import ru.liga.repository.JpaRestaurantMenuItemRepository;
import ru.liga.repository.JpaRestaurantRepository;
import ru.liga.service.RestaurantMenuItemService;

import java.math.BigDecimal;


@Service
public class JpaRestaurantMenuItemService implements RestaurantMenuItemService {
    @Autowired
    private JpaRestaurantMenuItemRepository jpaRestaurantMenuItemRepository;
    @Autowired
    private JpaRestaurantRepository jpaRestaurantRepository;

    @Transactional
    public RestaurantMenuItemResponse findRestaurantMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = jpaRestaurantMenuItemRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return JpaRestaurantMenuItemMapper.map(restaurantMenuItem);
    }

    @Transactional
    public RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request) {
        RestaurantMenuItem restaurantMenuItem;
        Long restaurantId =  request.getRestaurantId();
        Restaurant restaurant =jpaRestaurantRepository.findById(restaurantId).orElseThrow(() ->
                new DataNotFoundException(String.format("Restaurant id = %d not found", restaurantId)));
        restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem.setRestaurant(restaurant);
        restaurantMenuItem.setName(request.getName());
        restaurantMenuItem.setPrice(request.getPrice());
        restaurantMenuItem.setImage(request.getImageUrl());
        restaurantMenuItem.setDescription(request.getDescription());
        jpaRestaurantMenuItemRepository.save(restaurantMenuItem);
        return JpaRestaurantMenuItemMapper.map(restaurantMenuItem);
}
    @Transactional
    public void deleteRestaurantMenuItemById(Long id){
        jpaRestaurantMenuItemRepository.deleteById(id);
    }
    @Transactional
    public void updatePrice(BigDecimal price, Long id){
        jpaRestaurantMenuItemRepository.updatePrice(price,id);
    }
}
